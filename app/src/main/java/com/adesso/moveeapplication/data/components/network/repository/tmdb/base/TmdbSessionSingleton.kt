package com.adesso.moveeapplication.data.components.network.repository.tmdb.base

/**
 * Created by Burak Karakoca on 23.10.2020.
 */
object TmdbSessionSingleton {
    lateinit var sessionId: String
    var isGuest: Boolean = false
}