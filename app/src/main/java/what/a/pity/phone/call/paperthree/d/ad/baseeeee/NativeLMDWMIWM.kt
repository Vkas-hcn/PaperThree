package what.a.pity.phone.call.paperthree.d.ad.baseeeee

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import com.blankj.utilcode.util.LogUtils
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import what.a.pity.phone.call.paperthree.R

class NativeLMDWMIWM(private val context: Context, private val item: EveryADBean) : SoWhatCanYouDo(item) {

    private var nativeAd: NativeAd? = null

    override fun loadHowAreYou(onAdLoaded: () -> Unit, onAdLoadFailed: (msg: String?) -> Unit) {
        LogUtils.e(
            "WallPaper AD=${item.where}",
            " NativeAd, id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} start preload"
        )
        AdLoader.Builder(context, item.adIdKKKK).apply {
            forNativeAd { ad ->
                nativeAd = ad
                onAdLoaded.invoke()
                ad.setOnPaidEventListener {advalue->
                    ad.responseInfo?.let { nav ->
                        BIBIUBADDDDUtils.putPointAdOnline(advalue.valueMicros)
                    }
                }
            }
            withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(e: LoadAdError) {
                    onAdLoadFailed.invoke(e.message)
                }

                override fun onAdOpened() {
                    super.onAdOpened()
                    Log.e("TAG", "onAdClicked: Nat" )
                    BIBIUBADDDDUtils.countAD(s = false, c = true)
                }
            })
            withNativeAdOptions(NativeAdOptions.Builder().apply {
                setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
            }.build())

        }.build().loadAd(adRequest)
    }

    override fun showMyNameIsHei(activity: Activity, nativeParent: ViewGroup?, onAdDismissed: () -> Unit) {
        if (null == nativeAd) return
        LogUtils.e(
            "WallPaper AD=${item.where}",
            " NativeAd, id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} SUS show"
        )
        val nativeAdView = LayoutInflater.from(activity)
            .inflate(R.layout.jajjaj, nativeParent, false) as NativeAdView

        nativeAdView.findViewById<AppCompatTextView?>(R.id.mlskmclskdc_app_name).let {
            nativeAdView.headlineView =it
            it.text = nativeAd?.headline
        }
        nativeAdView.findViewById<AppCompatTextView?>(R.id.mlskmclskdc_action).let {
            nativeAdView.callToActionView =it
            it.text = nativeAd?.callToAction
        }

        nativeAdView.findViewById<ImageView>(R.id.mlskmclskdc_app_icon)?.let { iconView ->
            nativeAdView.iconView = iconView
            iconView.setImageDrawable(nativeAd?.icon?.drawable)
        }


        nativeAdView.findViewById<TextView>(R.id.mlskmclskdc_app_content)?.let { bodyView ->
            nativeAdView.bodyView = bodyView
            bodyView.text = nativeAd?.body
        }

        nativeAd?.let { nativeAdView.setNativeAd(it) }
        nativeParent?.isVisible = true
        nativeParent?.removeAllViews()
        nativeParent?.addView(nativeAdView)
        BIBIUBADDDDUtils.countAD(true)
    }

    override fun gandiao() {
        nativeAd?.destroy()
        nativeAd = null
    }

    private val adRequest: AdRequest get() = AdRequest.Builder().build()

}