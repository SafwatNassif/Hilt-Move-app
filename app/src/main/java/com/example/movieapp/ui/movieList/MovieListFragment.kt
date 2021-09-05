package com.example.movieapp.ui.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.ui.MovieViewModel
import com.example.movieapp.ui.movieList.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieListFragment : Fragment() {

    val viewModel: MovieViewModel by activityViewModels()
    val adapter : MovieListAdapter by lazy {
        MovieListAdapter()
    }

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
        initView()
        viewModel.viewState.observe(viewLifecycleOwner, {
            renderView(it)
        })

        // to avoid reload data again.
        if (!(viewModel.viewState.value is MovieListViewState.Success))
            viewModel.getMovieList()
    }

    private fun initView() {
        viewBinding.rvMovieList.adapter = adapter
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
                lifecycleScope.launch {
                    adapter.submitData(viewState.movies)
                }
            }
        }
    }

}