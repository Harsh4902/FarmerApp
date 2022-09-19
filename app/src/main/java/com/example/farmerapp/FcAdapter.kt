package com.example.farmerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmerapp.models.FinalCategory
import kotlinx.android.synthetic.main.crop_content.view.*

class FcAdapter(
    val content : List<FinalCategory>,
    private val listner : CategoryClicked
): RecyclerView.Adapter<FcAdapter.FcViewHolde>(){

    inner class FcViewHolde(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FcViewHolde {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crop_content,parent,false)
        val viewHolder = FcViewHolde(view)

        view.setOnClickListener {
            listner.onItemClicked(content[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FcViewHolde, position: Int) {
        holder.itemView.apply {
            tvCrop.text = content[position].ctName
        }
    }

    override fun getItemCount(): Int {
        return content.size
    }

}

interface CategoryClicked{
    fun onItemClicked(item : FinalCategory)
}