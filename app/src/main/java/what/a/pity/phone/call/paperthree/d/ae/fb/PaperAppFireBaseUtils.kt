package what.a.pity.phone.call.paperthree.d.ae.fb

import android.annotation.SuppressLint
import com.blankj.utilcode.util.SPUtils
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.AdvertiseEntity

object PaperAppFireBaseUtils {

    @SuppressLint("StaticFieldLeak")
    var remoteConfig: FirebaseRemoteConfig? = null
    var serverADData: AdvertiseEntity? = null
    var listAD: String? = null


    var isGetADData: Boolean = false

    var isAppInit = true

    fun getFirebaseRemoteConfigData() {
        remoteConfig = Firebase.remoteConfig
        remoteConfig!!.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                listAD = remoteConfig?.getString("safeeas_busy")
                FireBaseAD.getFirebaseStringADData()
            }
        }
    }

    fun fourAppWait4SecondsToGetData() {
        MainScope().launch {
            var i = 1
            while (i < 8) {
                if (!isGetADData) FireBaseAD.getFirebaseStringADData()
                delay(500)
                if (i == 7) {
                    FireBaseAD.setADData()
                }
                i++
            }
        }
    }

    fun appCircleToRequestFireData() {
        MainScope().launch {
            while (true) {
                if (!isGetADData) {
                    FireBaseAD.getFirebaseStringADData()
                }
                if (isGetADData) {
                    break
                }
                delay(1000)
            }
        }
    }

    fun isHaveHistoryData(): Boolean {
        val dataString = SPUtils.getInstance().getString(PaperThreeConstant.AD_HISTORY, "")
        val gson = Gson()
        val resultBean: AdvertiseEntity? = gson.fromJson(dataString, AdvertiseEntity::class.java)
        return if (dataString != null && resultBean != null) {
            BIBIUBADDDDUtils.initializeAdConfig(dataString)
            true
        } else false

    }


}