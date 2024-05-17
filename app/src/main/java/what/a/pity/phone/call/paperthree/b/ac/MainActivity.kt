package what.a.pity.phone.call.paperthree.b.ac

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
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
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp.Companion.isHaveLight
import what.a.pity.phone.call.paperthree.a.app.PaperThreeConstant
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.b.ac.light.LightAdapter
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.SoWhatCanYouDo
import what.a.pity.phone.call.paperthree.databinding.ActivityMainBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.light.LightWindow
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils
import kotlin.system.exitProcess

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var imageList: MutableList<Int>
    private lateinit var lightMainAdapter: AppMainAdapter
    private lateinit var bannerAdapter: AppImageListAdapter

    override var viewID: Int
        get() = R.layout.activity_main
        set(value) {}

    override fun initV() {
        if (!BIBIUBADDDDUtils.canShowAD()) {
//            mBinding.nativeFrameQr.isInvisible = true
        }
        mBinding.showLoading = false
        BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.preload(this)
        initBanner()
        userList()
        userLightAdList()
        userLightPopList()
        showClassName()
        clickToMain()
        clickClass()
        PaperThreeConstant.canRefreshHomeNative = true
    }

    private fun showClassName() {
        val classNameList = KeyData.getClassNameList()
        classNameList.forEachIndexed { index, s ->
            when (index) {
                0 -> mBinding.tv0.text = s
                1 -> mBinding.tv1.text = s
                2 -> mBinding.tv2.text = s
                3 -> mBinding.tv3.text = s
                4 -> mBinding.tv4.text = s
                5 -> mBinding.tv5.text = s
            }
        }
    }

    private fun setClassData(classWall: Int) {
        val classNameList = KeyData.getClassNameList()
        val imageList = KeyData.getNameData(classNameList[classWall])
        lightMainAdapter.upDataList(imageList)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changingLockData(resourceId: Int, isBanner: Boolean) {
        KeyData.setLocalRvData(this, resourceId)
        if (isBanner) {
            lightMainAdapter.notifyDataSetChanged()
        } else {
            bannerAdapter.notifyDataSetChanged()
        }
    }

    private fun initBanner() {
        val bannerList = KeyData.getNameData(KeyData.getClassNameList()[0]) as MutableList<Int>
        bannerAdapter = AppImageListAdapter(this, bannerList.shuffled().take(5))
        mBinding.wallPaperThreeMainBanner.addBannerLifecycleObserver(this)
            ?.setAdapter(bannerAdapter)
            ?.setBannerGalleryEffect(10, 10, 10, 0.9f)
            ?.indicator = CircleIndicator(this)
    }

    private fun userList() {
        imageList = KeyData.getNameData(KeyData.getClassNameList()[0]) as MutableList<Int>
        val recyclerView = findViewById<RecyclerView>(R.id.wallPaperThreeMainImgList)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        lightMainAdapter = AppMainAdapter(this, imageList)
        recyclerView.adapter = lightMainAdapter
    }

    private fun userLightAdList() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_light_ad)
        val imageList: List<Int> = PaperThreeVariable.imgLightAdList
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        val adapter = LightMainAdapter(this, imageList)
        recyclerView.adapter = adapter
    }

    private fun userLightPopList() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_light_pop)
        val imageList: List<Int> = PaperThreeVariable.imgLightPopList
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        val adapter = LightMainAdapter(this, imageList)
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


    private var baseAd: SoWhatCanYouDo? = null

    private fun clickToMain() {
        mBinding.tvEdge.setOnClickListener {
            mBinding.isLight = false
            showBianJie()
            BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.preload(this)
            BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.preload(this)
            BIBIUBADDDDUtils.rewAd.preload(this)
        }
        mBinding.tvWall.setOnClickListener {
            mBinding.isLight = true
            showBianJie()
        }
    }

    private fun clickClass() {
        mBinding.tv0.setOnClickListener {
            mBinding.classWall = 0
            Log.e("TAG", "clickClass: 0")
            setClassData(0)
        }
        mBinding.tv1.setOnClickListener {
            mBinding.classWall = 1
            Log.e("TAG", "clickClass: 1")
            setClassData(1)
        }
        mBinding.tv2.setOnClickListener {
            mBinding.classWall = 2
            Log.e("TAG", "clickClass: 2")
            setClassData(2)
        }
        mBinding.tv3.setOnClickListener {
            mBinding.classWall = 3
            Log.e("TAG", "clickClass: 3")
            setClassData(3)
        }
        mBinding.tv4.setOnClickListener {
            mBinding.classWall = 4
            Log.e("TAG", "clickClass: 4")
            setClassData(4)
        }
        mBinding.tv5.setOnClickListener {
            mBinding.classWall = 5
            Log.e("TAG", "clickClass: 5")
            setClassData(5)
        }
    }

    private fun showBianJie() {
        if (mBinding.isLight == true) {
            WallNetDataUtils.postPotIntData(this, "wa14ll")
            if (PaperThreeApp.isGifImage) {
                mBinding.haveLightView = 2
                mBinding.lightGif.setGifResource(R.drawable.ic_gif_1)
            } else {
                mBinding.lightView.setGradientSetting2()
                mBinding.haveLightView = 1
            }
            LightWindow.getInstance().closeThePasswordBox()
        } else {
            mBinding.haveLightView = 0
            if (isHaveLight) {
                LightWindow.getInstance().showPasswordBox()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.Main) {
            delay(200)
            showBianJie()
//            if (!BIBIUBADDDDUtils.canShowAD()) {
//                mBinding.nativeFrameQr.isVisible = false
//            } else if (PaperThreeConstant.canRefreshHomeNative) {
//                BIBIUBADDDDUtils.mainNativeBOUVIY.preload(this@MainActivity)
//                mBinding.nativeFrameQr.isVisible = true
//                while (true) {
//                    if (BIBIUBADDDDUtils.mainNativeBOUVIY.haveCache) {
//                        PaperThreeConstant.canRefreshHomeNative = false
//                        BIBIUBADDDDUtils.mainNativeBOUVIY.showVIUVYNativeAd(
//                            this@MainActivity,
//                            mBinding.nativeFrameQr
//                        ) { baseAd = it }
//                        break
//                    }
//                    delay(300)
//                }
//            }
        }
    }

}