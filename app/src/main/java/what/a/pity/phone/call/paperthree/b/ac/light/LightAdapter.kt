package what.a.pity.phone.call.paperthree.b.ac.light

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
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
import what.a.pity.phone.call.paperthree.b.ac.MainActivity
import what.a.pity.phone.call.paperthree.b.ac.PreViewActivity
import what.a.pity.phone.call.paperthree.c.mlskd.ImageKKKK
import what.a.pity.phone.call.paperthree.d.ad.baseeeee.BIBIUBADDDDUtils
import what.a.pity.phone.call.paperthree.fast.KeyData
import what.a.pity.phone.call.paperthree.fast.utils.GetWallDataUtils
import what.a.pity.phone.call.paperthree.fast.utils.WallNetDataUtils


class LightAdapter(
    private val activity: LightWallActivity,
    private val dataList: MutableList<LightWallBean>
) :
    RecyclerView.Adapter<LightAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.item_light_wall, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val resId = dataList[position].imageData
        val haveCheck = dataList[position].haveCheck
        holder.imageView.setImageResource(resId)
        if (resId == 0) {
            holder.imageView.setImageResource(0)
            holder.imgChooe.visibility = View.VISIBLE
            if (haveCheck) {
                holder.imgChooe.setImageResource(R.drawable.ic_no_chooe_2)
                holder.flMain.background =
                    ContextCompat.getDrawable(activity, R.drawable.bg_light_chooe)
            } else {
                holder.imgChooe.setImageResource(R.drawable.ic_no_chooe)
                holder.flMain.background = ContextCompat.getDrawable(activity, R.drawable.bg_light)
            }
        } else {
            if (haveCheck) {
                holder.imgChooe.visibility = View.VISIBLE
                holder.viewChooe.visibility = View.VISIBLE
                holder.imgChooe.setImageResource(R.drawable.ic_chooe)
                holder.flMain.background =
                    ContextCompat.getDrawable(activity, R.drawable.bg_light_chooe)
                holder.viewChooe.visibility = View.VISIBLE
            } else {
                holder.imgChooe.visibility = View.GONE
                holder.viewChooe.visibility = View.GONE
                holder.flMain.background = null
            }
        }
        holder.imageView.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var flMain: FrameLayout
        var imgChooe: ImageView
        var viewChooe: View

        init {
            imageView = itemView.findViewById(R.id.img_main)
            flMain = itemView.findViewById(R.id.fl_main)
            imgChooe = itemView.findViewById(R.id.img_chooe)
            viewChooe = itemView.findViewById(R.id.view_chooe)
        }
    }




}