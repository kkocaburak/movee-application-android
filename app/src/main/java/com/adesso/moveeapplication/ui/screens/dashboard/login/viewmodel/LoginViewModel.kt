package com.adesso.moveeapplication.ui.screens.dashboard.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.components.network.repository.tmdb.api.TmdbApi
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.GuestSessionResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TokenResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.UserSessionResponse
import com.adesso.moveeapplication.data.model.tmdb.LoginInfo
import com.adesso.moveeapplication.ui.base.BaseViewModel
import com.adesso.moveeapplication.ui.util.RetrofitErrorUtil
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Burak Karakoca on 20.10.2020.
 */
class LoginViewModel : BaseViewModel() {

    val tokenResponse : MutableLiveData<TokenResponse> = MutableLiveData()
    val userTokenResponse : MutableLiveData<TokenResponse> = MutableLiveData()
    val userSessionResponse : MutableLiveData<UserSessionResponse> = MutableLiveData()
    val guestSessionResponse : MutableLiveData<GuestSessionResponse> = MutableLiveData()

    private var tokenCallback = object : Callback<TokenResponse> {
        override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
            if (response.isSuccessful) {
                tokenResponse.value = response.body()
            } else {
                errorMessage.value = RetrofitErrorUtil.errorBodyConverter(response)
            }
        }
    }

    private var userTokenCallback = object : Callback<TokenResponse> {
        override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
            if (response.isSuccessful) {
                userTokenResponse.value = response.body()
            } else {
                errorMessage.value = RetrofitErrorUtil.errorBodyConverter(response)
            }
        }
    }

    private var userSessionCallback = object : Callback<UserSessionResponse> {
        override fun onFailure(call: Call<UserSessionResponse>, t: Throwable) {
            responseNotSuccessful()
        }

        override fun onResponse(call: Call<UserSessionResponse>, response: Response<UserSessionResponse>) {
            if (response.isSuccessful) {
                userSessionResponse.value = response.body()
            } else {
                errorMessage.value = RetrofitErrorUtil.errorBodyConverter(response)
            }
        }

    }

    private var guestSessionCallback = object : Callback<GuestSessionResponse> {
        override fun onFailure(call: Call<GuestSessionResponse>, t: Throwable) {
            responseNotSuccessful()
        }
        override fun onResponse(call: Call<GuestSessionResponse>, response: Response<GuestSessionResponse>) {
            if (response.isSuccessful) {
                guestSessionResponse.value = response.body()
            } else {
                errorMessage.value = RetrofitErrorUtil.errorBodyConverter(response)
            }
        }
    }

    fun getToken() {
        TmdbApi.getTokenResult(tokenCallback)
    }

    fun getUserToken(user: LoginInfo) {
        TmdbApi.getUserTokenResult(userTokenCallback, user)
    }

    fun getUserSession(token: String) {
        TmdbApi.getUserSessionResult(userSessionCallback, token)
    }

    fun getGuestSession() {
        TmdbApi.getGuestSessionResult(guestSessionCallback)
    }

}