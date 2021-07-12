package com.adesso.moveeapplication.ui.screens.dashboard.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adesso.moveeapplication.data.components.network.repository.tmdb.api.TmdbApi
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.SearchTMDBResponse
import com.adesso.moveeapplication.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Burak Karakoca on 20.10.2020.
 */
class SearchViewModel : BaseViewModel() {

    val searchResponse : MutableLiveData<SearchTMDBResponse> = MutableLiveData()
    val searchStatus : MutableLiveData<Boolean> = MutableLiveData()

    private var multipleTMDBCallback = object : Callback<SearchTMDBResponse> {
        override fun onFailure(call: Call<SearchTMDBResponse>, t: Throwable) {
            responseNotSuccessful()
            searchStatus.value = false
        }
        override fun onResponse(call: Call<SearchTMDBResponse>, response: Response<SearchTMDBResponse>) {
            if (response.isSuccessful) {
                searchStatus.value = true
                searchResponse.value = response.body()
            } else {
                responseNotSuccessful()
                searchStatus.value = false
            }
        }
    }

    fun getSearchResultList(myQuery: String) {
        TmdbApi.getSearchResult(multipleTMDBCallback, myQuery)
    }

}