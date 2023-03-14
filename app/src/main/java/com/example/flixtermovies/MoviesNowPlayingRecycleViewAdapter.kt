package com.example.flixtermovies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesNowPlayingRecycleViewAdapter(
    private val movies: List<MoviesNowPlaying>,
    private val mListener: MoviesNowPlayingFragment
) : RecyclerView.Adapter<MoviesNowPlayingRecycleViewAdapter.MovieViewHolder>()
{
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movies_now_playing, parent, false)
        return MovieViewHolder(view)
    }

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: MoviesNowPlaying? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.movie_tittle) as TextView
        val mMovieCover: ImageView = mView.findViewById(R.id.movie_cover) as ImageView
        val mMovieDescription: TextView = mView.findViewById(R.id.movie_description) as TextView

        override fun toString(): String {
            return mMovieTitle.toString()
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.description
        Log.i("Debug", "$movie.description")
        Log.i("Debug", "https://image.tmbd.org/t/p/w500/${movie.movieCoverUrl}")

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + movie.movieCoverUrl)
            .centerInside()
            .into(holder.mMovieCover)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
