package com.adesso.moveeapplication.ui.screens.detail.moviedetail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.data.components.network.repository.tmdb.api.TmdbApi
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.MovieCastResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.MovieDetailResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.PostResponse
import com.adesso.moveeapplication.ui.screens.detail.moviedetail.base.MovieDetailBaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Burak Karakoca on 5.10.2020.
 */
class MovieDetailViewModel : MovieDetailBaseViewModel() {

    val movieCastResponse : MutableLiveData<MovieCastResponse> = MutableLiveData()
    val movieDetailResponse : MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val postMovieRateResponse : MutableLiveData<PostResponse> = MutableLiveData()

    private var movieCastCallback = object : Callback<MovieCastResponse> {
        override fun onFailure(call: Call<MovieCastResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<MovieCastResponse>, response: Response<MovieCastResponse>) {
            if (response.isSuccessful) {
                movieCastResponse.value = response.body()
            } else {
                responseNotSuccessful()
            }
        }
    }
    private var movieDetailCallback = object : Callback<MovieDetailResponse> {
        override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
            if (response.isSuccessful) {
                movieDetailResponse.value = response.body()
            } else {
                responseNotSuccessful()
            }
        }
    }

    private var moviePostRateCallback = object : Callback<PostResponse> {
        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
            if (response.isSuccessful) {
                postMovieRateResponse.value = response.body()
            } else {
                postMovieRateResponse.value = null
            }
        }
    }

    fun getMovieCastList(movieId: Int) {
        TmdbApi.getMovieCast(movieCastCallback, movieId)
    }
    fun getMovieDetailList(movieId: Int) {
        TmdbApi.getMovieDetail(movieDetailCallback, movieId)
    }

    fun postMovieRate(movieId: Int, rate: Int) {
        val rateValue : Double = rate.toDouble() * MoveeConstants.INT_TWO
        TmdbApi.postMovieRate(moviePostRateCallback, movieId , rateValue)
    }
}