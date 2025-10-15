package com.andrew.alatreon.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrew.alatreon.adapter.PopularMovieListAdapter
import com.andrew.alatreon.databinding.FragmentPopularMoviesBinding
import com.andrew.alatreon.model.Movie
import com.andrew.alatreon.util.Logger
import com.andrew.alatreon.viewmodel.MainActivityViewModel
import kotlin.getValue


/**
 * A simple [Fragment] subclass.
 */
class PopularMoviesFragment : Fragment(), Logger {
    //Binding variable
    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding get() = _binding!!
    //Attach the viewModel variable
    private val viewModel: MainActivityViewModel by viewModels()
    // Adapters variable
    private val popularMoviesAdapter = PopularMovieListAdapter(arrayListOf())



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        activity?.setTitle("Popular Movies")
        //Returning the root view of the fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    fun setupView() {
        binding.rvPopularMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = popularMoviesAdapter
        }
        setupViewModelObservers()
        viewModel.getPopularMovies()
    }

    fun setupViewModelObservers() {
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.apply {
                    rvPopularMovies.visibility = View.VISIBLE
                    loadingMovies.visibility = View.GONE
                    errorLoadingMovies.visibility = View.GONE
                }
                popularMoviesAdapter.updatePopularMovies(it)
            }
            else {
                binding.apply {
                    rvPopularMovies.visibility = View.GONE
                    loadingMovies.visibility = View.GONE
                    errorLoadingMovies.visibility = View.VISIBLE
                }
            }
        }

        viewModel.loadingState.observe(viewLifecycleOwner) {
            if (it) {
                binding.apply {
                    rvPopularMovies.visibility = View.GONE
                    loadingMovies.visibility = View.VISIBLE
                    errorLoadingMovies.visibility = View.GONE
                }
            }
        }

        viewModel.errorLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.apply {
                    rvPopularMovies.visibility = View.GONE
                    loadingMovies.visibility = View.GONE
                    errorLoadingMovies.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}