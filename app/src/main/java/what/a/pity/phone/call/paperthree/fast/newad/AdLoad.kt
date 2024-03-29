package what.a.pity.phone.call.paperthree.fast.newad


import android.app.Application
import android.view.View
import androidx.appcompat.app.AppCompatActivity


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import com.blankj.utilcode.util.ActivityUtils
import com.google.android.gms.ads.*
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView

import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.AdvertiseEntity
import what.a.pity.phone.call.paperthree.fast.KeyData

//object AdLoad {
//
//    fun loadOf(where: String, isLoad: Boolean = false) {
//        Load.of(where)?.load(isLoadType = isLoad)
//    }
//
//    fun resultOf(where: String): Any? {
//        return Load.of(where)?.res
//    }
//
//    fun showFullScreenOf(
//        where: String,
//        context: AppCompatActivity,
//        res: Any,
//        preload: Boolean = false,
//        onShowCompleted: () -> Unit
//    ) {
//        Show.of(where)
//            .showFullScreen(
//                context = context,
//                res = res,
//                callback = {
//                    Load.of(where)?.let { load ->
//                        load.clearCache()
//                        if (preload) {
//                            load.load()
//                        }
//                    }
//                    onShowCompleted()
//                }
//            )
//    }
//
//    fun showNativeOf(
//        where: String,
//        nativeRoot: ViewGroup,
//        res: Any,
//        preload: Boolean = false,
//        onShowCompleted: (() -> Unit)? = null
//    ) {
//        Show.of(where)
//            .showNativeOf(
//                nativeRoot = nativeRoot,
//                res = res,
//                callback = {
//                    Load.of(where)?.let { load ->
//                        load.clearCache()
//                        if (preload) {
//                            load.load()
//                        }
//                    }
//                    onShowCompleted?.invoke()
//                }
//            )
//    }
//
//    fun loadAllAd() {
//        runCatching {
//            Log.e(logTagWpt, "重新加载广告")
//            Load.of(KeyData.ad_connect)?.load()
//
//            Load.of(KeyData.ad_result)?.load()
//
//            Load.of(KeyData.ad_home)?.load()
//
//            Load.of(KeyData.ad_back)?.load()
//        }
//    }
//
//    private class Load private constructor(private val where: String) {
//        companion object {
//            private val open by lazy { Load(KeyData.ad_open) }
//            private val home by lazy { Load(KeyData.ad_home) }
//            private val connect by lazy { Load(KeyData.ad_connect) }
//            private val back by lazy { Load(KeyData.ad_back) }
//            private val result by lazy { Load(KeyData.ad_result) }
//
//            fun of(where: String): Load? {
//                return when (where) {
//                    KeyData.ad_open -> open
//                    KeyData.ad_home -> home
//                    KeyData.ad_connect -> connect
//                    KeyData.ad_back -> back
//                    KeyData.ad_result -> result
//                    else -> null
//                }
//            }
//
//        }
//
//
//        private var createdTime = 0L
//        var res: Any? = null
//            set
//        var isLoading = false
//            set
//
//
//        fun load(
//            context: Context = PaperThreeApp.instance,
//            requestCount: Int = 1,
//            inst: AdvertiseEntity = GreenUtils.getLocalAdData(),
//            isLoadType: Boolean = false
//        ) {
//            App.isAppGreenSameDayGreen()
//
//            if (isLoading) {
//                Log.d(logTagWpt, "${where},正在请求中")
//                return
//            }
//
//            val cache = res
//            val cacheTime = createdTime
//            if (cache != null && cache != "") {
//                if (cacheTime > 0L
//                    && ((System.currentTimeMillis() - cacheTime) > (1000L * 60L * 60L))
//                ) {
//                    Log.d(logTagWpt, "${where},缓存已过期")
//                    clearCache()
//                } else {
//                    Log.d(logTagWpt, "${where},现有缓存")
//                    return
//                }
//            }
//            if ((cache == null || cache == "") && GreenUtils.isThresholdReached()) {
//                Log.d(logTagWpt, "广告达到上线")
//                res = ""
//                return
//            }
//            if (!isLoadType && where == KeyData.ad_back && GreenUtils.isBuyQuantityBanGreen()) {
//                Log.e(logTagWpt, "买量屏蔽用户不加载${where}广告")
//                return
//            }
////            if (!isLoadType && (where == KeyData.ad_back || where == KeyData.ad_connect) && GreenUtils.whetherBlackListBan()) {
////                Log.e(logTagWpt, "黑名单用户不加载${where}广告")
////                res = ""
////                return
////            }
//            isLoading = true
//            Log.d(logTagWpt, "${where},加载启动")
//            doRequest(
//                context, when (where) {
//                    KeyData.ad_open -> inst.lif_sniff
//                    KeyData.ad_connect -> inst.lif_impri
//                    KeyData.ad_home -> inst.lif_wind
//                    KeyData.ad_result -> inst.lif_spread
//                    KeyData.ad_back -> inst.lif_lude
//                    else -> emptyList()
//                }.sortedByDescending { it.lif_suffer }
//            ) {
//                val isSuccessful = it != null
//                Log.d(logTagWpt, "${where},加载完成, 结果=$isSuccessful")
//                if (isSuccessful) {
//                    res = it
//                    createdTime = System.currentTimeMillis()
//                }
//                isLoading = false
//                if (!isSuccessful && where == KeyData.ad_open && requestCount < 2) {
//                    load(context, requestCount + 1, inst)
//                }
//            }
//        }
//
//        private fun doRequest(
//            context: Context,
//            units: List<GreenAdDetailBean>,
//            startIndex: Int = 0,
//            callback: ((result: Any?) -> Unit)
//        ) {
//            val unit = units.getOrNull(startIndex)
//            if (unit == null) {
//                callback(null)
//                return
//            }
//            Log.d(logTagWpt, "${where},on request: $unit")
//            GoogleAds(where).load(context, unit) {
//                if (it == null)
//                    doRequest(context, units, startIndex + 1, callback)
//                else
//                    callback(it)
//            }
//        }
//
//        fun clearCache() {
//            res = null
//            createdTime = 0L
//        }
//    }
//
//    private class Show private constructor(private val where: String) {
//        companion object {
//            private var isShowingFullScreen = false
//
//            fun of(where: String): Show {
//                return Show(where)
//            }
//
//            fun finishAdActivity() {
//                GoogleAds.finishAdActivity()
//            }
//        }
//
//        fun showFullScreen(
//            context: AppCompatActivity,
//            res: Any,
//            callback: () -> Unit
//        ) {
//            if (isShowingFullScreen || context.lifecycle.currentState != Lifecycle.State.RESUMED) {
////                callback()
//                return
//            }
//            isShowingFullScreen = true
//            GoogleAds(where)
//                .showFullScreen(
//                    context = context,
//                    res = res,
//                    callback = {
//                        isShowingFullScreen = false
//                        callback()
//                    }
//                )
//        }
//
//        fun showNativeOf(
//            nativeRoot: ViewGroup,
//            res: Any,
//            callback: () -> Unit
//        ) {
//            GoogleAds(where)
//                .showNativeOf(
//                    nativeRoot = nativeRoot,
//                    res = res,
//                    callback = callback
//                )
//        }
//    }
//
//    private class GoogleAds(private val where: String) {
//        private class GoogleFullScreenCallback(
//            private val where: String,
//            private val callback: () -> Unit
//        ) : FullScreenContentCallback() {
//            override fun onAdDismissedFullScreenContent() {
//                Log.d(logTagWpt, "${where}--dismissed")
//                onAdComplete()
//            }
//
//            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
//                Log.d(logTagWpt, "${where}--fail to show, message=${p0.message}]")
//                onAdComplete()
//            }
//
//            private fun onAdComplete() {
//                callback()
//            }
//
//            override fun onAdShowedFullScreenContent() {
//                GreenUtils.recordNumberOfAdDisplaysGreen()
//                Log.d(logTagWpt, "${where}--showed")
//
//            }
//
//            override fun onAdClicked() {
//                super.onAdClicked()
//                Log.d(logTagWpt, "${where}插屏广告点击")
//                GreenUtils.recordNumberOfAdClickGreen()
//            }
//        }
//
//        companion object {
//            fun init(context: Context, onInitialized: () -> Unit) {
//                MobileAds.initialize(context) {
//                    onInitialized()
//                }
//            }
//
//            fun finishAdActivity() {
//                ActivityUtils.finishActivity(AdActivity::class.java)
//            }
//        }
//
//        fun load(
//            context: Context,
//            unit: GreenAdDetailBean,
//            callback: ((result: Any?) -> Unit)
//        ) {
//            val requestContext = context.applicationContext
//            when (unit.lif_copy) {
//                KeyData.open -> {
//                    AppOpenAd.load(
//                        requestContext,
//                        unit.lif_reuse,
//                        AdRequest.Builder().build(),
//                        AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
//                        object :
//                            AppOpenAd.AppOpenAdLoadCallback() {
//                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
//                                Log.d(logTagWpt, "${where},request fail: ${loadAdError.message}")
//                                callback(null)
//                            }
//
//                            override fun onAdLoaded(appOpenAd: AppOpenAd) {
//                                appOpenAd.setOnPaidEventListener {
//                                    GreenUtils.putPointAdValueGreen(it.valueMicros,context)
//                                }
//                                callback(appOpenAd)
//                            }
//                        })
//                }
//                KeyData.prohibit -> {
//                    if (where == KeyData.ad_connect && App.isConnect) {
//                        GreenUtils.putPointGreen("lif_detec")
//                    }
//                    InterstitialAd.load(
//                        requestContext,
//                        unit.lif_reuse,
//                        AdRequest.Builder().build(),
//                        object : InterstitialAdLoadCallback() {
//                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
//                                Log.d(logTagWpt, "${where},request fail: ${loadAdError.message}")
//                                callback(null)
//                            }
//
//                            override fun onAdLoaded(interstitialAd: InterstitialAd) {
//                                interstitialAd.setOnPaidEventListener {
//                                    GreenUtils.putPointAdValueGreen(it.valueMicros,context)
//                                }
//                                callback(interstitialAd)
//                            }
//                        }
//                    )
//                }
//                KeyData.worship -> {
//                    if (where == KeyData.ad_result && App.isConnect) {
//                        GreenUtils.putPointGreen("lif_pass")
//                    }
//                    AdLoader.Builder(requestContext, unit.lif_reuse)
//                        .forNativeAd {
//                            it.setOnPaidEventListener { adValue ->
//                                loadOf(where)
//                                GreenUtils.putPointAdValueGreen(adValue.valueMicros,context)
//                            }
//                            callback(it)
//                        }
//                        .withAdListener(object : AdListener() {
//                            override fun onAdOpened() {
//                                super.onAdOpened()
//                                Log.e(logTagWpt, "${where}-原生广告点击")
//
//                                GreenUtils.recordNumberOfAdClickGreen()
//                            }
//
//                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
//                                Log.d(logTagWpt, "${where},request fail: ${loadAdError.message}")
//                                callback(null)
//                            }
//
//                            override fun onAdLoaded() {
//                                super.onAdLoaded()
//
//                            }
//                        })
//                        .withNativeAdOptions(
//                            NativeAdOptions.Builder()
//                                .setAdChoicesPlacement(
//                                    when (where) {
//                                        KeyData.ad_home -> NativeAdOptions.ADCHOICES_TOP_RIGHT
//                                        KeyData.ad_result -> NativeAdOptions.ADCHOICES_TOP_LEFT
//                                        else -> NativeAdOptions.ADCHOICES_BOTTOM_LEFT
//                                    }
//                                )
//                                .build()
//                        )
//                        .build()
//                        .loadAd(AdRequest.Builder().build())
//                }
//                else -> {
//                    callback(null)
//                }
//            }
//        }
//
//        fun showFullScreen(
//            context: AppCompatActivity,
//            res: Any,
//            callback: () -> Unit
//        ) {
//            when (res) {
//                is AppOpenAd -> {
//                    res.fullScreenContentCallback = GoogleFullScreenCallback(where, callback)
//                    res.show(context)
//                }
//                is InterstitialAd -> {
//                    if (where == KeyData.ad_back) {
//                        if (GreenUtils.isBuyQuantityBanGreen()) {
//                            Log.e(logTagWpt, "根据买量屏蔽插屏广告。。。")
//                            callback.invoke()
//                            return
//                        }
//                    }
//                    res.fullScreenContentCallback = GoogleFullScreenCallback(where, callback)
//                    res.show(context)
//
//                }
//                else -> callback()
//            }
//        }
//
//        fun showNativeOf(
//            nativeRoot: ViewGroup,
//            res: Any,
//            callback: () -> Unit
//        ) {
//            val imgOc = nativeRoot.findViewById<View>(R.id.img_green_ad_frame)
//            val nativeAd = res as? NativeAd ?: return
//            imgOc?.visibility = View.GONE
//            val ogAdFrame = nativeRoot.findViewById<ViewGroup>(R.id.green_ad_frame)
//            ogAdFrame?.visibility = View.VISIBLE
//            val adView = if (where == KeyData.ad_home) {
//                LayoutInflater.from(nativeRoot.context)
//                    .inflate(
//                        R.layout.layout_home_ad,
//                        nativeRoot.findViewById(R.id.green_ad_frame),
//                        false
//                    ) as NativeAdView
//            } else {
//                LayoutInflater.from(nativeRoot.context)
//                    .inflate(
//                        R.layout.layout_result_ad,
//                        nativeRoot.findViewById(R.id.green_ad_frame),
//                        false
//                    ) as NativeAdView
//            }
//            ogAdFrame.apply {
//                removeAllViews()
//                addView(adView)
//            }
//            adView.findViewById<MediaView>(R.id.ad_media)?.let { mediaView ->
//                adView.mediaView = mediaView
//                nativeAd.mediaContent?.let { mediaContent ->
//                    mediaView.setMediaContent(mediaContent)
//                    mediaView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//                }
//            }
//
//            adView.findViewById<TextView>(R.id.ad_headline)?.let { titleView ->
//                adView.headlineView = titleView
//                titleView.text = nativeAd.headline
//            }
//
//            adView.findViewById<TextView>(R.id.ad_body)?.let { bodyView ->
//                adView.bodyView = bodyView
//                bodyView.text = nativeAd.body
//            }
//
//            adView.findViewById<TextView>(R.id.ad_call_to_action)?.let { actionView ->
//                adView.callToActionView = actionView
//                actionView.text = nativeAd.callToAction
//            }
//
//            adView.findViewById<ImageView>(R.id.ad_app_icon)?.let { iconView ->
//                adView.iconView = iconView
//                iconView.setImageDrawable(nativeAd.icon?.drawable)
//            }
//
//            adView.setNativeAd(nativeAd)
//            GreenUtils.recordNumberOfAdDisplaysGreen()
//            Log.d(logTagWpt, "${where}--showed")
//            callback()
//        }
//    }
//}