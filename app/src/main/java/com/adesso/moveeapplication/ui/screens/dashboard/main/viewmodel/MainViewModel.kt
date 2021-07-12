package com.adesso.moveeapplication.ui.screens.dashboard.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adesso.moveeapplication.data.components.network.repository.tmdb.api.TmdbApi
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbUser
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.MovieResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesResponse
import com.adesso.moveeapplication.data.model.tmdb.Genre
import com.adesso.moveeapplication.ui.base.BaseViewModel
import com.adesso.moveeapplication.ui.components.generic.GenericItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Burak Karakoca on 23.09.2020.
 */
class MainViewModel : BaseViewModel() {

    val userFavoriteMoviesResponse : MutableLiveData<MovieResponse> = MutableLiveData()
    val userFavoriteTvSeriesResponse : MutableLiveData<TvSeriesResponse> = MutableLiveData()
    val userRatedMoviesResponse : MutableLiveData<MovieResponse> = MutableLiveData()
    val userRatedTvSeriesResponse : MutableLiveData<TvSeriesResponse> = MutableLiveData()

    companion object {
        var genreList: ArrayList<Genre> = arrayListOf()
        var movieVisionList: List<GenericItem> = ArrayList()
        var tvPopularList: List<GenericItem> = ArrayList()

        fun initGenreList(_genreList: List<Genre>) {
            _genreList.forEach { genre ->
                if (!genreList.contains(genre)) {
                    genreList.add(genre)
                }
            }
        }
    }

    private var movieFavoriteCallback = object : Callback<MovieResponse> {
        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
            if (response.isSuccessful) {
                userFavoriteMoviesResponse.value = response.body()
            } else {
                userFavoriteMoviesResponse.value = null
            }
        }
    }

    private var tvFavoriteCallback = object : Callback<TvSeriesResponse> {
        override fun onFailure(call: Call<TvSeriesResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<TvSeriesResponse>, response: Response<TvSeriesResponse>) {
            if (response.isSuccessful) {
                userFavoriteTvSeriesResponse.value = response.body()
            } else {
                userFavoriteTvSeriesResponse.value = null
            }
        }
    }

    private var rateMovieCallback = object : Callback<MovieResponse> {
        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
            if (response.isSuccessful) {
                userRatedMoviesResponse.value = response.body()
            } else {
                userRatedMoviesResponse.value = null
            }
        }
    }

    private var rateTvSeriesCallback = object : Callback<TvSeriesResponse> {
        override fun onFailure(call: Call<TvSeriesResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<TvSeriesResponse>, response: Response<TvSeriesResponse>) {
            if (response.isSuccessful) {
                userRatedTvSeriesResponse.value = response.body()
            } else {
                userRatedTvSeriesResponse.value = null
            }
        }
    }

    fun getMovieFavoritesList() {
        TmdbApi.getFavoriteMovies(movieFavoriteCallback, TmdbUser.user?.id)
    }

    fun getTvFavoritesList() {
        TmdbApi.getFavoriteTvSeries(tvFavoriteCallback, TmdbUser.user?.id)
    }

    fun getRatedMovies() {
        TmdbApi.getRatedMovies(rateMovieCallback, TmdbUser.user?.id)
    }

    fun getRatedTvSeries() {
        TmdbApi.getRatedTvSeries(rateTvSeriesCallback, TmdbUser.user?.id)
    }

}