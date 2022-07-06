package com.pmberjaya.tvadsmanager.ui.beranda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.api.network.model.Banner

class BannerAdapter(private var itemList: List<Banner>): RecyclerView.Adapter<BannerAdapter.BannerViewHolder>(){
    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemImage : ImageView = itemView.findViewById(R.id.banner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false))
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(itemList[position].cover_image_link)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_no_images))
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}