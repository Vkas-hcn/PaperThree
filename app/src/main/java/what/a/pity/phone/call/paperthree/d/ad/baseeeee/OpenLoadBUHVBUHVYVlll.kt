package what.a.pity.phone.call.paperthree.d.ad.baseeeee

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OpenLoadBUHVBUHVYVlll(private val context: Context, private val item: EveryADBean) : SoWhatCanYouDo(item) {

    private var ad: Any? = null
    private val adRequest: AdRequest get() = AdRequest.Builder().build()


    override fun loadHowAreYou(onAdLoaded: () -> Unit, onAdLoadFailed: (msg: String?) -> Unit) {
        LogUtils.e(
            "WallPaper AD",
            " OpenAD, id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} start preload"
        )
        loadAppOpen(onAdLoaded, onAdLoadFailed)
    }

    override fun showMyNameIsHei(activity: Activity, nativeParent: ViewGroup?, onAdDismissed: () -> Unit) {
        val callback: FullScreenContentCallback by lazy {
            object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    val baseAct = activity as? AppCompatActivity
                    if (null != baseAct) {
                        baseAct.lifecycleScope.launch {
                            while (Lifecycle.State.RESUMED != baseAct.lifecycle.currentState)
                                delay(200L)
                            onAdDismissed.invoke()
                        }
                    } else onAdDismissed.invoke()
                }

                override fun onAdShowedFullScreenContent() {
                    LogUtils.e(
                        "WallPaper AD",
                        "OpenAD, id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} SUS Show"
                    )
                    BIBIUBADDDDUtils.countAD()
                }

                override fun onAdFailedToShowFullScreenContent(e: AdError) {
                    val baseAct = activity as? AppCompatActivity
                    if (null != baseAct) {
                        baseAct.lifecycleScope.launch {
                            while (Lifecycle.State.RESUMED != baseAct.lifecycle.currentState)
                                delay(200L)
                            onAdDismissed.invoke()
                        }
                    } else onAdDismissed.invoke()
                }

                override fun onAdClicked() = BIBIUBADDDDUtils.countAD(s = false, c = true)
            }
        }

        fun showAdMobFullScreenAd() {
            when (val adF = ad) {
                is AppOpenAd -> {
                    adF.run {
                        fullScreenContentCallback = callback
                        show(activity)
                    }
                }
                else -> onAdDismissed.invoke()
            }
        }
        showAdMobFullScreenAd()
    }

    private fun loadAppOpen(onAdLoaded: () -> Unit, onAdLoadFailed: (msg: String?) -> Unit) {
        AppOpenAd.load(
            context,
            item.adIdKKKK,
            adRequest,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(appOpenAd: AppOpenAd) {
                    ad = appOpenAd
                    onAdLoaded.invoke()
                }

                override fun onAdFailedToLoad(e: LoadAdError) = onAdLoadFailed.invoke(e.message)
            })
    }


}