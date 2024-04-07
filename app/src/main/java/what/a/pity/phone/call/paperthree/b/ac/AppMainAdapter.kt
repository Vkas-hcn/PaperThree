package what.a.pity.phone.call.paperthree.b.ac

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SPUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.c.mlskd.ImageKKKK
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils


class AppMainAdapter(private val activity: MainActivity, private val dataList: List<Int>) :
    RecyclerView.Adapter<AppMainAdapter.ViewHolder>() {
    var jobDetailWall: Job? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.mlskmlsk, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        ImageKKKK.loadRoundedImage(
            holder.itemView.context,
            dataList[position], holder.imageView, 12f
        )
        val resId = dataList[position]
        holder.imageView.setOnClickListener {
            timeShowDetailAd(
                {
                    showJumpDetailAdTime(holder, resId)
                }, {
                    jumpToDetail(holder, resId)

                })
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

    private fun jumpToDetail(holder: ViewHolder, resId: Int) {
        val intent = Intent(holder.itemView.context, PreViewActivity::class.java)
        intent.putExtra("intentImgResID", resId)
        holder.itemView.context.startActivity(intent)
        WallNetDataUtils.postPotIntData(activity,"wa4ll","fa",resId.toString())
    }

    private fun showJumpDetailAd(holder: ViewHolder, resId: Int) {
        if (BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.haveCache && activity.lifecycle.currentState == Lifecycle.State.RESUMED) {
            BIBIUBADDDDUtils.interHaHaHaOPNNOPIN2.showFullScreenAdBIUYBUI(activity) {
                jumpToDetail(holder, resId)
            }
        }
    }

    private fun showJumpDetailAdTime(holder: ViewHolder, resId: Int) {
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