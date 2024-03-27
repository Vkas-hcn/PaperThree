package what.a.pity.phone.call.paperthree.d.ad.baseeeee

import android.content.Context
import com.blankj.utilcode.util.LogUtils


class LLKLKMNCNWmnLLADLoad(
    private val contextcaniscnaiesbc: Context,
    private val snvkdjnv: ADType,
    private val svdkalc: MutableList<EveryADBean>,
    private val nslcasidckn: MutableList<SoWhatCanYouDo>,
    private val canusdkbcaushdconLoad: (Boolean) -> Unit = {}
) {

    fun startLoadBUH() {
        if (svdkalc.isEmpty()) canusdkbcaushdconLoad.invoke(false) else preloadAdByIndexibscdaiuhsdbcuahsbcukashbcu(0)
    }

    private fun preloadAdByIndexibscdaiuhsdbcuahsbcukashbcu(index: Int) {
        val item = svdkalc.getOrNull(index)
        if (null == item) {
            LogUtils.e("WallPaper AD item null")
            canusdkbcaushdconLoad.invoke(false)
            return
        }
        val baseAdanscinc = when (item.adYype) {
            "plai" -> OpenLoadBUHVBUHVYVlll(context = contextcaniscnaiesbc, item = item)
            "nibi" -> NativeLMDWMIWM(context = contextcaniscnaiesbc, item = item)
            "ity"->ONIJNIIOINOIInterLoad(context = contextcaniscnaiesbc, item = item)
            else -> null
        }
        if (null == baseAdanscinc) {
            LogUtils.e("WallPaper AD baseAd null  ${item.adYype}")
            preloadAdByIndexibscdaiuhsdbcuahsbcukashbcu(index + 1)
            return
        }
        val type = when(snvkdjnv.placeName){
            "aiesbvaoubveaubvauwevb"->"Open AD"
            "amvpoesinvapiwbevnaoewubvaourbvaouwehv"->"InterstitialAd"
            else->"NativeAd"
        }
        baseAdanscinc.loadHowAreYou(onAdLoaded = {
            nslcasidckn.add(baseAdanscinc)
            nslcasidckn.sortByDescending { it.adBean.adWeightHAHHA }
            canusdkbcaushdconLoad.invoke(true)
            LogUtils.e("WallPaper AD","$type, id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} onAdLoaded")
        }, onAdLoadFailed = {
            LogUtils.e("WallPaper AD","$type, id :${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} onAdFailedLoad")
            preloadAdByIndexibscdaiuhsdbcuahsbcukashbcu(index + 1)
        })
    }

}