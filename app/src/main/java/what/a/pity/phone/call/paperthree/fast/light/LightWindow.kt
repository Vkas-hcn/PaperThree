package what.a.pity.phone.call.paperthree.fast.light

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Point
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.b.ac.light.RotatingGradientRectangleView
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils

class LightWindow {
    companion object {
        fun getInstance() = InstanceHelper.lockWindowHelper
    }

    object InstanceHelper {
        @SuppressLint("StaticFieldLeak")
        val lockWindowHelper = LightWindow()
    }

    var mWindowManager: WindowManager? = null
    var wmParams: WindowManager.LayoutParams? = null
    var mFloatingLayout: View? = null
    var scrImage: RotatingGradientRectangleView? = null
    var gifImage: ImageView? = null

    private lateinit var context: Context


    fun initWindow(context: Context) {
        if (mWindowManager != null) {
            return
        }
        this.context = context
        mWindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        wmParams = WindowManager.LayoutParams()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            wmParams?.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        else wmParams?.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        wmParams?.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_FULLSCREEN or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE

        wmParams?.gravity = Gravity.START or Gravity.TOP
        wmParams?.format = PixelFormat.RGBA_8888
        val screenSize = Point()
        mWindowManager?.defaultDisplay?.getRealSize(screenSize)
        wmParams?.width = screenSize.x
        wmParams?.height = screenSize.y
        processPasswordControls2(context)
    }


    private fun processPasswordControls2(context: Context) {
        val inflater = LayoutInflater.from(context)
        mFloatingLayout = inflater.inflate(R.layout.layout_lock_screen2, null)
        scrImage = mFloatingLayout?.findViewById(R.id.lightView)
        gifImage = mFloatingLayout?.findViewById(R.id.lightGif)

    }


    fun showPasswordBox() {
        if (mWindowManager != null) {
            if (PaperThreeApp.isGifImage) {
                scrImage?.isVisible = false
                gifImage?.isVisible = true
                if (gifImage != null) {
//                    GetWallDataUtils.loadDrawableGif(context, R.drawable.gif_ad_fire01, gifImage!!)
                }
            } else {
                scrImage?.isVisible = true
                gifImage?.isVisible = false
                scrImage?.setGradientSetting()
            }
            if (mWindowManager != null && mFloatingLayout?.parent != null) {
                mWindowManager?.removeView(mFloatingLayout)
            }
            mWindowManager?.addView(mFloatingLayout, wmParams)
        }
    }

    fun closeThePasswordBox() {
        if (mWindowManager != null && mFloatingLayout?.parent != null) {
            mWindowManager?.removeView(mFloatingLayout)
        }
    }


}