package what.a.pity.phone.call.paperthree.guide.ui.con

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.b.ac.light.LightWallActivity
import what.a.pity.phone.call.paperthree.fast.KeyData

class GuideAdapter (private val activity: WallPaperActivity, private val dataList: List<GuideDataBean>) :
    RecyclerView.Adapter<GuideAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.item_guide, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val resId = dataList[position]

        holder.imageTop.setImageResource(resId.wallData)
        holder.textIntroduce.text = resId.introduce
        when(position){
            0->{
                holder.view1.background = ContextCompat.getDrawable(activity,R.drawable.ic_guide_oval_check)
                holder.view2.background = ContextCompat.getDrawable(activity,R.drawable.ic_guide_oval_diss)
                holder.view3.background = ContextCompat.getDrawable(activity,R.drawable.ic_guide_oval_diss)
            }
            1->{
                holder.view1.background = ContextCompat.getDrawable(activity,R.drawable.ic_guide_oval_diss)
                holder.view2.background = ContextCompat.getDrawable(activity,R.drawable.ic_guide_oval_check)
                holder.view3.background = ContextCompat.getDrawable(activity,R.drawable.ic_guide_oval_diss)
            }
            2->{
                holder.view1.background = ContextCompat.getDrawable(activity,R.drawable.ic_guide_oval_diss)
                holder.view2.background = ContextCompat.getDrawable(activity,R.drawable.ic_guide_oval_diss)
                holder.view3.background = ContextCompat.getDrawable(activity,R.drawable.ic_guide_oval_check)
            }
        }
    }



    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageTop: ImageView
        var textIntroduce: TextView
        var view1: View
        var view2: View
        var view3: View

        init {
            imageTop = itemView.findViewById(R.id.img_bg_guide)
            textIntroduce = itemView.findViewById(R.id.tv_guide_title)
            view1 = itemView.findViewById(R.id.view_1)
            view2 = itemView.findViewById(R.id.view_2)
            view3 = itemView.findViewById(R.id.view_3)
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