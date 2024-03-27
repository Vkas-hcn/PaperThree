package what.a.pity.phone.call.paperthree.b.ac

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.databinding.SetPageBinding

class SettingActivity : BaseActivity<SetPageBinding>() {
    override var viewID: Int
        get() = R.layout.set_page
        set(value) {}
    override fun initV() {

    }
    override fun initL() {
        mBinding.close.setOnClickListener { finish() }

        mBinding.kfpesfkpefkmpeoPrivacyItem.setOnClickListener {
            this.startActivity(
                Intent(
                    this, WActivity::class.java
                )
            )
        }
        mBinding.kfpesfkpefkmpeoUpdateItem.setOnClickListener {
            val appPackageName = this.packageName
            try {
                val launchIntent = Intent()
                launchIntent.data = Uri.parse("market://details?id=$appPackageName")
                this.startActivity(launchIntent)
            } catch (anfe: ActivityNotFoundException) {
                this.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }
        mBinding.kfpesfkpefkmpeoShareItem.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=${this.packageName}"
            )
            sendIntent.type = "text/plain"
            val shareIntent = Intent.createChooser(sendIntent, null)
            this.startActivity(shareIntent)
        }
    }
}