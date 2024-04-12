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
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.SpanUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeApp
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.b.ac.EndViewActivity
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.SoWhatCanYouDo
import what.a.pity.phone.call.paperthree.databinding.LightSettingLayoutBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.light.GifWallpaperService
import what.a.pity.phone.call.paperthree.fast.light.LightWindow
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import java.io.IOException

class LightSettingActivity : BaseActivity<LightSettingLayoutBinding>(),
    EasyPermissions.PermissionCallbacks {

    override var viewID: Int
        get() = R.layout.light_setting_layout
        set(value) {}
    private var curImg = R.mipmap.qiuqiu1
    private var baseAd: SoWhatCanYouDo? = null
    val REQUEST_CODE_SET_WALLPAPER = 0x756

    override fun initV() {
        curImg = KeyData.lightWallData
        if (curImg != 0) {
            mBinding.curImage.setImageResource(curImg)
        } else {
            mBinding.curImage.setImageResource(R.drawable.ic_blck)
        }
        showImageType()
    }

    private fun showImageType() {
        if (PaperThreeApp.isGifImage) {
            mBinding.haveLightView = false
        } else {
            mBinding.haveLightView = true
            mBinding.lightView.setGradientSetting2()
        }
    }

    override fun initL() {
        mBinding.clApply.setOnClickListener {
            val isOpenLightPermission =
                SPUtils.getInstance().getBoolean(KeyData.isOpenLightPermission, false)
            if (isOpenLightPermission) {
                stopGifWallpaperService()
                startWallpaperSettings()
                KeyData.isImagePos = KeyData.isImagePosApp

                KeyData.lightSpeed = KeyData.lightSpeedApp

                KeyData.lightBorder = KeyData.lightBorderApp
                LightWindow.getInstance().showPasswordBox()
            } else {
                stopGifWallpaperService()
                startWallpaperSettings()
            }
            mBinding.lightView.visibility = View.GONE
            mBinding.lightGif.visibility = View.GONE
        }
        mBinding.pagerThreeBack.setOnClickListener {
            finish()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    private fun goEndPaper() {
        val intent = Intent(this, EndViewActivity::class.java)
        startActivity(intent)
        PaperThreeApp.isHaveLight = true
    }

    private fun stopGifWallpaperService() {
        val intent = Intent(this, GifWallpaperService::class.java)
        stopService(intent)
    }

    private fun startWallpaperSettings() {
        lifecycleScope.launch(Dispatchers.IO) {
            val wallpaperManager = WallpaperManager.getInstance(this@LightSettingActivity)
            val defaultWallpaper = BitmapFactory.decodeResource(this@LightSettingActivity.resources, R.drawable.ic_blck)
            try {
                wallpaperManager.setBitmap(defaultWallpaper)
                withContext(Dispatchers.Main){
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
                // 用户成功设置了动态壁纸
                goEndPaper()
            }
        }
    }

}







