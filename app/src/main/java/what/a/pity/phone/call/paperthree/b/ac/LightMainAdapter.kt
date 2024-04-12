package what.a.pity.phone.call.paperthree.b.ac

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.b.ac.light.LightWallActivity
import what.a.pity.phone.call.paperthree.c.mlskd.ImageKKKK
import what.a.pity.phone.call.paperthree.fast.KeyData

class LightMainAdapter(private val activity: MainActivity, private val dataList: List<Int>) :
    RecyclerView.Adapter<LightMainAdapter.ViewHolder>() {
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
        holder.imageView.setOnClickListener {
            jumpToDetail(holder, resId)
            KeyData.isImagePos = position
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
        val intent = Intent(holder.itemView.context, LightWallActivity::class.java)
        intent.putExtra(
            "lightWall", activity.resources.getResourceName(resId)
        )
        holder.itemView.context.startActivity(intent)
    }


}