package what.a.pity.phone.call.paperthree.fast.light

import android.R.attr
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Handler
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.WindowManager
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.b.ac.light.MyGifImageView
import what.a.pity.phone.call.paperthree.b.ac.light.RotatingGradientRectangleView
import what.a.pity.phone.call.paperthree.b.ac.light.WinDowImageView
import what.a.pity.phone.call.paperthree.fast.KeyData


class GifWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine {
        return MyWallpaperEngine()
    }

    inner class MyWallpaperEngine : Engine() {

        private val handler = Handler()
        private var visible = true
        private var layoutInflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        private var wallpaperView: View? = null
        var scrImage: RotatingGradientRectangleView? = null
        var gifImage: MyGifImageView? = null
        var gifImage2: LottieAnimationView? = null

        var imageWindow: WinDowImageView? = null
        private val drawRunnable = Runnable {
            draw()
        }


        override fun onVisibilityChanged(visible: Boolean) {
            this.visible = visible
            if (visible) {
                draw()
            } else {
                handler.removeCallbacks(drawRunnable)
            }
        }

        override fun onSurfaceCreated(holder: SurfaceHolder) {
            super.onSurfaceCreated(holder)

            draw()

        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
            handler.removeCallbacks(drawRunnable)
        }

        private fun draw() {
            val surfaceHolder = surfaceHolder
            val canvas: Canvas? = if (surfaceHolder.surface.isValid) {
                surfaceHolder.lockCanvas()
            } else {
                null
            }

            if (canvas != null) {
                canvas.drawColor(Color.BLACK)
                val layoutParams = WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    PixelFormat.TRANSLUCENT
                )
                if (wallpaperView == null) {


                    wallpaperView = layoutInflater.inflate(R.layout.layout_lock_screen2, null)

                    scrImage = wallpaperView?.findViewById(R.id.lightView)
                    gifImage = wallpaperView?.findViewById(R.id.lightGif)
                    gifImage2 = wallpaperView?.findViewById(R.id.lav_smile)
                    val layoutParams2 = gifImage2!!.layoutParams
                    layoutParams2.width = resources.displayMetrics.widthPixels
                    layoutParams2.height = resources.displayMetrics.heightPixels + 200
                    gifImage2!!.layoutParams = layoutParams2
                    imageWindow = wallpaperView?.findViewById(R.id.image_window)

                    imageWindow?.isVisible = true
                    if (KeyData.lightWallData != 0) {
                        imageWindow?.setImageResource(KeyData.lightWallData)
                    } else {
                        imageWindow?.setImageResource(R.drawable.ic_blck)
                    }

                    if (PaperThreeApp.isGifImage) {
                        scrImage?.isVisible = false
                        gifImage?.isVisible = false
                        Log.e("TAG", "draw: 1111")
                        gifImage2?.playAnimation()
                        ValueAnimator.ofFloat(0f, 1f).apply {
                            repeatCount = -1
                            duration = 1000 / 60
                            addUpdateListener { gifImage2?.progress = it.animatedValue as Float }
                            start()
                        }
                    } else {
                        Log.e("TAG", "draw: 3333")
                        scrImage?.isVisible = true
                        gifImage?.isVisible = false
                        scrImage?.setGradientSetting()
                    }
                    wallpaperView?.measure(canvas.width, canvas.height)
                    wallpaperView?.layout(0, 0, canvas.width, canvas.height)
                    windowManager?.addView(wallpaperView, layoutParams)

                }
                wallpaperView?.draw(canvas)
                surfaceHolder.unlockCanvasAndPost(canvas)

            }

            if (visible) {
                Log.e("TAG", " service visible: ")
                handler.removeCallbacks(drawRunnable)
                handler.postDelayed(drawRunnable, 1000 / 60)
            }
        }
    }

}



