package com.adesso.moveeapplication.ui.screens.detail.tvdetail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.data.components.network.repository.tmdb.api.TmdbApi
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.PostResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesCastResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesDetailResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesResponse
import com.adesso.moveeapplication.data.model.tmdb.ITvSeries
import com.adesso.moveeapplication.ui.screens.detail.tvdetail.base.TvDetailBaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Burak Karakoca on 9.10.2020.
 */
class TvSeriesDetailViewModel : TvDetailBaseViewModel() {
    val tvCastResponse : MutableLiveData<TvSeriesCastResponse> = MutableLiveData()
    val tvDetailResponse : MutableLiveData<TvSeriesDetailResponse> = MutableLiveData()
    val postTvRateResponse : MutableLiveData<PostResponse> = MutableLiveData()

    private var tvDetailCallback = object : Callback<TvSeriesDetailResponse> {
        override fun onFailure(call: Call<TvSeriesDetailResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<TvSeriesDetailResponse>, response: Response<TvSeriesDetailResponse>) {
            if (response.isSuccessful) {
                tvDetailResponse.value = response.body()
            } else {
                responseNotSuccessful()
            }
        }
    }
    private var tvCastCallback = object : Callback<TvSeriesCastResponse> {
        override fun onFailure(call: Call<TvSeriesCastResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<TvSeriesCastResponse>, response: Response<TvSeriesCastResponse>) {
            if (response.isSuccessful) {
                tvCastResponse.value = response.body()
            } else {
                responseNotSuccessful()
            }
        }
    }

    private var tvPostRateCallback = object : Callback<PostResponse> {
        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
            if (response.isSuccessful) {
                postTvRateResponse.value = response.body()
            } else {
                postTvRateResponse.value = null
            }
        }
    }

    fun getTvSeriesDetailList(tvSeriesId: Int) {
        TmdbApi.getTvSeriesDetail(tvDetailCallback, tvSeriesId)
    }

    fun getTvSeriesCastList(tvSeriesId: Int) {
        TmdbApi.getTvSeriesCast(tvCastCallback, tvSeriesId)
    }

    fun postTvSeriesRate(tvSeriesId: Int, rate: Int) {
        val rateValue : Double = rate.toDouble() * MoveeConstants.INT_TWO
        TmdbApi.postTvSeriesRate(tvPostRateCallback, tvSeriesId , rateValue)
    }
}