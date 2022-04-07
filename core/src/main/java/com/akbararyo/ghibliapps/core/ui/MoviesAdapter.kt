package com.akbararyo.ghibliapps.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akbararyo.ghibliapps.core.R
import com.akbararyo.ghibliapps.core.databinding.ItemMoviesBinding
import com.akbararyo.ghibliapps.core.domain.model.Movies
import com.bumptech.glide.Glide
import java.util.ArrayList

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ListViewHolder>(){

    private var listData = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Movies>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMoviesBinding.bind(itemView)
        fun bind(data: Movies) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(imgItemPoster)
                tvItemTitle.text = data.title
                tvItemRelease.text = data.release
                tvItemDescription.text = data.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}