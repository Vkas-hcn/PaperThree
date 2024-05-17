package what.a.pity.phone.call.paperthree.b.ac

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.a.utils.AppInitUtils
import what.a.pity.phone.call.paperthree.b.ac.light.LightWallActivity
import what.a.pity.phone.call.paperthree.c.mlskd.ImageKKKK
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils

class LightMainAdapter(private val activity: MainActivity, private val dataList: List<Int>) :
    RecyclerView.Adapter<LightMainAdapter.ViewHolder>() {
    private var jobRvAd: Job? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.item_light, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val resId = dataList[position]
        ImageKKKK.loadRoundedImage(
            holder.itemView.context,
            dataList[position], holder.imageView, 4f
        )
        holder.imgClock.isVisible = KeyData.isLockWall(activity, dataList[position], false)
        holder.imgAd.isVisible = KeyData.isLockWall(activity, dataList[position], false)
        holder.imageView.setOnClickListener {
            if (holder.imgClock.isVisible) {
                waitForRvData(position)
            } else {
                jumpToDetail(holder, resId, position)
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
        KeyData.setLocalRvData(activity, dataList[position])
        notifyItemChanged(position)
        Toast.makeText(activity, "You have unlocked free EdgeLighting!", Toast.LENGTH_SHORT).show()
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

    private fun jumpToDetail(holder: ViewHolder, resId: Int, position: Int) {
        KeyData.isImagePosApp = position
        val intent = Intent(holder.itemView.context, LightWallActivity::class.java)
        val fileName = activity.resources.getResourceName(resId).substringAfterLast("/")
        intent.putExtra(
            "lightWall", fileName
        )
        holder.itemView.context.startActivity(intent)
    }


}