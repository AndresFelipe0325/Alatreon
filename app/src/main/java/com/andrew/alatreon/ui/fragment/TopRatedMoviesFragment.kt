package com.andrew.alatreon.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrew.alatreon.R
import com.andrew.alatreon.databinding.FragmentTopRatedMoviesBinding


/**
 * A simple [Fragment] subclass.
 */
class TopRatedMoviesFragment : Fragment() {
    //Binding variable
    private var _binding: FragmentTopRatedMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        _binding = FragmentTopRatedMoviesBinding.inflate(inflater, container, false)
        //Returning the root view of the fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}