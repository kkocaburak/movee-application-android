package com.adesso.moveeapplication.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adesso.moveeapplication.ui.screens.dashboard.main.viewmodel.MainViewModel
import com.adesso.moveeapplication.ui.screens.detail.moviedetail.viewmodel.MovieDetailViewModel
import com.adesso.moveeapplication.ui.screens.detail.castdetail.viewmodel.CastViewModel
import com.adesso.moveeapplication.ui.screens.dashboard.movie.viewmodel.MovieViewModel
import com.adesso.moveeapplication.ui.screens.dashboard.search.viewmodel.SearchViewModel
import com.adesso.moveeapplication.ui.screens.detail.tvdetail.viewmodel.TvSeriesDetailViewModel
import com.adesso.moveeapplication.ui.screens.dashboard.tvseries.viewmodel.TvViewModel
import com.adesso.moveeapplication.ui.screens.dashboard.user.viewmodel.UserProfileViewModel
import com.adesso.moveeapplication.ui.screens.dashboard.login.viewmodel.LoginViewModel
import com.adesso.moveeapplication.ui.screens.map.viewmodel.MapViewModel

/**
 * Created by Burak Karakoca on 5.10.2020.
 */
class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel().javaClass -> {
                MainViewModel() as T
            }
            MovieViewModel().javaClass -> {
                MovieViewModel() as T
            }
            TvViewModel().javaClass -> {
                TvViewModel() as T
            }
            SearchViewModel().javaClass -> {
                SearchViewModel() as T
            }
            MovieDetailViewModel().javaClass -> {
                MovieDetailViewModel() as T
            }
            TvSeriesDetailViewModel().javaClass -> {
                TvSeriesDetailViewModel() as T
            }
            CastViewModel().javaClass -> {
                CastViewModel() as T
            }
            LoginViewModel().javaClass -> {
                LoginViewModel() as T
            }
            UserProfileViewModel().javaClass -> {
                UserProfileViewModel() as T
            }
            MapViewModel().javaClass -> {
                MapViewModel() as T
            }
            else -> {
                BaseViewModel() as T
            }
        }
    }

}