package what.a.pity.phone.call.paperthree.b.ac

import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.SPUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.SoWhatCanYouDo
import what.a.pity.phone.call.paperthree.databinding.EndLayoutBinding
import what.a.pity.phone.call.paperthree.databinding.PreviewLayoutBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils

class EndViewActivity : BaseActivity<EndLayoutBinding>() {

    override var viewID: Int
        get() = R.layout.end_layout
        set(value) {}
    private var curImg = R.mipmap.qiuqiu1
    private var baseAd: SoWhatCanYouDo? = null
    override fun initV() {
        PaperThreeConstant.canRefreshHomeNative = true
        BIBIUBADDDDUtils.mainNativeBOUVIY.preload(this)
        BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.preload(this)
        mBinding.tvEndState.text = if (PreViewActivity.isClickTypeDown == 2) {
            "Your Wallpaper Has Been Successfully Set."
        } else {
            "Your Wallpaper Has Been Successfully Download."
        }
    }

    override fun initL() {
        mBinding.actContinue.setOnClickListener {
            backToMainFun()
        }

        mBinding.pagerThreeBack.setOnClickListener {
            backToMainFun()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backToMainFun()
            }
        })
    }

    fun backToMainFun() {
        timeShowMainAd({
            smklllllcmnjiaecnuibbaiusybdcaiyvba()
        }, {
            jumpToMain()
        })
    }

    private fun smklllllcmnjiaecnuibbaiusybdcaiyvba() {
        if ((!GetWallDataUtils.showAdCenter() || !GetWallDataUtils.showAdBlacklist()) || !BIBIUBADDDDUtils.canShowAD()) {
            jumpToMain()
            return
        }
        if (BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.haveCache && lifecycle.currentState == Lifecycle.State.RESUMED) {
            BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.showFullScreenAdBIUYBUI(this) {
                jumpToMain()
            }
        } else jumpToMain()
    }

    private fun jumpToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        PaperThreeConstant.canRefreshHomeNative = true
    }

    private fun timeShowMainAd(nextFun: () -> Unit, applyFun: () -> Unit) {
        val num = GetWallDataUtils.getLocalBlockingData().rescontinue?.toInt()
        var loadNum = SPUtils.getInstance().getInt(KeyData.local_rescontinue)
        if (num != 0 && loadNum <= 0) {
            loadNum = num ?: 0
            SPUtils.getInstance().put(KeyData.local_rescontinue, loadNum)
            nextFun()
            return
        }
        if (loadNum > 0) {
            loadNum--
            SPUtils.getInstance().put(KeyData.local_rescontinue, loadNum)
            applyFun()
            return
        }
        nextFun()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.Main) {
            if (!BIBIUBADDDDUtils.canShowAD() || !GetWallDataUtils.showAdCenter() || !GetWallDataUtils.showAdBlacklist()) {
                mBinding.nativeFrameEnd.isVisible = false
            } else if (PaperThreeConstant.canRefreshHomeNative) {
                PaperThreeConstant.canRefreshHomeNative = false
                BIBIUBADDDDUtils.mainNativeBOUVIY.preload(this@EndViewActivity)
                mBinding.nativeFrameEnd.isVisible = true
                MainScope().launch {
                    while (true) {
                        if (BIBIUBADDDDUtils.mainNativeBOUVIY.haveCache) {
                            PaperThreeConstant.canRefreshHomeNative = false
                            BIBIUBADDDDUtils.mainNativeBOUVIY.showVIUVYNativeAd(
                                this@EndViewActivity,
                                mBinding.nativeFrameEnd
                            ) { baseAd = it }
                            break
                        }
                        delay(300)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        baseAd?.gandiao()
    }
}







