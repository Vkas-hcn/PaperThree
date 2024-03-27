package what.a.pity.phone.call.paperthree.b.ac

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.PermissionUtils.SimpleCallback
import com.blankj.utilcode.util.ToastUtils
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.a.app.PaperThreeVariable.Companion.isToRequestPer
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.databinding.DetailAcBinding

class DetailSetActivity : BaseActivity<DetailAcBinding>() {
    override var viewID: Int = R.layout.detail_ac
    val PEIMISSION_CODE = 222

    override fun initV() {
        mBinding.detailImg.setImageResource(intent.getIntExtra("intentImgResID", R.mipmap.qiuqiu1))
    }

    override fun initL() {
        showConfirmationDialog(this, mBinding.detailImg)
    }


    @SuppressLint("MissingInflatedId")
    fun showConfirmationDialog(detailSetActivity: DetailSetActivity, imageView: ImageView) {
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
        alertDialog.setOnDismissListener {
            alertDialog.dismiss()
            detailSetActivity.finish()
        }
        setWallpaperButton.setOnClickListener {
            AppInitUtils().setFreshAppWallpaper(
                detailSetActivity,
                imageView.drawable as BitmapDrawable
            )
            alertDialog.dismiss()
        }

        setLockscreenButton.setOnClickListener {
            checkPer(imageView)
            alertDialog.dismiss()
        }
        setBoth.setOnClickListener {
            checkPer(imageView)
            AppInitUtils().setFreshAppWallpaper(
                detailSetActivity,
                imageView.drawable as BitmapDrawable,
                false
            )
            alertDialog.dismiss()
        }
        setCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun checkPer(imageView: ImageView) {
        if (Settings.System.canWrite(this)) {
            AppInitUtils().setFreshAppLockWallPaper(this, imageView.drawable as BitmapDrawable)
        } else {
            isToRequestPer = true
            PermissionUtils.requestWriteSettings(object : SimpleCallback {
                override fun onGranted() {
                    isToRequestPer = false
                    AppInitUtils().setFreshAppLockWallPaper(
                        this@DetailSetActivity,
                        mBinding.detailImg.drawable as BitmapDrawable
                    )
                }
                override fun onDenied() {
                    isToRequestPer = false
                    ToastUtils.showLong("Permission Denied !")
                }

            })

        }
    }

}

