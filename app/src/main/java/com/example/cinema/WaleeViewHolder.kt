package com.example.cinema

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.show_list_walee.view.*

    class WaleeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView = itemView.idTextView
        private val quoteTextView = itemView.quoteTextView
        private val sourceTextView = itemView.sourceTextView
        private val articleTextView = itemView.articleTextView
        fun bind(walee: Walee) {

            val article = "👦: "+ (walee.article)?:"-"
            val source = "🌐: "+ (walee.source)?:"-"

            idTextView!!.text = walee.id.toString()
            quoteTextView.text = walee.quote
            sourceTextView.text = source
            articleTextView.text = article

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EditWaleeActivity::class.java)
                intent.putExtra(EditWaleeActivity.EXTRA_STUDENT_ID, walee.id.toString())
                itemView.context.startActivity(intent)
            }
        }//bind
    }
