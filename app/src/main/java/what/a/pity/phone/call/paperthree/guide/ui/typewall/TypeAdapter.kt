package what.a.pity.phone.call.paperthree.guide.ui.typewall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import what.a.pity.phone.call.paperthree.R
import what.a.pity.phone.call.paperthree.fast.KeyData

class TypeAdapter(private val activity: TypeActivity, private val dataList: List<String>) :
    RecyclerView.Adapter<TypeAdapter.ViewHolder>() {
    private var selectedPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.item_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = dataList[position]
        holder.imgType.setImageResource(getTypeImage(item))
        holder.tvName.text = item
        if (position == selectedPosition) {
            holder.imgCheck.setImageResource(R.drawable.icon_check)
        } else {
            holder.imgCheck.setImageResource(R.drawable.icon_dis_check)
        }
        holder.itemView.setOnClickListener {
            // Get the current position from the ViewHolder
            val currentPosition = holder.adapterPosition
            if (currentPosition == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }

            // Update the selected position and notify the adapter to refresh the views
            val previousSelectedPosition = selectedPosition
            selectedPosition = currentPosition

            // Notify the previous selected item to update its view
            if (previousSelectedPosition != RecyclerView.NO_POSITION) {
                notifyItemChanged(previousSelectedPosition)
            }
            // Notify the current selected item to update its view
            notifyItemChanged(selectedPosition)
        }
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgType: ImageView
        var tvName: TextView
        var imgCheck: ImageView

        init {
            imgType = itemView.findViewById(R.id.img_type)
            tvName = itemView.findViewById(R.id.tv_name)
            imgCheck = itemView.findViewById(R.id.img_check)
        }
    }

    private fun getTypeImage(name: String): Int {
        return when (name) {
            "Nature" -> R.drawable.icon_nature
            "Beautiful" -> R.drawable.icon_beautiful
            "Art" -> R.drawable.icon_art
            "Animals" -> R.drawable.icon_animals
            "City" -> R.drawable.icon_city
            "Cool" -> R.drawable.icon_cool
            else -> R.drawable.icon_nature
        }
    }
    fun setCheckTypeName(){
        KeyData.checkTheType = dataList[selectedPosition]
    }
}