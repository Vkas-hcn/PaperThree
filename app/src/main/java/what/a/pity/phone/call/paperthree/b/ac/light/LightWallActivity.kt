package what.a.pity.phone.call.paperthree.b.ac.light

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.View
import android.widget.MediaController
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.SPUtils
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp.Companion.isGifImage
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.databinding.ActivityLightWallBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.light.LightWindow
import java.io.IOException

class LightWallActivity : BaseActivity<ActivityLightWallBinding>() {
    private var imageLightListData: MutableList<LightWallBean> = ArrayList()
    private lateinit var imageLightData: LightWallBean
    private var lightName: String = ""
    private var wallPaperData = R.mipmap.qiuqiu1

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
    }

    override fun initL() {
        mBinding.clLight.setOnClickListener {
            mBinding.clDialog.visibility = View.GONE
        }
        mBinding.rvLightPop.setOnClickListener {
            mBinding.clDialog.visibility = View.GONE
        }
        mBinding.pagerThreeBack.setOnClickListener {
            finish()
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
    }

    private fun showImageType() {
        lightName = intent.getStringExtra("lightWall") ?: ""
        LightWindow.getInstance().closeThePasswordBox()
        if (lightName.contains("top")) {
            isGifImage = true
            mBinding.haveLightView = false
            try {
//                val gifDrawable = GifDrawable(assets, "gif_ad_ass_01.gif")
//                mBinding.lightGif?.setImageDrawable(gifDrawable)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            isGifImage = false
            mBinding.lightView.setGradientSetting()
            mBinding.haveLightView = true
        }
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
        KeyData.lightWallData = wallPaperData
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
        if (mBinding.haveLightView == false) {
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
                startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
                    data = "package:${applicationContext.packageName}".toUri()
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        } else {
            nextFun()
        }
    }

    fun setSeekBar() {
        mBinding.sbSpeed.progress = (1000 - KeyData.lightSpeed.toInt()) / 10
        mBinding.sbBorder.progress = KeyData.lightBorder.toInt()
        mBinding.sbSpeed.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val duration = (1000 - (progress * 10)).toLong()
                mBinding.lightView.setAnimationDuration(duration)
                KeyData.lightSpeed = duration
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        mBinding.sbBorder.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mBinding.lightView.setStrokeWidth((progress).toFloat())
                KeyData.lightBorder = (progress).toFloat()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}