package com.adesso.moveeapplication.ui.screens.dashboard.user.viewmodel

import androidx.lifecycle.MutableLiveData
import com.adesso.moveeapplication.data.components.network.repository.tmdb.api.TmdbApi
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.ProfileResponse
import com.adesso.moveeapplication.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Burak Karakoca on 26.10.2020.
 */
class UserProfileViewModel : BaseViewModel()