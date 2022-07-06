package com.pmberjaya.tvadsmanager.ui.layanan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.api.network.model.LayananPaket
import com.pmberjaya.tvadsmanager.util.Utility

class LayananAdapter(private var itemList: List<LayananPaket>, private val onClickListener: LayananAdapter.OnClickListener): RecyclerView.Adapter<LayananAdapter.LayananViewHolder>(){
    inner class LayananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemImage : ImageView = itemView.findViewById(R.id.layanan_kategori_service_Image)
        val itemName : TextView = itemView.findViewById(R.id.layanan_kategori_service_Name)
        val itemDescription : TextView = itemView.findViewById(R.id.layanan_kategori_service_Description)
        val itemPrice : TextView = itemView.findViewById(R.id.layanan_kategori_service_Price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayananViewHolder {
        return LayananViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layanan, parent, false))
    }

    override fun onBindViewHolder(holder: LayananViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(itemList[position].cover_image_link)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_no_images))
            .into(holder.itemImage)
        holder.itemName.text = itemList[position].name
        holder.itemDescription.text = itemList[position].description
        holder.itemPrice.text = Utility.convertPrice(itemList[position].price)

        holder.itemView.setOnClickListener{
            onClickListener.onClick(itemList[position])
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class OnClickListener(val clickListener: (layananPaket : LayananPaket) -> Unit){
        fun onClick(layananPaket: LayananPaket) = clickListener(layananPaket)
    }
}