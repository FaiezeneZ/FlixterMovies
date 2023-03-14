package com.example.flixtermovies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesNowPlayingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesNowPlayingFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_now_playing_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY

        client["https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i("On Sucess","Successful")
                progressBar.hide()

                val resultsJSON: JSONArray = json.jsonObject.getJSONArray("results") as JSONArray
                Log.i("On Success", "$resultsJSON")
                val moviesRawJSON: String = resultsJSON.toString()
                val gson = Gson()

                val arrayMovieType = object : TypeToken<List<MoviesNowPlaying>>() {}.type
                val models: List<MoviesNowPlaying> = gson.fromJson(moviesRawJSON, arrayMovieType)

                recyclerView.adapter= MoviesNowPlayingRecycleViewAdapter(models, this@MoviesNowPlayingFragment)

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.i("Debug", "Failed")
            }
        }]
    }


}
