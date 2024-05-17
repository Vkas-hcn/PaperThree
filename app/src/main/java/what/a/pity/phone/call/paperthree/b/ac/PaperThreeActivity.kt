package what.a.pity.phone.call.paperthree.b.ac

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.d.ae.fb.FireBaseAD
import what.a.pity.phone.call.paperthree.d.ae.fb.PaperAppFireBaseUtils
import what.a.pity.phone.call.paperthree.databinding.SsssssssssBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.light.GifWallpaperService
import what.a.pity.phone.call.paperthree.fast.light.LightBroadcast
import what.a.pity.phone.call.paperthree.fast.light.LightService
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils
import java.io.IOException


class PaperThreeActivity : BaseActivity<SsssssssssBinding>() {

    override var viewID: Int = R.layout.ssssssssss

    var job: Job? = null
    var job2: Job? = null
    private lateinit var consentInformation: ConsentInformation

    override fun initV() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE

        window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        updateUserOpinions()
        GetWallDataUtils.getBlackData(this)
        PaperThreeConstant.canRefreshHomeNative = true
        PaperThreeConstant.canRefreshHomeNative2 = true
        PaperThreeConstant.canRefreshEndNative = true
        checkAD()
        beforeA()
        WallNetDataUtils.getSessionList(this)
        WallNetDataUtils.postPotIntData(this, "wa1ll")
        startServiceAndBroadcast()
    }

    private fun startServiceAndBroadcast() {
        val innerReceiver = LightBroadcast()
        val intentFilter = IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        registerReceiver(innerReceiver, intentFilter)
        val intentOne = Intent(this, LightService::class.java)
        startService(intentOne)
    }

    private fun beforeA() {
        if (!SPUtils.getInstance().getBoolean(KeyData.ad_user_state)) {
            return
        }
        if (GetWallDataUtils.isOrganic() && !SPUtils.getInstance().getBoolean(KeyData.openFirst,false)) {
            WallNetDataUtils.postPotIntData(this, "wa28ll")
            Log.d("TAG", "The ref is Organic not show")
            SPUtils.getInstance().put(KeyData.openFirst, true)
            AppInitUtils().isMainOrGuide(this)
            return
        }
        if (BIBIUBADDDDUtils.canShowAD()) {
            job = AppInitUtils().countDown(100, 100, MainScope(), {
                mBinding.clakemcpb.progress = it
                if (it > 10)
                    BIBIUBADDDDUtils.showOpenAdIfCan(this@PaperThreeActivity) {
                        AppInitUtils().isMainOrGuide(this)
                    }
            }, {
                startActivity(Intent(this@PaperThreeActivity, MainActivity::class.java))
                finish()
            })
        } else {
            LogUtils.e()
            aaaa()
        }
    }

    private fun checkAD() {
        if (PaperAppFireBaseUtils.isAppInit) {
            LogUtils.e()
            PaperAppFireBaseUtils.isAppInit = false
            job2 = AppInitUtils().countDown(8, 500, MainScope(), {
                FireBaseAD.checkADData {
                    if (it) {
                        preLoadAD()
                        job2?.cancel()
                    }
                }
            }, {
                FireBaseAD.checkADDataWithServer()
                preLoadAD()
            })
        } else {
            preLoadAD()
        }
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
        job = null
    }

    private fun preLoadAD() {
        BIBIUBADDDDUtils.startOpenBOIBOIUBU.preload(this)
        BIBIUBADDDDUtils.mainNativeBOUVIY.preload(this)
        BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.preload(this)
        BIBIUBADDDDUtils.mainNativeBOUVIY2.preload(this)
        BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.preload(this)
        BIBIUBADDDDUtils.rewAd.preload(this)
        if (KeyData.checkTheType.isBlank()) {
            BIBIUBADDDDUtils.intIntroduce.preload(this)
            BIBIUBADDDDUtils.intType.preload(this)
        }
    }

    override fun initL() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })
    }

    private fun aaaa() {
        AppInitUtils().countDown(100, 20, MainScope(), {
            mBinding.clakemcpb.progress = it
        }, {
            AppInitUtils().isMainOrGuide(this)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        job = null
        job2?.cancel()
        job2 = null
    }


    private fun updateUserOpinions() {
        val data = SPUtils.getInstance().getBoolean(KeyData.ad_user_state)
        if (data) {
            return
        }

        val debugSettings =
            ConsentDebugSettings.Builder(this)
                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
                .addTestDeviceHashedId("FB2D8129B0FE1EB562D7FE7C0B2F4013")
                .build()
        val params = ConsentRequestParameters
            .Builder()
            .setConsentDebugSettings(debugSettings)
            .build()
        consentInformation = UserMessagingPlatform.getConsentInformation(this)
        consentInformation.requestConsentInfoUpdate(
            this,
            params, {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(this) { loadAndShowError ->
                    Log.e("TAG", "updateUserOpinions1: $loadAndShowError")
                    if (consentInformation.canRequestAds()) {
                        SPUtils.getInstance().put(KeyData.ad_user_state, true)
                        beforeA()
                    }
                }
            },
            {
                Log.e("TAG", "updateUserOpinions2: $it")
                SPUtils.getInstance().put(KeyData.ad_user_state, true)
                beforeA()
            }
        )
    }

}