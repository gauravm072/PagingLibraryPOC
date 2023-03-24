package com.example.paginglibrarypoc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paginglibrarypoc.R
import com.example.paginglibrarypoc.model.ModelClass

class RepoListAdapter : PagingDataAdapter<ModelClass, RepoListAdapter.ViewHolder>(COMPARATOR) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)
        holder.txtName.text = repo?.full_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_view, parent, false)
        return ViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ModelClass>() {
            override fun areItemsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean =
                oldItem.full_name == newItem.full_name

            override fun areContentsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean =
                oldItem == newItem

        }
    }
}