package com.adesso.moveeapplication.ui.screens.dashboard.movie.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adesso.moveeapplication.data.components.network.repository.tmdb.api.TmdbApi
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.GenreResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.MovieResponse
import com.adesso.moveeapplication.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Burak Karakoca on 20.10.2020.
 */
class MovieViewModel : BaseViewModel() {

    val movieListResponse : MutableLiveData<MovieResponse> = MutableLiveData()
    val movieGenreListResponse : MutableLiveData<GenreResponse> = MutableLiveData()
    val movieVisionListResponse : MutableLiveData<MovieResponse> = MutableLiveData()

    private var moviePopularCallback = object : Callback<MovieResponse> {
        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
            if (response.isSuccessful) {
                movieListResponse.value = response.body()
            } else {
                movieListResponse.value = null
            }
        }
    }

    private var movieGenreCallback = object : Callback<GenreResponse> {
        override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
            if (response.isSuccessful) {
                movieGenreListResponse.value = response.body()
            } else {
                movieGenreListResponse.value = null
            }
        }
    }

    private var movieVisionCallback = object : Callback<MovieResponse> {
        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
            if (response.isSuccessful) {
                movieVisionListResponse.value = response.body()
            } else {
                movieVisionListResponse.value = null
            }
        }
    }

    fun getMovieGenreList() {
        TmdbApi.getMovieGenre(movieGenreCallback)
    }

    fun getMovieList(page: Int) {
        TmdbApi.getMoviePopular(moviePopularCallback, page)
    }

    fun getMovieVisionList() {
        TmdbApi.getMovieNowPlaying(movieVisionCallback)
    }

}