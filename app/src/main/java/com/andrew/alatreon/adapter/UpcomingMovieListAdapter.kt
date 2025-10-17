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

class UpcomingMovieListAdapter(private var upcomingMovies: ArrayList<Movie>) :
    RecyclerView.Adapter<UpcomingMovieListAdapter.ViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun updateUpcomingMovies(newUpcomingMovies: ArrayList<Movie>) {
        upcomingMovies.clear()
        upcomingMovies.addAll(newUpcomingMovies)
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
        holder.bind(upcomingMovies[position])
    }

    override fun getItemCount(): Int = upcomingMovies.size

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