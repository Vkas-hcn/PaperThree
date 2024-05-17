package what.a.pity.phone.call.paperthree.a.app

import android.app.Application
import com.blankj.utilcode.util.SPUtils
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils

class PaperThreeApp : Application() {
    companion object {
        lateinit var instance: PaperThreeApp
        var isGifImage = false
        var isAdImage = false
        var isHaveLight = false
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppInitUtils().initAppLifeObserver(this)
    }
}