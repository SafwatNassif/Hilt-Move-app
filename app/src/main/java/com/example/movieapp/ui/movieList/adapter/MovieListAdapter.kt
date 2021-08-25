package com.example.movieapp.ui.movieList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.model.MovieItem
import com.example.movieapp.databinding.ItemMovieLayoutBinding
import kotlin.reflect.KFunction1

class MovieListAdapter(private val movies: List<MovieItem>, val onItemClick: KFunction1<MovieItem, Unit>) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {


    override fun getItemCount() = movies.size


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

        holder.bind(item = movies[position])
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


            view.cvContainer.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }
}