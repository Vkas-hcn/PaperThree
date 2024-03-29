package what.a.pity.phone.call.paperthree.b.ac

import android.content.Intent
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.youth.banner.indicator.CircleIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.SoWhatCanYouDo
import what.a.pity.phone.call.paperthree.databinding.ActivityMainBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import kotlin.system.exitProcess

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override var viewID: Int
        get() = R.layout.activity_main
        set(value) {}

    override fun initV() {
        if (!BIBIUBADDDDUtils.canShowAD()) {
            mBinding.nativeFrameQr.isVisible = false
        } else {
        }
        mBinding.showLoading = false
        BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.preload(this)
        initBanner()
        userList()
    }

    private fun userList() {
        val recyclerView = findViewById<RecyclerView>(R.id.wallPaperThreeMainImgList)
        val imageList: List<Int> = PaperThreeVariable.imageList
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        val adapter = AppMainAdapter(this, imageList)
        recyclerView.adapter = adapter
    }

    override fun initL() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                PaperThreeVariable.isAppBackFor = true
                SPUtils.getInstance().remove(KeyData.local_preback)
                SPUtils.getInstance().remove(KeyData.local_preuse)
                SPUtils.getInstance().remove(KeyData.local_preenter)
                SPUtils.getInstance().remove(KeyData.local_rescontinue)
                finish()
            }
        })
        mBinding.wallPaperThreeMainSetRl.setOnClickListener {
            startActivity(
                Intent(this, SettingActivity::class.java)
            )
        }
    }

    private fun initBanner() {
        mBinding.wallPaperThreeMainBanner.addBannerLifecycleObserver(this)
            ?.setAdapter(AppImageListAdapter(this, PaperThreeVariable.imgBannerList))
            ?.setBannerGalleryEffect(10, 10, 10, 0.9f)
            ?.indicator = CircleIndicator(this)
    }

    private var baseAd: SoWhatCanYouDo? = null

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.Main) {
            delay(200)
            if (!BIBIUBADDDDUtils.canShowAD()) {
                mBinding.nativeFrameQr.isVisible = false
            } else if (PaperThreeConstant.canRefreshHomeNative) {
                PaperThreeConstant.canRefreshHomeNative = false
                BIBIUBADDDDUtils.mainNativeBOUVIY.preload(this@MainActivity)
                mBinding.nativeFrameQr.isVisible = true
                    while (true) {
                        if (BIBIUBADDDDUtils.mainNativeBOUVIY.haveCache) {
                            PaperThreeConstant.canRefreshHomeNative = false
                            BIBIUBADDDDUtils.mainNativeBOUVIY.showVIUVYNativeAd(
                                this@MainActivity,
                                mBinding.nativeFrameQr
                            ) { baseAd = it }
                            baseAd?.gandiao()
                            break
                        }
                        delay(300)
                    }
            }
        }
    }

}