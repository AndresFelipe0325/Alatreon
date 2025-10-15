package com.andrew.alatreon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrew.alatreon.databinding.ItemMovieMainBinding
import com.andrew.alatreon.model.Movie
import com.andrew.alatreon.util.Logger
import com.andrew.alatreon.util.baseImgUrl
import com.andrew.alatreon.util.getProgressDrawable
import com.andrew.alatreon.util.loadImage

class PopularMovieListAdapter(private var popularMovies: ArrayList<Movie>) :
    RecyclerView.Adapter<PopularMovieListAdapter.ViewHolder>(), Logger {

    fun updatePopularMovies(newPopularMovies: ArrayList<Movie>) {
        popularMovies.clear()
        popularMovies.addAll(newPopularMovies)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemMovieMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(popularMovies[position])
    }

    override fun getItemCount(): Int = popularMovies.size

    inner class ViewHolder(val binding: ItemMovieMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val progressBar = getProgressDrawable(binding.root.context)
        fun bind(movie: Movie) {
            logInfo(movie.toString())
            binding.apply {
                movieTitle.text = movie.title
                movieImage.loadImage("${baseImgUrl}${movie.imgUrl}",
                    progressBar )
            }
        }
    }

}

