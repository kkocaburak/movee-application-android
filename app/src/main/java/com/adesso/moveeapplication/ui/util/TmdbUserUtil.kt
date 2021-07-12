package com.adesso.moveeapplication.ui.util

import android.view.View
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.TmdbUser
import com.adesso.moveeapplication.ui.components.generic.MovieItem

/**
 * Created by Burak Karakoca on 2.11.2020.
 */
object TmdbUserUtil {
    fun isItemFavorite(id: Int) : Boolean {
        TmdbUser.userFavorites!!.forEach {
            if (id == it.id) {
                return true
            }
        }
        return false
    }

    fun isMovieRated(id: Int) : Boolean {
        TmdbUser.userRatedMovies!!.forEach {
            if (id == it.id) {
                return true
            }
        }
        return false
    }

    fun isTvSeriesRated(id: Int) : Boolean {
        TmdbUser.userRatedTvSeries!!.forEach {
            if (id == it.id) {
                return true
            }
        }
        return false
    }

    fun getRatedMovieRateValue(id: Int) : Int {
        TmdbUser.userRatedMovies!!.forEach {
            if (id == it.id) {
                return it.rating / MoveeConstants.INT_TWO
            }
        }
        return MoveeConstants.INT_ZERO
    }

    fun setRatedMovieRateValue(id: Int, rate: Int) {
        TmdbUser.userRatedMovies!!.forEach {
            if (id == it.id) {
                it.rating = rate
            }
        }
    }

    fun getRatedTvSeriesRateValue(id: Int) : Int {
        TmdbUser.userRatedTvSeries!!.forEach {
            if (id == it.id) {
                return it.rating / MoveeConstants.INT_TWO
            }
        }
        return MoveeConstants.INT_ZERO
    }

    fun setRatedTvSeriesRateValue(id: Int, rate: Int) {
        TmdbUser.userRatedTvSeries!!.forEach {
            if (id == it.id) {
                it.rating = rate
            }
        }
    }
}