package what.a.pity.phone.call.paperthree.b.ac

import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Lifecycle
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.databinding.PreviewLayoutBinding

class PreViewActivity : BaseActivity<PreviewLayoutBinding>(), EasyPermissions.PermissionCallbacks {

    override var viewID: Int
        get() = R.layout.preview_layout
        set(value) {}
    private var curImg = R.mipmap.qiuqiu1

    override fun initV() {
        curImg = intent.getIntExtra("intentImgResID", R.mipmap.qiuqiu1)
        mBinding.curImage.setImageResource(curImg)
        BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.preload(this)
    }

    override fun initL() {
        mBinding.downloadCl.setOnClickListener {
            AppInitUtils().saveImg(curImg, this)
        }
        mBinding.clApply.setOnClickListener {
            AppInitUtils().goSet(curImg, this)
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

    private fun smklllllcmnjiaecnuibbaiusybdcaiyvba() {
        if (BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.haveCache && lifecycle.currentState == Lifecycle.State.RESUMED) {
            BIBIUBADDDDUtils.interHaHaHaOPNNOPIN.showFullScreenAdBIUYBUI(this) {
                finish()
            }
        } else finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        AppInitUtils().saveFreshAppImageToGallery(this, curImg)
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
}







