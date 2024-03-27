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
                val gson = Gson()
                val resultBean: AdvertiseEntity? =
                    gson.fromJson(PaperAppFireBaseUtils.listAD, AdvertiseEntity::class.java)
                if (resultBean != null) {
                    PaperAppFireBaseUtils.isGetADData = true
                    PaperAppFireBaseUtils.serverADData = resultBean
                    BIBIUBADDDDUtils.initializeAdConfig(PaperAppFireBaseUtils.listAD)
                    SPUtils.getInstance()
                        .put(PaperThreeConstant.AD_HISTORY, PaperAppFireBaseUtils.listAD)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


}