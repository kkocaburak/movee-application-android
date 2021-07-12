package com.adesso.moveeapplication.data.model.tmdb

import java.io.Serializable

/**
 * Created by Burak Karakoca on 19.10.2020.
 */
interface IMovie : Serializable {
    val adult: Boolean
    val backdropPath: String
    val genreIds: List<Int>?
    val id: Int
    val originalLanguage: String
    val originalTitle: String
    val overview: String?
    val popularity: Double?
    val posterPath: String?
    val releaseDate: String
    val title: String
    val video: Boolean
    val voteAverage: Double
    val voteCount: Int
}