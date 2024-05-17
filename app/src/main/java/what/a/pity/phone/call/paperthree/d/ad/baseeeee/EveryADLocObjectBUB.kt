package what.a.pity.phone.call.paperthree.d.ad.baseeeee

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import what.a.pity.phone.call.paperthree.b.ac.PreViewActivity
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils


class EveryADLocObjectBUB(private val snvlinjvk: ADType) {

    private val dataList: MutableList<EveryADBean> = mutableListOf()
    private val cacheListncsudbca: MutableList<SoWhatCanYouDo> = mutableListOf()
    private var isLoadBHBU = false
    private var vsnoevn: (Boolean) -> Unit = {}
    val haveCache: Boolean get() = cacheListncsudbca.isNotEmpty()

    private fun adCaData(): SoWhatCanYouDo? = cacheListncsudbca.removeFirstOrNull()

    fun showFullScreenAdBIUYBUI(activity: Activity, onAdDismissed: () -> Unit) {

        if (cacheListncsudbca.isEmpty()) {
            onAdDismissed.invoke()
            return
        }
        val baseAd = adCaData()
        if (null == baseAd) {
            onAdDismissed.invoke()
            return
        }
        baseAd.showMyNameIsHei(activity = activity, onAdDismissed = onAdDismissed)
        vsnoevn = {}
        if (baseAd.adBean.where != "fa_c_ongre" && baseAd.adBean.where != "introductionIV" && baseAd.adBean.where != "personalizeIV") {
            preload(activity)
        }
    }

    fun showVIUVYNativeAd(
        activity: Activity,
        parent: ViewGroup?,
        onBaseAd: (SoWhatCanYouDo) -> Unit
    ) {
        if (cacheListncsudbca.isEmpty()) {
            Log.e("TAG", "showVIUVYNativeAd: 1", )
            return
        }
        val baseAd = adCaData() ?: return
        Log.e("TAG", "showVIUVYNativeAd: 2")
        baseAd.showMyNameIsHei(activity = activity, nativeParent = parent)
        onBaseAd.invoke(baseAd)
        vsnoevn = {}
        preload(activity)
    }

    fun initializeSource(data: MutableList<EveryADBean>?) {
        dataList.run {
            clear()
            addAll(data ?: mutableListOf())
            sortByDescending { it.adWeightHAHHA }
        }
    }

    fun preload(context: Context) {
        MainScope().launch {
            if (dataList.isEmpty()) return@launch
            if (!BIBIUBADDDDUtils.canShowAD()) return@launch
            if (haveCache && isCacheOverTime().not()) {
                return@launch
            }
            if (haveCache) {
                return@launch
            }
            if (isLoadBHBU) return@launch
            isLoadBHBU = true
            LLKLKMNCNWmnLLADLoad(context, snvlinjvk, dataList, cacheListncsudbca) {
                isLoadBHBU = false
                vsnoevn.invoke(it)
            }.startLoadBUH()
        }
    }

    private fun isCacheOverTime(): Boolean {
        val item = cacheListncsudbca.firstOrNull() ?: return false
        return if (System.currentTimeMillis() - item.loadTime >= (1000L * 60L * 50L)) {
            cacheListncsudbca.remove(item)
            true
        } else {
            false
        }
    }
}