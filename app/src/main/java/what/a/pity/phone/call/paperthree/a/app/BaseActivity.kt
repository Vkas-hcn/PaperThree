package what.a.pity.phone.call.paperthree.a.app

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import com.gyf.immersionbar.ImmersionBar
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity(), Init {
    abstract var viewID: Int

    var isShowPage = false

    lateinit var mBinding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInitUtils().screenAndroid(this)
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        mBinding = DataBindingUtil.setContentView(this, viewID)
        mBinding.lifecycleOwner = this
        setContentView(mBinding.root)
        ImmersionBar.with(this).fullScreen(false).navigationBarColor(R.color.haahaha)
            .statusBarColor(R.color.haahaha).init()
        initV()
        initL()
    }

    override fun onResume() {
        super.onResume()
        isShowPage = true
    }

    override fun onStop() {
        super.onStop()
        isShowPage = false
    }


    fun isActivityResumed(): Boolean {
        return Lifecycle.State.RESUMED == lifecycle.currentState && isShowPage
    }



}

interface Init {
    fun initV()
    fun initL()
}