package com.pmberjaya.tvadsmanager.ui.layanan

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pmberjaya.tvadsmanager.R
import com.pmberjaya.tvadsmanager.api.network.model.LayananKategori

class KategoriAdapter(private var itemList: List<LayananKategori>, private val onClickListener: OnClickListener): RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>(){
    private var selectedItemPosition: Int = 0

    inner class KategoriViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemImage : ImageView = itemView.findViewById(R.id.kategori_Image)
        val itemText : TextView = itemView.findViewById(R.id.kategori_Text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        return KategoriViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_kategori, parent, false))
    }

    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(itemList[position].image_path)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_no_images))
            .into(holder.itemImage)
        holder.itemText.text = itemList[position].name
        holder.itemView.setOnClickListener{
            onClickListener.onClick(itemList[position])
            selectedItemPosition = holder.adapterPosition
            notifyDataSetChanged()
        }
        if(selectedItemPosition == position)
            holder.itemView.setBackgroundColor(Color.parseColor("#cccccc"))
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class OnClickListener(val clickListener: (layananKategori : LayananKategori) -> Unit){
        fun onClick(layananKategori: LayananKategori) = clickListener(layananKategori)
    }
}
