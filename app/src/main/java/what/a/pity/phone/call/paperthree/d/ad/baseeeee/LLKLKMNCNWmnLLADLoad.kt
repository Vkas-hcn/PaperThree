package what.a.pity.phone.call.paperthree.d.ad.baseeeee

import android.content.Context
import android.util.Log
import com.blankj.utilcode.util.LogUtils
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils


class LLKLKMNCNWmnLLADLoad(
    private val contextcaniscnaiesbc: Context,
    private val snvkdjnv: ADType,
    private val svdkalc: MutableList<EveryADBean>,
    private val nslcasidckn: MutableList<SoWhatCanYouDo>,
    private val canusdkbcaushdconLoad: (Boolean) -> Unit = {}
) {

    fun startLoadBUH() {
        if (svdkalc.isEmpty()) canusdkbcaushdconLoad.invoke(false) else preloadAdByIndexibscdaiuhsdbcuahsbcukashbcu(
            0
        )
    }

    private fun preloadAdByIndexibscdaiuhsdbcuahsbcukashbcu(index: Int) {
        val item = svdkalc.getOrNull(index)
        if (null == item) {
            LogUtils.e("WallPaper AD-${item?.where}- item null")
            canusdkbcaushdconLoad.invoke(false)
            return
        }
        if ((item.where == "yulan" || item.where == "end") && (!GetWallDataUtils.showAdCenter())) {
            Log.e("TAG", "买量屏蔽${item.where}广告加载 ", )
            return
        }
        if ((item.where == "yulan" || item.where == "end") && (!GetWallDataUtils.showAdBlacklist())) {
            Log.e("TAG", "黑名单屏蔽${item.where}广告加载 ", )
            return
        }
        val baseAdanscinc = when (item.adYype) {
            "plai" -> OpenLoadBUHVBUHVYVlll(context = contextcaniscnaiesbc, item = item)
            "nibi" -> NativeLMDWMIWM(context = contextcaniscnaiesbc, item = item)
            "ity" -> ONIJNIIOINOIInterLoad(context = contextcaniscnaiesbc, item = item)
            else -> null
        }
        if (null == baseAdanscinc) {
            LogUtils.e("WallPaper AD baseAd null  ${item.adYype}")
            preloadAdByIndexibscdaiuhsdbcuahsbcukashbcu(index + 1)
            return
        }
        baseAdanscinc.loadHowAreYou(onAdLoaded = {
            nslcasidckn.add(baseAdanscinc)
            nslcasidckn.sortByDescending { it.adBean.adWeightHAHHA }
            canusdkbcaushdconLoad.invoke(true)
            LogUtils.e(
                "WallPaper AD",
                "${item.where}, id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} onAdLoaded-success"
            )
        }, onAdLoadFailed = {
            LogUtils.e(
                "WallPaper AD",
                "${item.where}, id :${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} onAdLoaded-error=${it}"
            )
            preloadAdByIndexibscdaiuhsdbcuahsbcukashbcu(index + 1)
        })
    }

}