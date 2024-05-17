package what.a.pity.phone.call.paperthree.fast.light

import android.R.attr
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Handler
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
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
        private val layoutInflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        private var wallpaperView: View? = null
        var scrImage: RotatingGradientRectangleView? = null
        var gifImage: MyGifImageView? = null
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
                if (wallpaperView == null) {
                    wallpaperView = layoutInflater.inflate(R.layout.layout_lock_screen2, null)
                    scrImage = wallpaperView?.findViewById(R.id.lightView)
                    gifImage = wallpaperView?.findViewById(R.id.lightGif)
                    imageWindow = wallpaperView?.findViewById(R.id.image_window)

                    imageWindow?.isVisible = true
                    if (KeyData.lightWallData != 0 && KeyData.lightWallData != -1) {
                        imageWindow?.setImageResource(KeyData.lightWallData)
                    } else {
                        imageWindow?.setImageResource(R.drawable.ic_blck)
                    }

                    wallpaperView?.measure(canvas.width, canvas.height)
                    wallpaperView?.layout(0, 0, canvas.width, canvas.height)
                    if (PaperThreeApp.isGifImageApp) {
                        gifImage?.isVisible = true
                        scrImage?.isVisible = false
                        gifImage?.setGifResource(R.drawable.ic_gif_1)
                        gifImage?.measure(canvas.width, canvas.height)
                        gifImage?.layout(0, 0, canvas.width, canvas.height)
                    } else {
                        scrImage?.isVisible = true
                        gifImage?.isVisible = false
                        scrImage?.setGradientSetting2()
                        scrImage?.measure(canvas.width, canvas.height)
                        scrImage?.layout(0, 0, canvas.width, canvas.height)
                    }
                }
                wallpaperView?.draw(canvas)
                surfaceHolder.unlockCanvasAndPost(canvas)
            }

            if (visible) {
                handler.removeCallbacks(drawRunnable)
                handler.postDelayed(drawRunnable, 1000 / 60)
            }
        }
    }

}



