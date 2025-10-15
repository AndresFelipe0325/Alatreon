package com.andrew.alatreon.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrew.alatreon.adapter.UpcomingMovieListAdapter
import com.andrew.alatreon.databinding.FragmentUpcomingMoviesBinding
import com.andrew.alatreon.viewmodel.MainActivityViewModel

/**
 * A simple [Fragment] subclass.
 */
class UpcomingMoviesFragment : Fragment() {
    //Binding variable
    private var _binding: FragmentUpcomingMoviesBinding? = null
    private val binding get() = _binding!!
    //Attach viewModel
    private val viewModel: MainActivityViewModel by viewModels()
    //Adapter variable
    private val upcomingMoviesAdapter = UpcomingMovieListAdapter(arrayListOf())



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpcomingMoviesBinding.inflate(inflater, container, false)
        activity?.setTitle("Upcoming Movies")
        //Returning the root view of the fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    fun setupView() {
        binding.rvUpcomingMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = upcomingMoviesAdapter
        }
        setupViewModelObservers()
        viewModel.getUpcomingMovies()
    }

    fun setupViewModelObservers() {
        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.apply {
                    rvUpcomingMovies.visibility = View.VISIBLE
                    loadingMovies.visibility = View.GONE
                    errorLoadingMovies.visibility = View.GONE
                }
                upcomingMoviesAdapter.updateUpcomingMovies(it)
            }
            else {
                binding.apply {
                    rvUpcomingMovies.visibility = View.GONE
                    loadingMovies.visibility = View.GONE
                    errorLoadingMovies.visibility = View.VISIBLE
                }
            }
        }

        viewModel.loadingState.observe(viewLifecycleOwner) {
            if (it) {
                binding.apply {
                    rvUpcomingMovies.visibility = View.GONE
                    loadingMovies.visibility = View.VISIBLE
                    errorLoadingMovies.visibility = View.GONE
                }
            }
        }

        viewModel.errorLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.apply {
                    rvUpcomingMovies.visibility = View.GONE
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