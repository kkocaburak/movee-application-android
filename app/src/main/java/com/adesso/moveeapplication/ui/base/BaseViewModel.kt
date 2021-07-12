package com.adesso.moveeapplication.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.data.components.network.repository.tmdb.api.TmdbApi
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbUser
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.MovieResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.PostResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.ProfileResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesResponse
import com.adesso.moveeapplication.ui.components.generic.GenericItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseViewModel: ViewModel() {
    val errorMessage : MutableLiveData<String> = MutableLiveData()
    var isLoading : MutableLiveData<Boolean> = MutableLiveData()

    val postFavoriteResponse : MutableLiveData<PostResponse> = MutableLiveData()
    val userInfoResponse : MutableLiveData<ProfileResponse> = MutableLiveData()

    var favoriteList : MutableLiveData<ArrayList<GenericItem>> = MutableLiveData()

    private var userInfoCallback = object : Callback<ProfileResponse> {
        override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
            if (response.isSuccessful) {
                userInfoResponse.value = response.body()
            } else {
                userInfoResponse.value = null
            }
        }
    }

    private var favoritePostCallback = object : Callback<PostResponse> {
        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
            errorMessage.value = t.localizedMessage
        }
        override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
            if (response.isSuccessful) {
                postFavoriteResponse.value = response.body()
            } else {
                postFavoriteResponse.value = null
            }
        }
    }

    fun getUserInfo() {
        TmdbApi.getUserInfoResult(userInfoCallback)
    }

    fun postFavorite(itemType: String, itemId: Int, favoriteValue: Boolean) {
        TmdbApi.postFavorite(
            favoritePostCallback,
            itemType,
            itemId,
            favoriteValue
        )
    }

    fun responseNotSuccessful() {
        errorMessage.value = MoveeApplication.appContext.getString(R.string.respond_false)
        isLoading.value = false
    }

}