package what.a.pity.phone.call.paperthree.g.ui.type

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.databinding.TypeGuideBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils

class TypeActivity  : BaseActivity<TypeGuideBinding>() {

    override var viewID: Int = R.layout.type_guide

    private var jobTypeAd: Job? = null

    private lateinit var typeAdapter: TypeAdapter
    override fun initV() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE

        window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        userWallPaperGuideList()
        WallNetDataUtils.postPotIntData(this, "wa33ll")
    }

    private fun userWallPaperGuideList() {
        val typeList = KeyData.getClassNameList()
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mBinding.rvLightPop.layoutManager = layoutManager
        typeAdapter = TypeAdapter(this, typeList)
        mBinding.rvLightPop.adapter = typeAdapter
    }

    override fun initL() {
        mBinding.tvNext.setOnClickListener {
            waitForRvData()
        }
        mBinding.pagerThreeBack.setOnClickListener {
            backTypeFun()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backTypeFun()
            }
        })
    }
    fun backTypeFun(){
        finish()
        WallNetDataUtils.postPotIntData(this, "wa34ll")
    }

    override fun onDestroy() {
        super.onDestroy()
        jobTypeAd?.cancel()
        jobTypeAd = null
    }
    private fun nextFun(){
        typeAdapter.setCheckTypeName()
        AppInitUtils().toMain(this)
        WallNetDataUtils.postPotIntData(this, "wa35ll","fa",KeyData.checkTheType)
    }
    private fun showRvAd() {
        mBinding.haveLoad = false
        BIBIUBADDDDUtils.intType.showFullScreenAdBIUYBUI(this) {
            nextFun()
        }
    }

    private fun waitForRvData() {
        if ((!GetWallDataUtils.showAdCenter() || !GetWallDataUtils.showAdBlacklist()) || !BIBIUBADDDDUtils.canShowAD()) {
            nextFun()
            return
        }
        mBinding.haveLoad = true
        jobTypeAd?.cancel()
        jobTypeAd = AppInitUtils().countDown(100, 50, MainScope(), {
            if (it < 100 && BIBIUBADDDDUtils.intType.haveCache) {
                jobTypeAd?.cancel()
                showRvAd()
            }
        }, {
            jobTypeAd?.cancel()
            mBinding.haveLoad = false
            nextFun()
        })
    }
}