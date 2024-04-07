package what.a.pity.phone.call.paperthree.fast.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.webkit.WebSettings
import androidx.core.os.bundleOf
import com.android.installreferrer.api.ReferrerDetails
import com.blankj.utilcode.util.SPUtils
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.ResponseInfo
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import org.json.JSONObject
import what.a.pity.phone.call.paperthree.BuildConfig
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.EveryADBean
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.KeyData.tba_wall_url
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils.postWallPaperData
import java.util.Currency
import java.util.Locale
import java.util.UUID

object WallNetDataUtils {

    private fun getTopLevelJsonData(context: Context): JSONObject {
        val jsonData = JSONObject()
        val henchmen = JSONObject()
        val shanty = JSONObject()
        val gasoline = JSONObject()
        val andean = JSONObject()
        henchmen.apply {
            //android_id
            put(
                "nausea",
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            )
            //operator
            put(
                "sherbet",
                ""
            )
        }
        shanty.apply {
            //os_version
            put("wop", Build.VERSION.RELEASE)
            //log_id
            put("patton", UUID.randomUUID().toString())
            //manufacturer
            put("krishna", Build.MANUFACTURER.toLowerCase())
        }

        gasoline.apply {
            //client_ts
            put("drunk", System.currentTimeMillis())
            //device_model
            put("tolstoy", Build.MODEL)
            //distinct_id
            put("merrill", SPUtils.getInstance().getString(KeyData.phone_uuid))
        }

        andean.apply {
            //system_language
            put(
                "menorca",
                "${Locale.getDefault().language}_${Locale.getDefault().country}"
            )
            //app_version
            put(
                "chignon",
                context.packageManager.getPackageInfo(context.packageName, 0).versionName
            )
            //bundle_id
            put("snorkel", "com.khdwallpaper.amazing")
            //gaid
            put(
                "soldiery", SPUtils.getInstance().getString(KeyData.gidDataWall, "")
            )
            //os
            put("medicate", "crest")
        }

        jsonData.apply {
            put("henchmen", henchmen)
            put("shanty", shanty)
            put("gasoline", gasoline)
            put("andean", andean)
        }
        return jsonData
    }

    fun getSessionJsonData(context: Context): String {
        return getTopLevelJsonData(context).apply {
            put("seagram", JSONObject().apply { })
        }.toString()
    }

    fun getInstallJsonData(context: Context, rd: ReferrerDetails): String {
        return getTopLevelJsonData(context).apply {
            //build
            put("finitude", "build/${Build.ID}")

            //referrer_url
            put("sachs", rd.installReferrer)

            //install_version
            put("toll", rd.installVersion)

            //user_agent
            put("biotite", getWebDefaultUserAgent(context))

            //lat
            put("jacky", getLimitTracking(context))

            //referrer_click_timestamp_seconds
            put("vicinity", rd.referrerClickTimestampSeconds)

            //install_begin_timestamp_seconds
            put("baseline", rd.installBeginTimestampSeconds)

            //referrer_click_timestamp_server_seconds
            put("flout", rd.referrerClickTimestampServerSeconds)

            //install_begin_timestamp_server_seconds
            put("slate", rd.installBeginTimestampServerSeconds)

            //install_first_seconds
            put("canary", getFirstInstallTime(context))

            //last_update_seconds
            put("socratic", getLastUpdateTime(context))
            put("poultry", "propos")
        }.toString()
    }

    private fun getAdJsonData(
        context: Context, adValue: AdValue,
        responseInfo: ResponseInfo,
        aDBean: EveryADBean
    ): String {
        val poultry = getTopLevelJsonData(context)
        //ad_pre_ecpm
        poultry.put("quitter", adValue.valueMicros)
        //currency
        poultry.put("haul", adValue.currencyCode)
        //ad_network
        poultry.put(
            "item",
            responseInfo.mediationAdapterClassName
        )
        //ad_source
        poultry.put("imagen", "admob")
        //ad_code_id
        poultry.put("rest", aDBean.adIdKKKK)
        //ad_pos_id
        poultry.put("swanky", aDBean.where)
        //ad_format
        poultry.put("salvo", aDBean.where?.let { getAdType(it) })
        //precision_type
        poultry.put("glycogen", getPrecisionType(adValue.precisionType))
        //ad_load_ip
        poultry.put("homonym", "")
        //ad_impression_ip
        poultry.put("gustavus", "")
        poultry.put("poultry", "postal")
        return poultry.toString()
    }

    private fun getTbaDataJson(context: Context, name: String): String {
        return getTopLevelJsonData(context).apply {
            put("poultry", name)
        }.toString()
    }

    private fun getTbaTimeDataJson(
        context: Context,
        name: String,
        parameterName: String,
        time: String?,
        ): String {
        val data = JSONObject()
        data.put(parameterName, time)
        return getTopLevelJsonData(context).apply {
            put("poultry", name)
            put("${parameterName}@verify",time)
        }.toString()
    }

