package what.a.pity.phone.call.paperthree.d.ad.baseeeee

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustAdRevenue
import com.adjust.sdk.AdjustConfig
import com.blankj.utilcode.util.LogUtils
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils

class ONIJNIIOINOIInterLoad(private val context: Context, private val item: EveryADBean) :
    SoWhatCanYouDo(item) {

    private var ad: Any? = null
    private val adRequest: AdRequest get() = AdRequest.Builder().build()
    private var rewardComplete = false
    override fun loadHowAreYou(onAdLoaded: () -> Unit, onAdLoadFailed: (msg: String?) -> Unit) {
        val type = when (item.adYype) {
            "plai" -> "Open"
            else -> "Inter"
        }
        LogUtils.e(
            "WallPaper AD=${item.where}",
            " InterstitialAd, type${type} id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} start preload"
        )
        loadInavnisduabnviosbvaoisubvd(onAdLoaded, onAdLoadFailed)

    }

    fun showRvNext(nextFun: () -> Unit) {
        if (item.where == "RV") {
            if (rewardComplete) {
                nextFun()
                rewardComplete = false
            }
        } else {
            nextFun()
        }
    }

    override fun showMyNameIsHei(
        activity: Activity,
        nativeParent: ViewGroup?,
        onAdDismissed: () -> Unit
    ) {
        val callback: FullScreenContentCallback by lazy {
            object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    val baseAct = activity as? AppCompatActivity
                    if (null != baseAct) {
                        baseAct.lifecycleScope.launch {
                            while (Lifecycle.State.RESUMED != baseAct.lifecycle.currentState) delay(
                                200L
                            )
                            showRvNext {
                                onAdDismissed.invoke()
                            }
                        }
                    } else {
                        showRvNext {
                            onAdDismissed.invoke()
                        }
                    }
                }

                override fun onAdShowedFullScreenContent() {
                    LogUtils.e(
                        "WallPaper AD=${item.where}",
                        " InterstitialAd, id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} SUS Show"
                    )
                    BIBIUBADDDDUtils.countAD(true)
                }

                override fun onAdFailedToShowFullScreenContent(e: AdError) {
                    LogUtils.e(
                        "WallPaper AD=${item.where}",
                        " InterstitialAd, id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} Failed Show"
                    )
                    val baseAct = activity as? AppCompatActivity
                    if (null != baseAct) {
                        baseAct.lifecycleScope.launch {
                            while (Lifecycle.State.RESUMED != baseAct.lifecycle.currentState) delay(
                                200L
                            )
                            onAdDismissed.invoke()
                        }
                    } else onAdDismissed.invoke()
                }

                override fun onAdClicked() {
                    Log.e("TAG", "onAdClicked: Int")
                    BIBIUBADDDDUtils.countAD(s = false, c = true)
                }
            }
        }

        fun showAdMobFullScreenAd() {
            when (val adF = ad) {
                is InterstitialAd -> {
                    adF.run {
                        fullScreenContentCallback = callback
                        show(activity)
                    }
                }

                is RewardedAd -> {
                    adF.let { ad ->
                        ad.fullScreenContentCallback = callback
                        ad.show(activity) { rewardItem ->
                            // Handle the reward.
                            val rewardAmount = rewardItem.amount
                            val rewardType = rewardItem.type
                            rewardComplete = true
                            Log.d("TAG", "User earned the reward.")
                        }
                    }
                }

                else -> onAdDismissed.invoke()
            }
        }

        showAdMobFullScreenAd()
    }


    private fun loadInavnisduabnviosbvaoisubvd(
        onAdLoaded: () -> Unit,
        onAdLoadFailed: (msg: String?) -> Unit
    ) {
        if (item.where == "RV") {
            RewardedAd.load(context, item.adIdKKKK, adRequest, object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(e: LoadAdError) = onAdLoadFailed.invoke(e.message)
                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    ad = rewardedAd
                    onAdLoaded.invoke()
                    rewardedAd.setOnPaidEventListener { adValue ->
                        WallNetDataUtils.getAdList(
                            PaperThreeApp.instance,
                            adValue,
                            rewardedAd.responseInfo,
                            item
                        )
                        BIBIUBADDDDUtils.putPointAdOnline(adValue.valueMicros)
                        val adRevenue = AdjustAdRevenue(AdjustConfig.AD_REVENUE_ADMOB)
                        adRevenue.setRevenue(adValue.valueMicros / 1000000.0, adValue.currencyCode)
                        adRevenue.setAdRevenueNetwork(rewardedAd.responseInfo.mediationAdapterClassName)
                        Adjust.trackAdRevenue(adRevenue)
                    }
                }
            })
        } else {
            InterstitialAd.load(context,
                item.adIdKKKK,
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        ad = interstitialAd
                        onAdLoaded.invoke()
                        interstitialAd.setOnPaidEventListener { adValue ->
                            WallNetDataUtils.getAdList(
                                PaperThreeApp.instance,
                                adValue,
                                interstitialAd.responseInfo,
                                item
                            )
                            BIBIUBADDDDUtils.putPointAdOnline(adValue.valueMicros)
                            val adRevenue = AdjustAdRevenue(AdjustConfig.AD_REVENUE_ADMOB)
                            adRevenue.setRevenue(
                                adValue.valueMicros / 1000000.0,
                                adValue.currencyCode
                            )
                            adRevenue.setAdRevenueNetwork(interstitialAd.responseInfo.mediationAdapterClassName)
                            Adjust.trackAdRevenue(adRevenue)
                        }
                    }

                    override fun onAdFailedToLoad(e: LoadAdError) = onAdLoadFailed.invoke(e.message)
                })
        }

    }


}