package what.a.pity.phone.call.paperthree.b.ac

import android.content.Intent
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import what.a.pity.phone.call.paperthree.BuildConfig
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.d.ae.fb.FireBaseAD
import what.a.pity.phone.call.paperthree.d.ae.fb.PaperAppFireBaseUtils
import what.a.pity.phone.call.paperthree.databinding.SsssssssssBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils

class PaperThreeActivity : BaseActivity<SsssssssssBinding>() {

    override var viewID: Int = R.layout.ssssssssss

    var job: Job? = null
    var job2: Job? = null
    private var jobStartWall: Job? = null
    private lateinit var consentInformation: ConsentInformation

    override fun initV() {
//        updateUserOpinions()
        GetWallDataUtils.getBlackData(this)
        PaperThreeConstant.canRefreshHomeNative = true
        PaperThreeConstant.canRefreshHomeNative2 = true
        checkAD()
        beforeA()
    }

    private fun beforeA() {
//        if(!SPUtils.getInstance().getBoolean(KeyData.ad_user_state)){
//            return
//        }
        if (BIBIUBADDDDUtils.canShowAD()) {
            job = AppInitUtils().countDown(100, 100, MainScope(), {
                mBinding.clakemcpb.progress = it
                if (it > 10)
                    BIBIUBADDDDUtils.showOpenAdIfCan(this@PaperThreeActivity)
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
            AppInitUtils().toMain(this)
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
                .addTestDeviceHashedId("AC2561437987A1BF036B1ADB0A89BDB4")
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