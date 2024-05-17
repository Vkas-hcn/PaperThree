package what.a.pity.phone.call.paperthree.b.ac

import android.content.Intent
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.SPUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
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
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils

class EndViewActivity : BaseActivity<EndLayoutBinding>() {
    var type = 0
    override var viewID: Int
        get() = R.layout.end_layout
        set(value) {}

    override fun initV() {
         type = intent.getIntExtra("type", 0)
        WallNetDataUtils.postPotIntData(this, "wa11ll", "fa", type.toString())
    }

    override fun initL() {
        mBinding.actContinue.setOnClickListener {
            WallNetDataUtils.postPotIntData(this, "wa13ll", "fa", type.toString())
            backToMainFun()
        }

        mBinding.pagerThreeBack.setOnClickListener {
            WallNetDataUtils.postPotIntData(this, "wa12ll", "fa", type.toString())

            backToMainFun()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                WallNetDataUtils.postPotIntData(this@EndViewActivity, "wa12ll", "fa", type.toString())
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
        Log.e("TAG", "jumpToMain-fa_c_inter: ")
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
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}







