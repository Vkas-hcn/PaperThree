package what.a.pity.phone.call.paperthree.b.ac

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SPUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.c.mlskd.ImageKKKK
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils


class AppMainAdapter(private val activity: MainActivity, private val dataList: MutableList<Int>) :
    RecyclerView.Adapter<AppMainAdapter.ViewHolder>() {
    private var jobRvAd: Job? = null
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
        holder.imgClock.isVisible = KeyData.isLockWall(activity, dataList[position])
        holder.imgAd.isVisible = KeyData.isLockWall(activity, dataList[position])

        val resId = dataList[position]
        holder.imageView.setOnClickListener {
            if (holder.imgClock.isVisible) {
                waitForRvData(position)
            } else {
                jumpToDetail(holder, resId)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var imgClock: ImageView
        var imgAd: ImageView

        init {
            imageView = itemView.findViewById(R.id.imageView)
            imgClock = itemView.findViewById(R.id.img_clock)
            imgAd = itemView.findViewById(R.id.img_ad)
        }
    }

    private fun showRvAd(position: Int) {
        activity.mBinding.showLoading = false
        BIBIUBADDDDUtils.rewAd.showFullScreenAdBIUYBUI(activity) {
            getRvFinish(position)
        }
    }

    private fun getRvFinish(position: Int) {
        activity.changingLockData(dataList[position],false)
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

    private fun jumpToDetail(holder: ViewHolder, resId: Int) {
        val intent = Intent(holder.itemView.context, PreViewActivity::class.java)
        intent.putExtra("intentImgResID", resId)
        holder.itemView.context.startActivity(intent)
        WallNetDataUtils.postImageNameData(activity, "wa4ll", resId)
    }

    fun upDataList(newListData:List<Int>){
        dataList.clear()
        dataList.addAll(newListData)
        notifyDataSetChanged()
    }
}