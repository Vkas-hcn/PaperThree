package what.a.pity.phone.call.paperthree.a.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.google.android.gms.ads.AdActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.b.ac.MainActivity
import what.a.pity.phone.call.paperthree.b.ac.PaperThreeActivity
import what.a.pity.phone.call.paperthree.b.ac.light.LightSettingActivity
import what.a.pity.phone.call.paperthree.b.ac.light.LightWallActivity
import what.a.pity.phone.call.paperthree.fast.light.GifWallpaperService
import what.a.pity.phone.call.paperthree.fast.light.LightWindow
import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import what.a.pity.phone.call.paperthree.R

class AppLifeCallBack : Application.ActivityLifecycleCallbacks {

    private var hotSeconds = 3

    override fun onActivityStarted(activity: Activity) {
        PaperThreeVariable.jaddaauwdList++
        if (PaperThreeVariable.isBackGround) {
            PaperThreeVariable.isBackGround = false
            if (PaperThreeVariable.isToRequestPer) return
            if ((System.currentTimeMillis() - PaperThreeVariable.exitAppTime) / 1000 > hotSeconds) {
                mslkcmkc(activity)
            }
        }
    }

    override fun onActivityStopped(activity: Activity) {
        PaperThreeVariable.jaddaauwdList--
        if (PaperThreeVariable.jaddaauwdList == 0) {
            PaperThreeVariable.isBackGround = true
            LightWindow.getInstance().showPasswordBox()
            PaperThreeVariable.exitAppTime = System.currentTimeMillis()
            if(PaperThreeApp.reallySetWall != 2){
                stopGifWallpaperService(activity)
            }
        }
    }
    @SuppressLint("ServiceCast")
    fun stopGifWallpaperService(context: Context) {
        // 停止动态壁纸服务
        (context.getSystemService(Context.WALLPAPER_SERVICE) as? GifWallpaperService.MyWallpaperEngine)?.stopWallpaper()
        // 设置静态壁纸
        setStaticWallpaper(context)
    }


    fun setStaticWallpaper(context: Context) {
        val wallpaperManager = WallpaperManager.getInstance(context)
        val defaultWallpaper = context.resources.getDrawable(R.drawable.ic_blck) // 你的静态壁纸资源
        wallpaperManager.setBitmap((defaultWallpaper as BitmapDrawable).bitmap)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        PaperThreeVariable.activityList.add(activity)
        if ((activity is LightWallActivity) || activity is LightSettingActivity) {
            LightWindow.getInstance().closeThePasswordBox()
        }
    }

    override fun onActivityResumed(activity: Activity) = Unit
    override fun onActivityPaused(activity: Activity) = Unit
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
    override fun onActivityDestroyed(activity: Activity) {
        PaperThreeVariable.activityList.remove(activity)
    }

    private fun mslkcmkc(activity: Activity) {
        val appLiveList = mutableListOf<Activity>()
        PaperThreeVariable.activityList.forEach {
            if (it is AdActivity) {
                it.finish()
            } else {
                appLiveList.add(it)
            }
        }
        PaperThreeVariable.activityList = appLiveList
        val intent = Intent(activity, PaperThreeActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

}
