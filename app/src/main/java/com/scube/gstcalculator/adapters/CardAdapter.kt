package com.scube.gstcalculator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scube.gstcalculator.R
import com.scube.gstcalculator.models.Card
import kotlinx.android.synthetic.main.row_card_item.view.*

class CardAdapter(private val context: Context, private var list: ArrayList<Card>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_card_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        if(holder is MyViewHolder){
            holder.itemView.apply {
                cardTitle.text = item.title
                cardValue.text = item.value
            }
        }
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

}