package what.a.pity.phone.call.paperthree.a.app

import android.app.Application
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils

class PaperThreeApp : Application() {
    companion object {
        lateinit var instance: PaperThreeApp
        var isGifImage = false


    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppInitUtils().initAppLifeObserver(this)
    }
}