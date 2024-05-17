package what.a.pity.phone.call.paperthree.b.ac

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SPUtils
import com.youth.banner.adapter.BannerAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.c.mlskd.ImageKKKK
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.KeyData.getFileName
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils


class AppImageListAdapter(private val activity: MainActivity,private val mData: List<Int>) :
    BannerAdapter<Int?, AppImageListAdapter.BannerViewHolder?>(mData) {
    var jobDetailWall: Job? = null
    private var jobRvAd: Job? = null

    override fun onCreateHolder(
        parent: ViewGroup, viewType: Int
    ): BannerViewHolder {
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }


    override fun onBindView(
        holder: BannerViewHolder?, data: Int?, position: Int, size: Int
    ) {
        ImageKKKK.loadRoundedImage(
            holder?.itemView?.context, data, holder?.imageView, 12f
        )
        val resID: Int? = data
        holder?.imageView?.setOnClickListener { v: View? ->
            resID?.let {
                WallNetDataUtils.postImageNameData(activity,"wa2ll",it)
                if (KeyData.isLockWall(activity, resID)) {
                    waitForRvData(position)
                } else {
                    jumpToDetail(holder, resID)
                }
            }
        }
    }
    private fun showRvAd(position: Int) {
        activity.mBinding.showLoading = false
        BIBIUBADDDDUtils.rewAd.showFullScreenAdBIUYBUI(activity) {
            getRvFinish(position)
        }
    }
    private fun getRvFinish(position: Int) {
        activity.changingLockData(mData[position],true)
        notifyItemChanged(position)
        Toast.makeText(activity, "You have unlocked free wallpaper!", Toast.LENGTH_SHORT).show()
    }

    private fun waitForRvData(position: Int) {
        activity.mBinding.showLoading = true
        jobRvAd?.cancel()
        jobRvAd = AppInitUtils().countDown(100, 150, MainScope(), {
            if (it < 100 && BIBIUBADDDDUtils.rewAd.haveCache) {
                Log.e("TAG", "waitForRvData: $it")
                jobRvAd?.cancel()
                showRvAd(position)
            }
        }, {
            jobRvAd?.cancel()
            activity.mBinding.showLoading = false
            getRvFinish(position)
        })
    }
    private fun jumpToDetail(holder: BannerViewHolder, resID: Int) {
        val intent = Intent(
            holder.itemView.context, PreViewActivity::class.java
        )
        intent.putExtra("intentImgResID", resID)
        holder.itemView.context.startActivity(intent)
        WallNetDataUtils.postImageNameData(activity,"wa4ll",resID)
    }

    inner class BannerViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView)

}