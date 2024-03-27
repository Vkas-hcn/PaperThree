package what.a.pity.phone.call.paperthree.b.ac

import android.webkit.WebViewClient
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.app.BaseActivity
import what.a.pity.phone.call.paperthree.databinding.MmmmmaaaaaBinding

class WActivity : BaseActivity<MmmmmaaaaaBinding>() {
    override var viewID: Int
        get() = R.layout.mmmmmaaaaa
        set(value) {}

    override fun initV() {
        mBinding.webbbbbbbb.canGoBack()
        mBinding.webbbbbbbb.webViewClient = object : WebViewClient() {}
        mBinding.webbbbbbbb.loadUrl("https://sites.google.com/view/facile-wallpaper/home")
    }

    override fun initL() {
        mBinding.close.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.webbbbbbbb.destroy()
    }
}