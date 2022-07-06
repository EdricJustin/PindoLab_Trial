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
import com.pmberjaya.tvadsmanager.api.network.model.News

class NewsAdapter(private var itemList: List<News>, private val onClickListener: OnClickListener): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){
    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemImage : ImageView = itemView.findViewById(R.id.news_Image)
        val itemTitle : TextView = itemView.findViewById(R.id.news_Title)
        val itemDescription : TextView = itemView.findViewById(R.id.news_Description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(itemList[position].cover_image)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.ic_no_images))
            .into(holder.itemImage)
        holder.itemTitle.text = itemList[position].title
        holder.itemDescription.text = itemList[position].description
        holder.itemView.setOnClickListener{
            onClickListener.onClick(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class OnClickListener(val clickListener: (news: News) -> Unit){
        fun onClick(news: News) = clickListener(news)
    }
}

