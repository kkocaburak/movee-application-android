package com.adesso.moveeapplication.ui.screens.detail.castdetail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adesso.moveeapplication.data.components.network.repository.tmdb.api.TmdbApi
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.CastDetailResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.CastMoviesResponse
import com.adesso.moveeapplication.ui.screens.detail.castdetail.base.CastBaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Burak Karakoca on 12.10.2020.
 */
class CastViewModel : CastBaseViewModel() {

    val castDetailResponse : MutableLiveData<CastDetailResponse> = MutableLiveData()
    val castMoviesResponse : MutableLiveData<CastMoviesResponse> = MutableLiveData()

    private var tvCastDetailCallback = object : Callback<CastDetailResponse> {
        override fun onFailure(call: Call<CastDetailResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<CastDetailResponse>, response: Response<CastDetailResponse>) {
            if (response.isSuccessful) {
                castDetailResponse.value = response.body()
            } else {
                responseNotSuccessful()
            }
        }
    }
    private var tvCastMoviesCallback = object : Callback<CastMoviesResponse> {
        override fun onFailure(call: Call<CastMoviesResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<CastMoviesResponse>, response: Response<CastMoviesResponse>) {
            if (response.isSuccessful) {
                castMoviesResponse.value = response.body()
            } else {
                responseNotSuccessful()
            }
        }
    }

    fun getTvCastDetail(personId: Int) {
        TmdbApi.getTvSeriesCastDetail(tvCastDetailCallback, personId)
    }
    fun getTvCastMovies(personId: Int) {
        TmdbApi.castMovieDetail(tvCastMoviesCallback, personId)
    }

}