package com.example.peliculas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculas.R
import com.example.peliculas.data.MovieItem
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val movies: List<MovieItem>,
    private val onItemClick: (MovieItem) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val yearTextView: TextView = itemView.findViewById(R.id.yearTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.Title
        holder.yearTextView.text = movie.Year
        Picasso.get().load(movie.Poster).placeholder(R.drawable.ic_launcher_background)
            .into(holder.posterImageView)

        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }
}