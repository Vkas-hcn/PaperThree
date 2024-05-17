package what.a.pity.phone.call.paperthree.b.ac.light

import android.app.Activity
import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.SPUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp.Companion.isAdImage
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp.Companion.isGifImage
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.databinding.ActivityLightWallBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.light.GifWallpaperService
import what.a.pity.phone.call.paperthree.fast.light.LightWindow
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils.isVisible
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils
import java.io.IOException

class LightWallActivity : BaseActivity<ActivityLightWallBinding>() {
    private var imageLightListData: MutableList<LightWallBean> = ArrayList()
    private lateinit var imageLightData: LightWallBean
    private var lightName: String = ""
    private var wallPaperData = -1
    private var jobBackAd: Job? = null
    var isOpenLightPermission =
        SPUtils.getInstance().getBoolean(KeyData.isOpenLightPermission, false)
    override var viewID: Int
        get() = R.layout.activity_light_wall
        set(value) {}

    override fun initV() {
        showImageType()
        userLightPopList()
        showPerUi()
        setSeekBar()
        WallNetDataUtils.postPotIntData(this, "wa17ll")
    }

    override fun initL() {
        mBinding.clDialog.setOnClickListener {
        }
        mBinding.clLight.setOnClickListener {
            mBinding.clDialog.visibility = View.GONE
        }
        mBinding.rvLightPop.setOnClickListener {
            mBinding.clDialog.visibility = View.GONE
        }
        mBinding.pagerThreeBack.setOnClickListener {
            waitForAdData()
        }
        mBinding.tvCon.setOnClickListener {
            jumpToDetail()
        }
        mBinding.imgPer.setOnClickListener {
            checkActionManage(this) {
                isOpenLightPermission = !isOpenLightPermission
                showPerUi()
            }
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                waitForAdData()
            }
        })
        mBinding.viewLoad.setOnClickListener { }
    }

    private fun backTOMain() {
        mBinding.haveLoad = false
        BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.showFullScreenAdBIUYBUI(this) {
            finish()
        }
    }

    fun waitForAdData() {
        WallNetDataUtils.postPotIntData(this, "wa18ll")
        if ((!GetWallDataUtils.showAdCenter() || !BIBIUBADDDDUtils.canShowAD())) {
            finish()
            return
        }
        mBinding.haveLoad = true
        jobBackAd?.cancel()
        jobBackAd = AppInitUtils().countDown(100, 150, MainScope(), {
            if (it < 100 && BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.haveCache) {
                jobBackAd?.cancel()
                backTOMain()
            }
        }, {
            jobBackAd?.cancel()
            mBinding.haveLoad = false
            finish()
        })
    }

    private fun showImageType() {
        lightName = intent.getStringExtra("lightWall") ?: ""
        isGifImage = lightName.contains("top")
        isAdImage = lightName.contains("ad")
        if (isGifImage || isAdImage) {
            WallNetDataUtils.postPotIntData(this, "wa15ll", "fa",lightName)
        } else {
            WallNetDataUtils.postPotIntData(this, "wa16ll", "fa",lightName)
        }
        if (isGifImage) {
            mBinding.haveLightView = 2
            mBinding.lightGif.setGifResource(R.drawable.ic_gif_1)
            mBinding.textView2.visibility = View.GONE
            mBinding.sbSpeed.visibility = View.GONE
            mBinding.tvBorder.visibility = View.GONE
            mBinding.sbBorder.visibility = View.GONE
        } else {
            mBinding.haveLightView = 1
            mBinding.lightView.setGradientSetting2()
            mBinding.textView2.visibility = View.VISIBLE
            mBinding.sbSpeed.visibility = View.VISIBLE
            mBinding.tvBorder.visibility = View.VISIBLE
            mBinding.sbBorder.visibility = View.VISIBLE
        }
        mBinding.clDialog.visibility = View.VISIBLE
    }

    private fun showPerUi() {
        val state = Settings.canDrawOverlays(this)
        if (isOpenLightPermission && state) {
            SPUtils.getInstance().put(KeyData.isOpenLightPermission, true)
            mBinding.imgPer.setImageResource(R.drawable.ic_open_menu2)
        } else {
            SPUtils.getInstance().put(KeyData.isOpenLightPermission, false)
            mBinding.imgPer.setImageResource(R.drawable.ic_open_menu)
        }
    }

    private fun jumpToDetail() {
        val intent = Intent(this, LightSettingActivity::class.java)
        KeyData.lightWallData = if (isAdImage && wallPaperData == -1) {
            KeyData.getAdImage(lightName)
        } else {
            wallPaperData
        }
        startActivity(intent)
    }

    private fun userLightPopList() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_light_pop)
        PaperThreeVariable.imageLightWallList.forEach {
            imageLightData = LightWallBean(0, false)
            imageLightData.imageData = it
            imageLightData.haveCheck = false
            imageLightListData.add(imageLightData)
        }
        imageLightListData[0].haveCheck = !isAdImage
        mBinding.clDialog.visibility = View.VISIBLE

        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        val adapter = LightAdapter(this, imageLightListData)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : LightAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                checkAndClick(position, adapter)
            }
        })
    }

    fun checkAndClick(position: Int, adapter: LightAdapter) {
        imageLightListData.forEach {
            it.haveCheck = false
        }
        imageLightListData[position].haveCheck = true
        wallPaperData = imageLightListData[position].imageData
        adapter.notifyDataSetChanged()
        if (isGifImage) {
            mBinding.textView2.visibility = View.GONE
            mBinding.sbSpeed.visibility = View.GONE
            mBinding.tvBorder.visibility = View.GONE
            mBinding.sbBorder.visibility = View.GONE
        } else {
            mBinding.textView2.visibility = View.VISIBLE
            mBinding.sbSpeed.visibility = View.VISIBLE
            mBinding.tvBorder.visibility = View.VISIBLE
            mBinding.sbBorder.visibility = View.VISIBLE
        }
        mBinding.clDialog.visibility = View.VISIBLE

    }

    private fun checkActionManage(context: Context, nextFun: () -> Unit) {
        if (!Settings.canDrawOverlays(context)
        ) {
            runCatching {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
                    data = "package:${applicationContext.packageName}".toUri()
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivityForResult(intent, 0x4556)
            }
        } else {
            nextFun()
        }
    }

    private fun setSeekBar() {
        mBinding.sbSpeed.progress = (1000 - KeyData.lightSpeed.toInt()) / 6
        mBinding.sbSpeed.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val duration = (1000 - (progress * 6)).toLong()
                mBinding.lightView.setAnimationDuration(duration)
                KeyData.lightSpeedApp = duration
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        mBinding.sbBorder.progress = KeyData.lightBorder.toInt()
        mBinding.sbBorder.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mBinding.lightView.setStrokeWidth((progress).toFloat())
                KeyData.lightBorderApp = (progress).toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}