package what.a.pity.phone.call.paperthree.b.ac

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.adapter.BannerAdapter
import what.a.pity.phone.call.paperthree.c.mlskd.ImageKKKK


class AppImageListAdapter(mData: List<Int?>?) :
    BannerAdapter<Int?, AppImageListAdapter.BannerViewHolder?>(mData) {
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
            val intent = Intent(
                holder.itemView.context, PreViewActivity::class.java
            )
            intent.putExtra("intentImgResID", resID)
            holder.itemView.context.startActivity(intent)
        }
    }

    inner class BannerViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}