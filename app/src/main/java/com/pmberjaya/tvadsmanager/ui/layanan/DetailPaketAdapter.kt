package com.pmberjaya.tvadsmanager.ui.layanan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.api.network.model.LayananLinkPaket

class DetailPaketAdapter(private var itemList: List<LayananLinkPaket>, private val onClickListener: DetailPaketAdapter.OnClickListener): RecyclerView.Adapter<DetailPaketAdapter.DetailPaketViewHolder>(){
    inner class DetailPaketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemText : TextView = itemView.findViewById(R.id.detail_paket_pemeriksaan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPaketViewHolder {
        return DetailPaketViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pemeriksaan, parent, false))
    }

    override fun onBindViewHolder(holder: DetailPaketViewHolder, position: Int) {
        holder.itemText.text = itemList[position].service!!.name

        holder.itemView.setOnClickListener{
            onClickListener.onClick(itemList[position])
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class OnClickListener(val clickListener: (layananlinkPaket : LayananLinkPaket) -> Unit){
        fun onClick(layananlinkPaket: LayananLinkPaket) = clickListener(layananlinkPaket)
    }
}
