package what.a.pity.phone.call.paperthree.b.ac

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SPUtils
import com.youth.banner.adapter.BannerAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import what.a.pity.phone.call.paperthree.c.mlskd.ImageKKKK
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils


class AppImageListAdapter(private val activity: MainActivity, mData: List<Int?>?) :
    BannerAdapter<Int?, AppImageListAdapter.BannerViewHolder?>(mData) {
    var jobDetailWall: Job? = null

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
                timeShowDetailAd(
                    {
                        showJumpDetailAdTime(holder, it)
                    }, {
                        jumpToDetail(holder, it)
                    })
            }
        }
    }

    private fun jumpToDetail(holder: BannerViewHolder, resID: Int) {
        val intent = Intent(
            holder.itemView.context, PreViewActivity::class.java
        )
        intent.putExtra("intentImgResID", resID)
        holder.itemView.context.startActivity(intent)
    }

    inner class BannerViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    private fun showJumpDetailAd(holder: BannerViewHolder, resId: Int) {
        if (BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.haveCache && activity.lifecycle.currentState == Lifecycle.State.RESUMED) {
            BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.showFullScreenAdBIUYBUI(activity) {
                jumpToDetail(holder, resId)
            }
        }
    }

    private fun showJumpDetailAdTime(holder: BannerViewHolder, resId: Int) {
        if ((!GetWallDataUtils.showAdCenter() || !GetWallDataUtils.showAdBlacklist()) || !BIBIUBADDDDUtils.canShowAD()) {
            jumpToDetail(holder, resId)
            return
        }
        jobDetailWall?.cancel()
        jobDetailWall = null
        jobDetailWall = activity.lifecycleScope.launch {
            activity.mBinding.showLoading = true
            BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.preload(activity)
            try {
                withTimeout(3000L) {
                    while (isActive) {
                        if (BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.haveCache) {
                            showJumpDetailAd(holder, resId)
                            jobDetailWall?.cancel()
                            jobDetailWall = null
                            activity.mBinding.showLoading = false
                            break
                        }
                        delay(500)
                    }
                }
            } catch (e: TimeoutCancellationException) {
                jobDetailWall?.cancel()
                jobDetailWall = null
                jumpToDetail(holder, resId)
                activity.mBinding.showLoading = false
            }
        }
    }

    private fun timeShowDetailAd(nextFun: () -> Unit, applyFun: () -> Unit) {
        val num = GetWallDataUtils.getLocalBlockingData().preenter?.toInt()
        var loadNum = SPUtils.getInstance().getInt(KeyData.local_preenter)
        if (num != 0 && loadNum <= 0) {
            loadNum = num ?: 0
            SPUtils.getInstance().put(KeyData.local_preenter, loadNum)
            nextFun()
            return
        }
        if (loadNum > 0) {
            loadNum--
            SPUtils.getInstance().put(KeyData.local_preenter, loadNum)
            applyFun()
            return
        }
        nextFun()
    }
}