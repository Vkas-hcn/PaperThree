package what.a.pity.phone.call.paperthree.d.ad.baseeeee

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
            "WallPaper AD",
            " NativeAd, id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} start preload"
        )
        AdLoader.Builder(context, item.adIdKKKK).apply {
            forNativeAd { ad ->
                nativeAd = ad
                onAdLoaded.invoke()
            }
            withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(e: LoadAdError) {
                    onAdLoadFailed.invoke(e.message)
                }

                override fun onAdClicked() = BIBIUBADDDDUtils.countAD(s = false, c = true)
            })
            withNativeAdOptions(NativeAdOptions.Builder().apply {
                setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
            }.build())

        }.build().loadAd(adRequest)
    }

    override fun showMyNameIsHei(activity: Activity, nativeParent: ViewGroup?, onAdDismissed: () -> Unit) {
        if (null == nativeAd) return
        LogUtils.e(
            "WallPaper AD",
            " NativeAd, id: ${item.adIdKKKK}, adweight: ${item.adWeightHAHHA} SUS show"
        )
        val nativeAdView = LayoutInflater.from(activity)
            .inflate(R.layout.jajjaj, nativeParent, false) as NativeAdView
        nativeAdView.mediaView = nativeAdView.findViewById(R.id.mlskmclskdc_cover)
        nativeAdView.mediaView?.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
        nativeAdView.headlineView = nativeAdView.findViewById(R.id.mlskmclskdc_app_name)
        nativeAdView.callToActionView = nativeAdView.findViewById(R.id.mlskmclskdc_action)
        nativeAdView.iconView = nativeAdView.findViewById(R.id.mlskmclskdc_app_icon)
        nativeAdView.bodyView = nativeAdView.findViewById(R.id.mlskmclskdc_app_content)


        nativeAd?.mediaContent?.let { nativeAdView.mediaView?.setMediaContent(it) }
        (nativeAdView.headlineView as? TextView)?.text = nativeAd?.headline ?: ""
        (nativeAdView.iconView as? ImageView)?.setImageDrawable(nativeAd?.icon?.drawable)
        (nativeAdView.callToActionView as? Button)?.text = nativeAd?.callToAction
        (nativeAdView.bodyView as? TextView)?.text = nativeAd?.body


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