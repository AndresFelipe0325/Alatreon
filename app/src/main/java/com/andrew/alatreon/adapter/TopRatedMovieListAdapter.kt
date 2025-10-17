package com.andrew.alatreon.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrew.alatreon.databinding.ItemMovieMainBinding
import com.andrew.alatreon.model.Movie
import com.andrew.alatreon.util.baseImgUrl
import com.andrew.alatreon.util.getProgressDrawable
import com.andrew.alatreon.util.loadImage

class TopRatedMovieListAdapter(private var topRatedMovies: ArrayList<Movie>) :
    RecyclerView.Adapter<TopRatedMovieListAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateTopRatedMovies(newTopRatedMovies: ArrayList<Movie>) {
        topRatedMovies.clear()
        topRatedMovies.addAll(newTopRatedMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemMovieMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(topRatedMovies[position])
    }

    override fun getItemCount(): Int = topRatedMovies.size

    inner class ViewHolder(private val binding: ItemMovieMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val progressBar = getProgressDrawable(binding.root.context)
        fun bind(movie: Movie) {
            binding.apply {
                movieTitle.text = movie.title
                movieImage.loadImage("${baseImgUrl}${movie.imgUrl}",
                    progressBar)
            }
        }
    }
}