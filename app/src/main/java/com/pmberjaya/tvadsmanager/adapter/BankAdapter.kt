//package com.pmberjaya.tvadsmanager.adapter
//
//import android.app.Activity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.pmberjaya.tvadsmanager.R
//import com.pmberjaya.tvadsmanager.api.model.BankData
//import com.pmberjaya.tvadsmanager.util.Utility
//import com.squareup.picasso.Picasso
//
//class BankAdapter(
//    var context: Activity,
//    var utility: Utility
//) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    var bankList: MutableList<BankData> = arrayListOf()
//
//    inner class ItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var tvBankName = view.findViewById<TextView>(R.id.tv_bank_name)
//        var tvAccountNumber = view.findViewById<TextView>(R.id.tv_account_number)
//        var tvAccountName = view.findViewById<TextView>(R.id.tv_account_name)
//        var imgBank = view.findViewById<ImageView>(R.id.img_bank)
//    }
//
//    override fun onCreateViewHolder(
//        viewGroup: ViewGroup,
//        viewType: Int
//    ): RecyclerView.ViewHolder {
//        val v = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.bank_item, viewGroup, false)
//        return ItemRowHolder(v)
//    }
//
//    override fun getItemCount(): Int {
//        return bankList.size
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is ItemRowHolder) {
//            Picasso.get().load(bankList[position].imageLink).fit().into(holder.imgBank)
//            holder.tvBankName.setText(bankList[position].bankName)
//            holder.tvAccountName.setText(bankList[position].bankAccountName)
//            holder.tvAccountNumber.setText(bankList[position].bankAccountNumber)
//        }
//    }
//
//    fun getListData(): MutableList<BankData> {
//        return bankList
//    }
//
//    fun setListData(listingList: MutableList<BankData>) {
//        bankList = listingList
//        notifyDataSetChanged()
//    }
//
//    fun changeListData(listingData: BankData, position: Int) {
//        bankList.set(position, listingData)
//        notifyItemChanged(position)
//    }
//}