package what.a.pity.phone.call.paperthree.fast.utils

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.blankj.utilcode.util.SPUtils
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import okhttp3.CacheControl
import okhttp3.Call
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.IOException
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.AdBlockingBean
import what.a.pity.phone.call.paperthree.fast.KeyData
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object GetWallDataUtils {
    private fun decodeBase64(str: String): String {
        return String(android.util.Base64.decode(str, android.util.Base64.DEFAULT))
    }

    fun getLocalBlockingData(): AdBlockingBean {
        val listType = object : TypeToken<AdBlockingBean>() {}.type
        return runCatching {
            Gson().fromJson<AdBlockingBean>(
                decodeBase64(
                    SPUtils.getInstance()
                        .getString(
                            KeyData.ad_blocking_key
                        )
                ),
                listType
            )
        }.getOrNull() ?: Gson().fromJson(
            PaperThreeConstant.adBlockingData,
            object : TypeToken<AdBlockingBean?>() {}.type
        )
    }

    private fun getRefTypeData(): String {
        val refOnline = SPUtils.getInstance().getString(KeyData.ref_online)
        if (refOnline.isBlank()) {
            return PaperThreeConstant.ref_load_data
        } else {
            return String(android.util.Base64.decode(refOnline, android.util.Base64.DEFAULT))
        }
    }

    fun getReferInformation(context: Context) {
        GlobalScope.launch {
            while (isActive) {
                val refData = SPUtils.getInstance().getString(KeyData.phone_ref)
                if (refData.isEmpty()) {
                    getReferrerData(context)
                } else {
                    cancel()
                    break
                }
                delay(5000)
            }
        }
    }

    private fun getReferrerData(context: Context) {
        var installReferrer = ""
        val referrer = SPUtils.getInstance().getString(KeyData.phone_ref)

        if (referrer.isNotBlank()) {
            return
        }
//        installReferrer = "facebook"
        installReferrer = "utm_source=google-play&utm_medium=organic"
        SPUtils.getInstance().put(KeyData.phone_ref, installReferrer)
        runCatching {
            val referrerClient = InstallReferrerClient.newBuilder(context).build()
            referrerClient.startConnection(object : InstallReferrerStateListener {
                override fun onInstallReferrerSetupFinished(p0: Int) {
                    when (p0) {
                        InstallReferrerClient.InstallReferrerResponse.OK -> {
                            val installReferrer =
                                referrerClient.installReferrer.installReferrer ?: ""
//                            SPUtils.getInstance().put(KeyData.phone_ref, installReferrer)
                            if (!SPUtils.getInstance().getBoolean(KeyData.haveWallInstall)) {
                                runCatching {
                                    installReferrer?.run {
                                        WallNetDataUtils.getInstallList(
                                            context,
                                            referrerClient.installReferrer
                                        )
                                    }
                                }.exceptionOrNull()
                            }
                        }
                    }
                    referrerClient.endConnection()
                }

                override fun onInstallReferrerServiceDisconnected() {
                }
            })
        }.onFailure { e ->
        }
    }

    private fun findOnlineRefList(inputString: String, refData: String): Boolean {
        val stringArray = inputString.split("&").toTypedArray()
        stringArray.forEach {
            if (refData.contains(it, true)) {
                return true
            }
        }
        return false
    }

    private fun isFacebookUser(): Boolean {
        val refData = SPUtils.getInstance().getString(KeyData.phone_ref)
        val pattern = getRefTypeData()
        if (refData.isBlank()) {
            return false
        }
        return findOnlineRefList(pattern, refData)
    }


    //屏蔽广告用户
    fun showAdCenter(): Boolean {
        return when (getLocalBlockingData().tsdesk) {
            "1" -> {
                true
            }

            "2" -> {
                isFacebookUser()
            }

            "3" -> {
                false
            }

            else -> {
                true
            }
        }
    }

    //黑名单
    fun showAdBlacklist(): Boolean {
        val blackData = SPUtils.getInstance().getString(KeyData.phone_black) != "tenure"
        return when (getLocalBlockingData().quoswer) {
            "1" -> {
                !blackData
            }

            "2" -> {
                true
            }

            else -> {
                true
            }
        }
    }


    fun getBlackData(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            getGid(context)
            val data = SPUtils.getInstance().getString(KeyData.phone_black)
            if (data.isEmpty()) {
                val params = blackData(context)
                try {
                    getMapRequest(
                        "https://cummings.khdwallpaper.com/lookup/granule",
                        params,
                        object : Callback {
                            override fun onSuccess(response: String) {
                                Log.e("TAG", "getBlackData-onSuccess: $response")
                                SPUtils.getInstance().put(KeyData.phone_black, response)
                            }

                            override fun onFailure(error: String) {
                                Log.e("TAG", "getBlackData-onFailure: $error")
                                nextBlackFun(context)
                            }
                        })
                } catch (e: Exception) {
                    nextBlackFun(context)
                }
            }
        }
    }

    private fun nextBlackFun(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            delay(10000)
            getBlackData(context)
        }
    }

    @SuppressLint("HardwareIds")
    fun blackData(context: Context): Map<String, Any> {
        return mapOf<String, Any>(
            //distinct_id
            "merrill" to SPUtils.getInstance().getString(KeyData.phone_uuid),
            //client_ts
            "drunk" to (System.currentTimeMillis()),
            //device_model
            "tolstoy" to Build.MODEL,
            //bundle_id
            "snorkel" to ("com.khdwallpaper.amazing"),
            //os_version
            "wop" to Build.VERSION.RELEASE,
            //gaid
            "soldiery" to SPUtils.getInstance().getString(KeyData.phone_gidData),
            //android_id
            "nausea" to Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            ),
            //os
            "medicate" to "crest",
            //app_version
            "chignon" to (getAppVersion(context) ?: ""),
        )
    }

    fun getGid(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            runCatching {
                SPUtils.getInstance().put(
                    KeyData.phone_gidData,
                    AdvertisingIdClient.getAdvertisingIdInfo(context).id ?: ""
                )
            }
        }
    }

    private fun getAppVersion(context: Context): String? {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)

            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }


    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    interface Callback {
        fun onSuccess(response: String)
        fun onFailure(error: String)
    }

    fun getMapRequest(url: String, map: Map<String, Any>, callback: Callback) {
        val urlBuilder = url.toHttpUrl().newBuilder()
        map.forEach { entry ->
            urlBuilder.addEncodedQueryParameter(
                entry.key,
                URLEncoder.encode(entry.value.toString(), StandardCharsets.UTF_8.toString())
            )
        }
        val request = Request.Builder()
            .get()
            .tag(map)
            .url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (response.isSuccessful && responseBody != null) {
                    callback.onSuccess(responseBody)
                } else {
                    callback.onFailure(responseBody.toString())
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure("Network error")
            }
        })
    }

    fun postWallPaperData(url: String, body: String, callback: Callback) {
        val requestBody =
            RequestBody.create("application/json".toMediaTypeOrNull(), body)
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .tag(body)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (response.isSuccessful && responseBody != null) {
                    callback.onSuccess(responseBody)
                } else {
                    callback.onFailure(responseBody.toString())
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure("Network error:$e")
            }
        })
    }


    fun loadDrawableGif(context: Context, resourceId: Int, imageView: ImageView) {
        Glide.with(context)
            .asGif()
            .load(resourceId) // Drawable资源ID
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }

}