package what.a.pity.phone.call.paperthree.b.ac

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
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
import what.a.pity.phone.call.paperthree.databinding.PreviewLayoutBinding
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.light.LightWindow
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils

class PreViewActivity : BaseActivity<PreviewLayoutBinding>(), EasyPermissions.PermissionCallbacks {

    override var viewID: Int
        get() = R.layout.preview_layout
        set(value) {}
    private var curImg = R.mipmap.qiuqiu1
    private var baseAd: SoWhatCanYouDo? = null

    companion object {
        var isClickTypeDown: Int = 0 //0:back 1:dow,2:apply，-1：操作中
    }

    override fun initV() {
        curImg = intent.getIntExtra("intentImgResID", R.mipmap.qiuqiu1)
        mBinding.curImage.setImageResource(curImg)
        BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.preload(this)
        PaperThreeConstant.canRefreshHomeNative2 = true
        WallNetDataUtils.postPotIntData(this, "wa5ll")
    }

    override fun initL() {
        mBinding.downloadCl.setOnClickListener {
            WallNetDataUtils.postPotIntData(this, "wa7ll")
            if (haveCanNext()) {
                return@setOnClickListener
            }
            isClickTypeDown = 1
            BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.preload(this)
            AppInitUtils().saveImg(this) {
                dowLoadImageView()
            }
        }
        mBinding.clApply.setOnClickListener {
            WallNetDataUtils.postPotIntData(this, "wa8ll")
            if (haveCanNext()) {
                return@setOnClickListener
            }
            isClickTypeDown = 2
            BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.preload(this)
            onePerInt {
                showConfirmationDialog(this, mBinding.curImage)
            }
        }
        mBinding.pagerThreeBack.setOnClickListener {
            smklllllcmnjiaecnuibbaiusybdcaiyvba()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                smklllllcmnjiaecnuibbaiusybdcaiyvba()
            }
        })
    }

    private fun haveCanNext(): Boolean {
        return mBinding.llLoading.isVisible
    }

    private fun dowLoadImageView() {
        timeShowApplyAd({
            showLoadingAd {
                AppInitUtils().saveFreshAppImageToGallery(this, curImg) {
                    goEndPaper()
                }
            }
        }, {
            AppInitUtils().saveFreshAppImageToGallery(this, curImg) {
                goEndPaper()
            }
        })
    }

    private fun showLoadingAd(nextFun: () -> Unit) {
        showLoadingPro {
            if (it) {
                nextFun()
                return@showLoadingPro
            }
            if (isCanShowAd()) {
                BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.showFullScreenAdBIUYBUI(this) {
                    nextFun()
                }
            } else nextFun()
        }
    }


    private fun showLoadingPro(isBack: Boolean = false, nextFun: (isBlocking: Boolean) -> Unit) {
        if (isClickTypeDown != 0 && (!GetWallDataUtils.showAdCenter() || !GetWallDataUtils.showAdBlacklist()) || !BIBIUBADDDDUtils.canShowAD()) {
            nextFun(true)
            return
        }
        lifecycleScope.launch {
            var proInt = 0
            mBinding.llLoading.visibility = View.VISIBLE
            while (isActive) {
                val delayTime = if (isBack) 30L else 80L
                if ((isBack || isCanShowAd() || proInt >= 100) && (proInt >= 100 || (isCanShowAd() && 25 <= proInt))) {
                    cancel()
                    nextFun(false)
                    mBinding.llLoading.visibility = View.GONE
                    break
                }
                proInt++
                mBinding.pbLoading.progress = proInt
                mBinding.tvLoading.text = when (isClickTypeDown) {
                    0 -> {
                        "Returning... ${proInt}%"
                    }

                    1 -> {
                        "Downloading... ${proInt}%"
                    }

                    2 -> {
                        "Apply... ${proInt}%"
                    }

                    else -> {
                        ""
                    }
                }

                delay(delayTime)
            }
        }
    }


    private fun timeShowApplyAd(nextFun: () -> Unit, applyFun: () -> Unit) {
        val num = GetWallDataUtils.getLocalBlockingData().preuse?.toInt()
        var loadNum = SPUtils.getInstance().getInt(KeyData.local_preuse)
        if (num != 0 && loadNum <= 0) {
            loadNum = num ?: 0
            SPUtils.getInstance().put(KeyData.local_preuse, loadNum)
            nextFun()
            return
        }
        if (loadNum > 0) {
            loadNum--
            SPUtils.getInstance().put(KeyData.local_preuse, loadNum)
            applyFun()
            return
        }
        nextFun()
    }

    private fun smklllllcmnjiaecnuibbaiusybdcaiyvba() {
        WallNetDataUtils.postPotIntData(this, "wa6ll")
        if (haveCanNext()) {
            return
        }

        isClickTypeDown = 0
        timeShowBackAd {
            showLoadingPro(true) {
                showPreBackAd()
            }
        }
    }

    private fun timeShowBackAd(nextFun: () -> Unit) {
        val num = GetWallDataUtils.getLocalBlockingData().preback?.toInt()
        var loadNum = SPUtils.getInstance().getInt(KeyData.local_preback)
        if (num != 0 && loadNum <= 0) {
            loadNum = num ?: 0
            SPUtils.getInstance().put(KeyData.local_preback, loadNum)
            nextFun()
            return
        }
        if (loadNum > 0) {
            loadNum--
            SPUtils.getInstance().put(KeyData.local_preback, loadNum)
            finish()
            return
        }
        nextFun()
    }

    private fun showPreBackAd() {
        if ((!GetWallDataUtils.showAdCenter() || !BIBIUBADDDDUtils.canShowAD())) {
            finish()
            return
        }
        if (isCanShowAd()) {
            BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.showFullScreenAdBIUYBUI(this) {
                finish()
            }
        } else finish()
    }

    private fun isCanShowAd(): Boolean {
        return BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.haveCache && lifecycle.currentState == Lifecycle.State.RESUMED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        dowLoadImageView()
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

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.Main) {
            if (!BIBIUBADDDDUtils.canShowAD() || !GetWallDataUtils.showAdCenter() || !GetWallDataUtils.showAdBlacklist()) {
                mBinding.nativeFramePreview.isVisible = false
            } else if (PaperThreeConstant.canRefreshHomeNative2) {
                PaperThreeConstant.canRefreshHomeNative2 = false
                BIBIUBADDDDUtils.mainNativeBOUVIY2.preload(this@PreViewActivity)
                mBinding.nativeFramePreview.isVisible = true
                MainScope().launch {
                    while (true) {
                        if (BIBIUBADDDDUtils.mainNativeBOUVIY2.haveCache) {
                            PaperThreeConstant.canRefreshHomeNative2 = false
                            BIBIUBADDDDUtils.mainNativeBOUVIY2.showVIUVYNativeAd(
                                this@PreViewActivity,
                                mBinding.nativeFramePreview
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


    @SuppressLint("MissingInflatedId")
    fun showConfirmationDialog(detailSetActivity: PreViewActivity, imageView: ImageView) {
        val dialogView: View = LayoutInflater.from(detailSetActivity).inflate(R.layout.nlcaa, null)
        val setWallpaperButton = dialogView.findViewById<AppCompatTextView>(R.id.setWallpaperButton)
        val setLockscreenButton =
            dialogView.findViewById<AppCompatTextView>(R.id.setLockscreenButton)
        val setBoth = dialogView.findViewById<AppCompatTextView>(R.id.setBoth)
        val setCancel = dialogView.findViewById<AppCompatTextView>(R.id.setCancel)
        val alertDialog =
            AlertDialog.Builder(detailSetActivity).setView(dialogView).setCancelable(true).create()
        val lp = alertDialog.window?.attributes
        lp?.gravity = Gravity.BOTTOM
        alertDialog.window?.attributes = lp
        alertDialog.window?.setBackgroundDrawableResource(R.color.haahaha)
        alertDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        alertDialog.window?.setDimAmount(0f)
        setWallpaperButton.setOnClickListener {
            if (haveCanNext()) {
                return@setOnClickListener
            }
            timeShowApplyAd({
                alertDialog.dismiss()
                showLoadingAd {
                    AppInitUtils().setFreshAppHomeWallpaper(
                        detailSetActivity,
                        imageView.drawable as BitmapDrawable
                    )
                    goEndPaper()
                }
            }, {
                alertDialog.dismiss()

                AppInitUtils().setFreshAppHomeWallpaper(
                    detailSetActivity,
                    imageView.drawable as BitmapDrawable
                )
                goEndPaper()
            })

        }

        setLockscreenButton.setOnClickListener {
            if (haveCanNext()) {
                return@setOnClickListener
            }
            timeShowApplyAd({
                showLoadingAd {
                    alertDialog.dismiss()
                    checkPer(imageView, true)
                    goEndPaper()
                }
            }, {
                alertDialog.dismiss()
                checkPer(imageView, true)
                goEndPaper()
            })
        }
        setBoth.setOnClickListener {
            if (haveCanNext()) {
                return@setOnClickListener
            }
            timeShowApplyAd({
                showLoadingAd {
                    alertDialog.dismiss()
                    checkPer(imageView)
                    AppInitUtils().setFreshAppWallpaper(
                        detailSetActivity,
                        imageView.drawable as BitmapDrawable
                    ) {}
                    goEndPaper()
                }
            }, {
                alertDialog.dismiss()
                checkPer(imageView)
                AppInitUtils().setFreshAppWallpaper(
                    detailSetActivity,
                    imageView.drawable as BitmapDrawable
                ) {}
                goEndPaper()
            })
        }
        setCancel.setOnClickListener {
            WallNetDataUtils.postPotIntData(detailSetActivity, "wa9ll")
            if (haveCanNext()) {
                return@setOnClickListener
            }
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    private fun goEndPaper() {
        PaperThreeConstant.canRefreshEndNative = true
        val intent = Intent(this, EndViewActivity::class.java)
        intent.putExtra("typeEnd", "1")
        startActivity(intent)
    }

    private fun checkPer(imageView: ImageView, isLock: Boolean = false) {
        if (Settings.System.canWrite(this)) {
            AppInitUtils().setFreshAppLockWallPaper(
                this,
                imageView.drawable as BitmapDrawable,
                "Lock screen",
                isLock
            )
        } else {
            PaperThreeVariable.isToRequestPer = true
            PermissionUtils.requestWriteSettings(object : PermissionUtils.SimpleCallback {
                override fun onGranted() {
                    PaperThreeVariable.isToRequestPer = false
                    AppInitUtils().setFreshAppLockWallPaper(
                        this@PreViewActivity,
                        curImg as BitmapDrawable, "Lock screen",
                        isLock
                    )
                }

                override fun onDenied() {
                    PaperThreeVariable.isToRequestPer = false
                    ToastUtils.showLong("Permission Denied !")
                }
            })
        }
    }

    private fun onePerInt(nextFun: () -> Unit) {
        if (Settings.System.canWrite(this)) {
            nextFun()
        } else {
            PaperThreeVariable.isToRequestPer = true
            PermissionUtils.requestWriteSettings(object : PermissionUtils.SimpleCallback {
                override fun onGranted() {
                    PaperThreeVariable.isToRequestPer = false
                    nextFun()
                }

                override fun onDenied() {
                    PaperThreeVariable.isToRequestPer = false
                    ToastUtils.showLong("Permission Denied !")
                }
            })
        }
    }
}







