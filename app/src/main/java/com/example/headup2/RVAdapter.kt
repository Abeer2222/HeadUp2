package com.example.headup2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(private val celebrities:ArrayList<ArrayList<String>>):
    RecyclerView.Adapter<RVAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name= celebrities[position][1]
        val taboo1= celebrities[position][2]
        val taboo2= celebrities[position][3]
        val taboo3= celebrities[position][4]
        holder.itemView.apply {
            tvname.text= name
            tvtaboo1.text=taboo1
            tvtaboo2.text=taboo2
            tvtaboo3.text=taboo3
        }}

    override fun getItemCount()= celebrities.size

}