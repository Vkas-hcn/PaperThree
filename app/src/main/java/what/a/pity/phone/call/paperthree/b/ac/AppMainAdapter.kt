package what.a.pity.phone.call.paperthree.b.ac

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.c.mlskd.ImageKKKK


class AppMainAdapter(private val context: Context, private val dataList: List<Int>) :
    RecyclerView.Adapter<AppMainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.mlskmlsk, parent, false)
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
            val intent = Intent(holder.itemView.context, PreViewActivity::class.java)
            intent.putExtra("intentImgResID", resId)
            holder.itemView.context.startActivity(intent)
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
}