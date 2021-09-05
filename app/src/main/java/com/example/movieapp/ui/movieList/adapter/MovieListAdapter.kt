package com.example.movieapp.ui.movieList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.model.MovieItem
import com.example.movieapp.databinding.ItemMovieLayoutBinding

class MovieListAdapter:
    PagingDataAdapter<MovieItem, MovieListAdapter.MovieViewHolder>(REPO_COMPARATOR) {


    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem == newItem
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieViewHolder = ItemMovieLayoutBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(movieViewHolder)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movieContainer.animation = AnimationUtils.loadAnimation(
            holder.view.root.context,
            R.anim.rv_animation
        )

        holder.bind(item = getItem(position)!!)
    }


    inner class MovieViewHolder(val view: ItemMovieLayoutBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(item: MovieItem) {
            view.tvMovieName.text = item.title
            view.rate.progress = (item.vote_average * 10).toInt()
            view.tvRate.text = "${(item.vote_average * 10).toInt()}"
            Glide.with(view.root.context)
                .load("http://image.tmdb.org/t/p/w500" + item.poster_path)
                .into(view.ivMovieImage)


        }
    }
}