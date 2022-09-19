package com.example.farmerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmerapp.models.CropContent
import kotlinx.android.synthetic.main.crop_content.view.*

class CropContentAdapter(
    var content : List<CropContent>,
    private val listner : CropContentClicked
) : RecyclerView.Adapter<CropContentAdapter.CropContentViewHolder>() {

    inner class CropContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CropContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crop_content,parent,false)
        val viewHolder = CropContentViewHolder(view)

        view.setOnClickListener {
            listner.onItemClicked(content[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CropContentViewHolder, position: Int) {
        holder.itemView.apply {
            tvCrop.text = content[position].cropName
        }
    }

    override fun getItemCount(): Int {
       return content.size
    }

}

interface CropContentClicked{
    fun onItemClicked(item : CropContent)
}