package com.pmberjaya.tvadsmanager.ui.beranda

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

class PaketAdapter(private var itemList: List<LayananPaket>, private val onClickListener: PaketAdapter.OnClickListener): RecyclerView.Adapter<PaketAdapter.PaketViewHolder>(){
    inner class PaketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemImage : ImageView = itemView.findViewById(R.id.paket_Image)
        val itemName : TextView = itemView.findViewById(R.id.paket_Name)
        val itemServiceName : TextView = itemView.findViewById(R.id.paket_serviceName)
        val itemPrice : TextView = itemView.findViewById(R.id.paket_Price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaketViewHolder {
        return PaketViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_paket, parent, false))
    }

    override fun onBindViewHolder(holder: PaketViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(itemList[position].cover_image_link)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_no_images))
            .into(holder.itemImage)
        holder.itemName.text = itemList[position].name
        holder.itemPrice.text = Utility.convertPrice(itemList[position].price)

        var packageLink = StringBuilder()
        for(i in 0 until itemList[position].package_link.size) {
            packageLink.append(itemList[position].package_link[i].service?.name)
            packageLink.append("\n")
        }
        holder.itemServiceName.text = packageLink.toString()

        holder.itemView.setOnClickListener{
            onClickListener.onClick(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class OnClickListener(val clickListener: (paket : LayananPaket) -> Unit){
        fun onClick(paket: LayananPaket) = clickListener(paket)
    }
}