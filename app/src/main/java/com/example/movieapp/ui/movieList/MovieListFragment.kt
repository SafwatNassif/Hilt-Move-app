package com.example.movieapp.ui.movieList

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.data.model.MovieItem
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.ui.MovieViewModel
import com.example.movieapp.ui.movieList.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieListFragment : Fragment() {

    val viewModel: MovieViewModel by activityViewModels()

    lateinit var viewBinding: FragmentMovieListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMovieListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, {
            renderView(it)
        })

        // to avoid reload data again.
        if (viewModel.viewState.value !is MovieListViewState.Success)
            viewModel.getMovieList()
    }

    private fun renderView(viewState: MovieListViewState?) {
        when (viewState) {
            is MovieListViewState.Loading -> {
                viewBinding.shimmerMovieList.root.visibility = if (viewState.show) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
            is MovieListViewState.Success -> {
                viewBinding.rvMovieList.adapter =
                    MovieListAdapter(viewState.movies, this::onItemClick)
            }
        }
    }

    private fun onItemClick(item: MovieItem) {
        findNavController().navigate(R.id.action_movieListFragment_to_detailsFragment)
    }

}