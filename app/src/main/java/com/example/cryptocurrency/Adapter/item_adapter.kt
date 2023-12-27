package com.example.cryptocurrency.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrency.DataClass.cryptoinfo
import com.example.cryptocurrency.databinding.ItemLayoutBinding

class item_adapter(private val context: Context, private var ratesmap:Map<String,Double>, private val detailedlist:List<Pair<String,cryptoinfo>>)
                                                                       :RecyclerView.Adapter<item_adapter.viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        return viewholder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return detailedlist.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val item = detailedlist[position]
        val symbol = item.first
        val rate:Double? = ratesmap[symbol]
        val roundedrate = String.format("%.6f", rate)  //rounding off to 6 decimal places
        val exchrate = String.format("Exchange Rate: $roundedrate",2.5)
        val fullname = String.format("Full Name: " + item.second.name_full,2.5)


        holder.itemLayoutBinding.rateTv.text = exchrate
        holder.itemLayoutBinding.fullnameTv.text = fullname
        Glide.with(context).load(item.second.icon_url).into(holder.itemLayoutBinding.currencyIv)
        holder.itemLayoutBinding.nameTv.text = item.second.symbol


    }



    class viewholder(var itemLayoutBinding: ItemLayoutBinding):RecyclerView.ViewHolder(itemLayoutBinding.root)
}