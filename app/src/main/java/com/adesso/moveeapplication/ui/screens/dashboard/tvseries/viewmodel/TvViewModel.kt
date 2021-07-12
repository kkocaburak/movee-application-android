package com.adesso.moveeapplication.ui.screens.dashboard.tvseries.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adesso.moveeapplication.data.components.network.repository.tmdb.api.TmdbApi
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.GenreResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesResponse
import com.adesso.moveeapplication.data.model.tmdb.TvSeries
import com.adesso.moveeapplication.ui.base.BaseViewModel
import com.adesso.moveeapplication.ui.components.generic.TvSeriesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Burak Karakoca on 20.10.2020.
 */
class TvViewModel : BaseViewModel() {

    val tvGenreListResponse : MutableLiveData<GenreResponse> = MutableLiveData()
    val tvPopularListResponse : MutableLiveData<TvSeriesResponse> = MutableLiveData()
    val tvTopRatedListResponse : MutableLiveData<TvSeriesResponse> = MutableLiveData()
    val selectedTvSeries : MutableLiveData<Int> = MutableLiveData()

    private var tvGenreCallback = object : Callback<GenreResponse> {
        override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
            if (response.isSuccessful) {
                tvGenreListResponse.value = response.body()
            } else {
                tvGenreListResponse.value = null
            }
        }
    }

    private var tvPopularCallback = object : Callback<TvSeriesResponse> {
        override fun onFailure(call: Call<TvSeriesResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<TvSeriesResponse>, response: Response<TvSeriesResponse>) {
            if (response.isSuccessful) {
                tvPopularListResponse.value = response.body()
            } else {
                tvPopularListResponse.value = null
            }
        }
    }

    private var tvTopRatedCallback = object : Callback<TvSeriesResponse> {
        override fun onFailure(call: Call<TvSeriesResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<TvSeriesResponse>, response: Response<TvSeriesResponse>) {
            if (response.isSuccessful) {
                tvTopRatedListResponse.value = response.body()
            } else {
                tvTopRatedListResponse.value = null
            }
        }
    }

    fun getTvGenreList() {
        TmdbApi.getTvGenre(tvGenreCallback)
    }

    fun getTvPopularList() {
        TmdbApi.getTvSeriesPopular(tvPopularCallback)
    }

    fun getTvTopRatedList(page: Int) {
        TmdbApi.getTvSeriesTopRated(tvTopRatedCallback, page)
    }

}