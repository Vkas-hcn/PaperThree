package what.a.pity.phone.call.paperthree.b.ac.light

import android.app.Activity
import android.app.ActivityManager
import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.b.ac.EndViewActivity
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.SoWhatCanYouDo
import what.a.pity.phone.call.paperthree.databinding.LightSettingLayoutBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.light.GifWallpaperService
import what.a.pity.phone.call.paperthree.fast.light.LightWindow
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils
import java.io.IOException

class LightSettingActivity : BaseActivity<LightSettingLayoutBinding>(),
    EasyPermissions.PermissionCallbacks {

    override var viewID: Int
        get() = R.layout.light_setting_layout
        set(value) {}
    private var curImg = R.mipmap.qiuqiu1
    private var baseAd: SoWhatCanYouDo? = null
    val REQUEST_CODE_SET_WALLPAPER = 0x756
    private var jobBackAd: Job? = null
    private var jobApplyAd: Job? = null

    override fun initV() {
        curImg = KeyData.lightWallData
        if (curImg != 0 && curImg != -1) {
            mBinding.curImage.setImageResource(curImg)
        } else {
            mBinding.curImage.setImageResource(R.drawable.ic_blck)
        }
        showImageType()
        WallNetDataUtils.postPotIntData(this, "wa19ll")
    }

    private fun showImageType() {
        if (PaperThreeApp.isGifImage) {
            mBinding.haveLightView = false
            mBinding.lightGif.setGifResource(R.drawable.ic_gif_1)
        } else {
            mBinding.haveLightView = true
            mBinding.lightView.setGradientSetting2()
        }
    }

    private fun applyFun() {
        val gaoji = if(PaperThreeApp.isGifImage || PaperThreeApp.isAdImage){
            "1"
        }else{
            "2"
        }
        val isOpenLightPermission =
            SPUtils.getInstance().getBoolean(KeyData.isOpenLightPermission, false)
        val sheRe = (gaoji=="2") && (KeyData.lightWallData != 0 && KeyData.lightWallData != -1)
        WallNetDataUtils.postPotIntData(
            this,
            "wa21ll",
            "fa",
            isOpenLightPermission.toString(),
            "fa1",
            gaoji,
            "fa2",
            sheRe.toString(),
        )

        if (isOpenLightPermission) {
            stopGifWallpaperService()
            startWallpaperSettings()
            KeyData.isImagePos = KeyData.isImagePosApp

            KeyData.lightSpeed = KeyData.lightSpeedApp

            KeyData.lightBorder = KeyData.lightBorderApp
            LightWindow.getInstance().showPasswordBox()
            Toast.makeText(this, "Successful application.", Toast.LENGTH_SHORT).show()

        } else {
            stopGifWallpaperService()
            startWallpaperSettings()
        }
        mBinding.lightView.visibility = View.GONE
        mBinding.lightGif.visibility = View.GONE
    }

    override fun initL() {
        mBinding.clApply.setOnClickListener {
            waitForApplyAdData()
        }
        mBinding.pagerThreeBack.setOnClickListener {
            waitForAdData()
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

    private fun applyWallFun() {
        mBinding.haveLoad = false
        BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.showFullScreenAdBIUYBUI(this) {
            applyFun()
        }
    }

    fun waitForAdData() {
        WallNetDataUtils.postPotIntData(this, "wa20ll")
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
            mBinding.haveLoad = false
            jobBackAd?.cancel()
            finish()
        })
    }

    private fun waitForApplyAdData() {
        if (!BIBIUBADDDDUtils.canShowAD()) {
            applyFun()
            return
        }
        mBinding.haveLoad = true
        jobApplyAd?.cancel()
        jobApplyAd = AppInitUtils().countDown(100, 150, MainScope(), {
            if (it < 100 && BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.haveCache) {
                jobApplyAd?.cancel()
                applyWallFun()
            }
        }, {
            mBinding.haveLoad = false
            jobApplyAd?.cancel()
            applyFun()
        })
    }

    private fun goEndPaper() {
        val intent = Intent(this, EndViewActivity::class.java)
        if (PaperThreeApp.isGifImage || PaperThreeApp.isAdImage) {
            intent.putExtra("typeEnd", "2")
        } else {
            intent.putExtra("typeEnd", "3")
        }
        startActivity(intent)

        PaperThreeApp.isHaveLight = true
        val gaoji = if(PaperThreeApp.isGifImage || PaperThreeApp.isAdImage){
            "1"
        }else{
            "2"
        }
        val isOpenLightPermission =
            SPUtils.getInstance().getBoolean(KeyData.isOpenLightPermission, false)
        val sheRe = (gaoji=="2") && (KeyData.lightWallData != 0 && KeyData.lightWallData != -1)
        WallNetDataUtils.postPotIntData(
            this,
            "wa22ll",
            "fa",
            isOpenLightPermission.toString(),
            "fa1",
            gaoji,
            "fa2",
            sheRe.toString(),
        )
    }

    private fun stopGifWallpaperService() {
        val intent = Intent(this, GifWallpaperService::class.java)
        stopService(intent)
    }

    private fun startWallpaperSettings() {
        lifecycleScope.launch(Dispatchers.IO) {
            val wallpaperManager = WallpaperManager.getInstance(this@LightSettingActivity)
            val defaultWallpaper = BitmapFactory.decodeResource(
                this@LightSettingActivity.resources,
                R.drawable.ic_blck
            )
            try {
                wallpaperManager.setBitmap(defaultWallpaper)
                withContext(Dispatchers.Main) {
                    PaperThreeVariable.isToRequestPer = true
                    val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
                    intent.putExtra(
                        WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                        ComponentName(this@LightSettingActivity, GifWallpaperService::class.java)
                    )
                    startActivityForResult(intent, REQUEST_CODE_SET_WALLPAPER)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        PaperThreeVariable.isToRequestPer = false
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        PaperThreeVariable.isToRequestPer = false
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this)
                .setRationale("This function requires storage permission to be enabled")
                .setNegativeButton("No")
                .setPositiveButton("Yes")
                .build().show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        baseAd?.gandiao()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SET_WALLPAPER) {
            lifecycleScope.launch {
                delay(1000)
                PaperThreeVariable.isToRequestPer = false
            }
            if (resultCode == Activity.RESULT_OK) {
                goEndPaper()
            }
        }
    }

}







