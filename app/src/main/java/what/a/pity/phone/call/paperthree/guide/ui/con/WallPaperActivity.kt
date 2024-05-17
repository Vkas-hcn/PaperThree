package what.a.pity.phone.call.paperthree.guide.ui.con

import android.content.Intent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.databinding.WallpaperGuideBinding
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils
import what.a.pity.phone.call.paperthree.guide.ui.type.TypeActivity

class WallPaperActivity : BaseActivity<WallpaperGuideBinding>() {

    override var viewID: Int = R.layout.wallpaper_guide

    private var jobIntroduceAd: Job? = null
    private var jobTypeAd: Job? = null

    private lateinit var guideDataBeanList: MutableList<GuideDataBean>
    private lateinit var guideAdapter: GuideAdapter
    private lateinit var guideDataBean: GuideDataBean
    override fun initV() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE

        window.navigationBarColor = resources.getColor(R.color.navigation_bar_color)
        BIBIUBADDDDUtils.intIntroduce.preload(this)
        BIBIUBADDDDUtils.intType.preload(this)
        userWallPaperGuideList()
        WallNetDataUtils.postPotIntData(this, "wa29ll")
    }

    private fun userWallPaperGuideList() {
        guideDataBeanList = ArrayList()
        for (i in 0..2) {
            when (i) {
                0 -> {
                    guideDataBean = GuideDataBean(
                        R.drawable.bg_guide_0,
                        "Express Your Style with our Vast Wallpaper Selection"
                    )
                }

                1 -> {
                    guideDataBean = GuideDataBean(
                        R.drawable.bg_guide_1,
                        "Illuminate Your Device with Dynamic Border Effects"
                    )
                }

                2 -> {
                    guideDataBean = GuideDataBean(
                        R.drawable.bg_guide_2,
                        "Immerse Yourself in a World of Diverse Wallpapers"
                    )
                }
            }
            guideDataBeanList.add(guideDataBean)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.rv_guide)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        guideAdapter = GuideAdapter(this, guideDataBeanList)
        recyclerView.adapter = guideAdapter
        val snapHelper = SingleSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    recyclerView.smoothScrollToPosition(
                        (recyclerView.layoutManager as LinearLayoutManager)
                            .findFirstVisibleItemPosition()
                    )
                }
            }
        })
    }

    private fun RecyclerView.getCurrentItemPosition(): Int {
        val layoutManager = this.layoutManager as? LinearLayoutManager
        return layoutManager?.findFirstVisibleItemPosition() ?: RecyclerView.NO_POSITION
    }

    private fun RecyclerView.scrollToNextItem(smooth: Boolean = true) {
        val currentPosition = getCurrentItemPosition()
        val adapter = this.adapter
        if (currentPosition != RecyclerView.NO_POSITION && adapter != null) {
            val nextPosition = currentPosition + 1
            if (nextPosition < adapter.itemCount) {
                if (nextPosition == 1) {
                    WallNetDataUtils.postPotIntData(this@WallPaperActivity, "wa30ll")
                }
                if (nextPosition == 2) {
                    WallNetDataUtils.postPotIntData(this@WallPaperActivity, "wa31ll")
                }
                if (smooth) {
                    this.smoothScrollToPosition(nextPosition)
                } else {
                    this.scrollToPosition(nextPosition)
                }
            } else {
                WallNetDataUtils.postPotIntData(this@WallPaperActivity, "wa32ll")
                waitForRvData()
            }
        }
    }


    override fun initL() {
        mBinding.tvNext.setOnClickListener {
            mBinding.rvGuide.scrollToNextItem()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        jobIntroduceAd?.cancel()
        jobIntroduceAd = null
        jobTypeAd?.cancel()
        jobTypeAd = null
    }

    private fun showRvAd() {
        mBinding.haveLoad = false
        BIBIUBADDDDUtils.intIntroduce.showFullScreenAdBIUYBUI(this) {
            goToType()
        }
    }


    private fun waitForRvData() {
        if ((!GetWallDataUtils.showAdCenter() || !GetWallDataUtils.showAdBlacklist()) || !BIBIUBADDDDUtils.canShowAD()) {
            goToType()
            return
        }
        mBinding.haveLoad = true
        jobIntroduceAd?.cancel()
        jobIntroduceAd = AppInitUtils().countDown(100, 50, MainScope(), {
            if (it < 100 && BIBIUBADDDDUtils.intIntroduce.haveCache) {
                jobIntroduceAd?.cancel()
                showRvAd()
            }
        }, {
            jobIntroduceAd?.cancel()
            mBinding.haveLoad = false
            goToType()
        })
    }

    private fun goToType() {
        val intent = Intent(this, TypeActivity::class.java)
        startActivity(intent)
        finish()
    }
}