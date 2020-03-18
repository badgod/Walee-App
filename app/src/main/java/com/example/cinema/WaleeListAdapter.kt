package com.example.cinema

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class WaleeListAdapter(private val walees: List<Walee>) :
        RecyclerView.Adapter<WaleeViewHolder>() {
        override fun onCreateViewHolder(group: ViewGroup, viewType: Int): WaleeViewHolder {
            val view = LayoutInflater.from(group.context)
                .inflate(R.layout.show_list_walee, group, false)
            return WaleeViewHolder(view)
        }
        override fun onBindViewHolder(viewHolder: WaleeViewHolder, position: Int) {
            val walee = walees[position]
            if (walee != null) {
                viewHolder.bind(walee)
            }
        }
    override fun getItemCount(): Int = walees.size
}
