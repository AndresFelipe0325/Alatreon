package com.andrew.alatreon.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrew.alatreon.adapter.TopRatedMovieListAdapter
import com.andrew.alatreon.databinding.FragmentTopRatedMoviesBinding
import com.andrew.alatreon.viewmodel.MainActivityViewModel
import kotlin.getValue


/**
 * A simple [Fragment] subclass.
 */
class TopRatedMoviesFragment : Fragment() {
    //Binding variable
    private var _binding: FragmentTopRatedMoviesBinding? = null
    private val binding get() = _binding!!
    //Attach the viewModel variable
    private val viewModel: MainActivityViewModel by viewModels()
    //TopRatedMovies adapter variable
    private val topRatedMoviesAdapter = TopRatedMovieListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        _binding = FragmentTopRatedMoviesBinding.inflate(inflater, container, false)
        activity?.setTitle("Top Rated Movies")
        //Returning the root view of the fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    fun setupView() {
        binding.rvTopRatedMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = topRatedMoviesAdapter
        }
        setupViewModelObservers()
        viewModel.getTopRatedMovies()
    }

    fun setupViewModelObservers() {
        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                binding.apply {
                    rvTopRatedMovies.visibility = View.VISIBLE
                    loadingMovies.visibility = View.GONE
                    errorLoadingMovies.visibility = View.GONE
                }
                topRatedMoviesAdapter.updateTopRatedMovies(it)
            }
            else {
                binding.apply {
                    rvTopRatedMovies.visibility = View.GONE
                    loadingMovies.visibility = View.GONE
                    errorLoadingMovies.visibility = View.VISIBLE
                }
            }
        }

        viewModel.loadingState.observe(viewLifecycleOwner) {
            if(it) {
                binding.apply {
                    rvTopRatedMovies.visibility = View.GONE
                    loadingMovies.visibility = View.VISIBLE
                    errorLoadingMovies.visibility = View.GONE
                }
            }
        }

        viewModel.errorLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.apply {
                    rvTopRatedMovies.visibility = View.GONE
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