package what.a.pity.phone.call.paperthree.d.ae.fb

import com.blankj.utilcode.util.SPUtils
import com.google.gson.Gson
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.AdvertiseEntity


object FireBaseAD {


    fun setADData() {
        if (!PaperAppFireBaseUtils.isHaveHistoryData()) {
            BIBIUBADDDDUtils.initializeAdConfig(PaperThreeConstant.nfskjnkkk)
        }
    }

    fun checkADData(result: (Boolean) -> Unit = { true }) {
        if (PaperAppFireBaseUtils.serverADData != null) {
            result.invoke(true)
        } else result.invoke(false)
    }

    fun checkADDataWithServer() {
        if (PaperAppFireBaseUtils.serverADData == null) {
            setADData()
        }
    }


    fun getFirebaseStringADData() {
        if (PaperAppFireBaseUtils.listAD?.isNotEmpty() == true && PaperAppFireBaseUtils.listAD?.isNotBlank() == true && PaperAppFireBaseUtils.listAD != null) {
            try {
                val listAd = String(android.util.Base64.decode(PaperAppFireBaseUtils.listAD, android.util.Base64.DEFAULT))
                val gson = Gson()
                val resultBean: AdvertiseEntity? =
                    gson.fromJson(listAd, AdvertiseEntity::class.java)
                if (resultBean != null) {
                    PaperAppFireBaseUtils.isGetADData = true
                    PaperAppFireBaseUtils.serverADData = resultBean
                    BIBIUBADDDDUtils.initializeAdConfig(listAd)
                    SPUtils.getInstance()
                        .put(PaperThreeConstant.AD_HISTORY, listAd)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


}