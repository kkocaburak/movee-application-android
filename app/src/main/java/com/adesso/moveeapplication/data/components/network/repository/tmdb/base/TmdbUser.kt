package com.adesso.moveeapplication.data.components.network.repository.tmdb.base

import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.MovieResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.ProfileResponse
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TvSeriesResponse
import com.adesso.moveeapplication.data.model.tmdb.Movie
import com.adesso.moveeapplication.data.model.tmdb.TvSeries
import com.adesso.moveeapplication.ui.components.generic.GenericItem

/**
 * Created by Burak Karakoca on 2.11.2020.
 */
object TmdbUser {
    var user: ProfileResponse? = null
    var userFavorites: ArrayList<GenericItem>? = arrayListOf()
    var userRatedMovies: ArrayList<Movie>? = arrayListOf()
    var userRatedTvSeries: ArrayList<TvSeries>? = arrayListOf()
}