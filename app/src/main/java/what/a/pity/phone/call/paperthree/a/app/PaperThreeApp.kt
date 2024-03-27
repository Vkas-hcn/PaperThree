package what.a.pity.phone.call.paperthree.a.app

import android.app.Application
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils

class PaperThreeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInitUtils().initAppLifeObserver(this)
    }
}