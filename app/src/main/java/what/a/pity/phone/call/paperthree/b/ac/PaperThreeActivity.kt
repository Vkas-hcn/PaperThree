package what.a.pity.phone.call.paperthree.b.ac

import android.content.Intent
import androidx.activity.OnBackPressedCallback
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.d.ae.fb.FireBaseAD
import what.a.pity.phone.call.paperthree.d.ae.fb.PaperAppFireBaseUtils
import what.a.pity.phone.call.paperthree.databinding.SsssssssssBinding

class PaperThreeActivity : BaseActivity<SsssssssssBinding>() {

    override var viewID: Int = R.layout.ssssssssss

    var job: Job? = null
    var job2: Job? = null

    override fun initV() {
        PaperThreeConstant.canRefreshHomeNative = true
        beforeA()
    }

    private fun beforeA() {
        if (BIBIUBADDDDUtils.canShowAD()) {
            job = AppInitUtils().countDown(100, 100, MainScope(), {
                mBinding.clakemcpb.progress = it
                if (it > 10)
                    BIBIUBADDDDUtils.showOpenAdIfCan(this@PaperThreeActivity)
            }, {
                startActivity(Intent(this@PaperThreeActivity, MainActivity::class.java))
                finish()
            })
            checkAD()
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


}