    private fun putPointAdKK(adValue: Long) {
        if (!BuildConfig.DEBUG) {
            AppEventsLogger.newLogger(PaperThreeApp.instance).logPurchase(
                (adValue / 1000000.0).toBigDecimal(), Currency.getInstance("USD")
            )
        }
    }

    private fun getWebDefaultUserAgent(context: Context): String {
        return try {
            WebSettings.getDefaultUserAgent(context)
        } catch (e: Exception) {
            ""
        }
    }

    private fun getLimitTracking(context: Context): String {
        return try {
            if (AdvertisingIdClient.getAdvertisingIdInfo(context).isLimitAdTrackingEnabled) {
                "flunk"
            } else {
                "kinky"
            }
        } catch (e: Exception) {
            "kinky"
        }
    }

    private fun getFirstInstallTime(context: Context): Long {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.firstInstallTime / 1000
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 0
    }

    private fun getLastUpdateTime(context: Context): Long {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.lastUpdateTime / 1000
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 0
    }

    fun getGid(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            runCatching {
                SPUtils.getInstance().put(
                    KeyData.gidDataWall,
                    AdvertisingIdClient.getAdvertisingIdInfo(context).id ?: ""
                )
            }
        }
    }

    private fun getAdType(type: String): String {
        if (type == "fa_c_ongre") {
            return "Open"
        }
        if (type == "fa_c_pach" || type == "fa_c_pre") {
            return "Native"
        }
        if (type == "fa_c_osur" || type == "fa_c_inter") {
            return "Interstitial"
        }
        return ""
    }

    private fun getPrecisionType(precisionType: Int): String {
        return when (precisionType) {
            0 -> {
                "UNKNOWN"
            }

            1 -> {
                "ESTIMATED"
            }

            2 -> {
                "PUBLISHER_PROVIDED"
            }

            3 -> {
                "PRECISE"
            }

            else -> {
                "UNKNOWN"
            }
        }
    }

    fun getSessionList(context: Context) {
        val data = getSessionJsonData(context)
        Log.e("TAG", "session-data=$data")
        try {
            postWallPaperData(tba_wall_url, data, object : GetWallDataUtils.Callback {
                override fun onSuccess(response: String) {
                    Log.e("TAG", "session-up=success:${response}")
                }

                override fun onFailure(error: String) {
                    Log.e("TAG", "session-up=onFailure:${error}")
                }
            })
        } catch (e: Exception) {
        }
    }

    fun getInstallList(context: Context, rd: ReferrerDetails) {
        val data = getInstallJsonData(context, rd)
        try {
            postWallPaperData(tba_wall_url, data, object : GetWallDataUtils.Callback {
                override fun onSuccess(response: String) {
                    SPUtils.getInstance().put(KeyData.haveWallInstall, true)
                }

                override fun onFailure(error: String) {
                    SPUtils.getInstance().put(KeyData.haveWallInstall, false)
                }
            })
        } catch (e: Exception) {
            SPUtils.getInstance().put(KeyData.haveWallInstall, false)
        }
    }

    fun getAdList(
        context: Context,
        adValue: AdValue,
        responseInfo: ResponseInfo,
        everyADBean: EveryADBean
    ) {
        val json = getAdJsonData(context, adValue, responseInfo, everyADBean)
        Log.e("TAG", "${everyADBean.where}-广告上传-${json}")
        try {
            postWallPaperData(tba_wall_url, json, object : GetWallDataUtils.Callback {
                override fun onSuccess(response: String) {
                    Log.e("TAG", "${everyADBean.where}-广告上传成功-${response}")
                }

                override fun onFailure(error: String) {
                    Log.e("TAG", "${everyADBean.where}-广告上传失败-${error}")
                }
            })
        } catch (e: Exception) {

        }
        putPointAdKK(adValue.valueMicros)
    }

    fun postPotIntData(context: Context, name: String, key: String? = null, time: String? =null) {
        val data = if (key != null) {
            putPointTimeGreen(name, time)
            getTbaTimeDataJson(context, name, key, time)
        } else {
            putPointGreen(name)
            getTbaDataJson(context, name)
        }
        Log.e("TAG", "postPotIntData--${name}: data=${data}")
        try {
            postWallPaperData(
                tba_wall_url,
                data,
                object : GetWallDataUtils.Callback {
                    override fun onSuccess(response: String) {
                        Log.e("TAG", "postPotIntData--${name}: onSuccess=${response}")
                    }

                    override fun onFailure(error: String) {
                        Log.e("TAG", "postPotIntData--${name}: onFailure=${error}")
                    }
                })
        } catch (e: Exception) {
            Log.e("TAG", "postPotIntData--${name}: Exception=${e}")
        }

    }

    private fun putPointGreen(name: String) {
        if (!BuildConfig.DEBUG) {
            Firebase.analytics.logEvent(name, null)
        }
    }

    private fun putPointTimeGreen(name: String, time: String?) {
        if (!BuildConfig.DEBUG) {
            Firebase.analytics.logEvent(name, bundleOf("fa" to time))
        }
    }
}