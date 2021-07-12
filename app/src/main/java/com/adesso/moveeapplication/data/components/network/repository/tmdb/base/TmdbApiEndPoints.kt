package com.adesso.moveeapplication.data.components.network.repository.tmdb.base

enum class TmdbApiEndPoints(val url: String) {
    MOVIE_POPULAR("movie/popular"),
    MOVIE_GENRE("genre/movie/list"),
    MOVIE_VISION("movie/now_playing"),
    MOVIE_CAST_STARTING("movie/"),
    MOVIE_CAST_ENDING("/credits"),
    MOVIE_DETAIL("movie/"),
    TV_POPULAR("tv/popular"),
    TV_GENRE("genre/tv/list"),
    TV_TOP_RATED("tv/top_rated"),
    TV_SERIES_DETAIL("tv/"),
    TV_SERIES_CAST_STARTING("tv/"),
    TV_SERIES_CAST_ENDING("/credits"),
    TV_SERIES_CAST_DETAIL("person/"),
    PERSON_MOVIES_STARTING("person/"),
    PERSON_MOVIES_ENDING("/movie_credits"),
    MULTIPLE_SEARCH("/3/search/multi"),
    TOKEN("authentication/token/new"),
    USER_TOKEN("authentication/token/validate_with_login"),
    USER_SESSION("authentication/session/new"),
    GUEST_SESSION("authentication/guest_session/new"),
    PROFILE_INFO_END_POINT("account"),
    USER_FAVORITES_STARTING("account/"),
    USER_FAVORITES_MOVIE_ENDING("/favorite/movies"),
    USER_FAVORITES_TV_ENDING("/favorite/tv"),
    POST_FAVORITE_ENDING("/favorite"),
    POST_RATE_MOVIE_STARTING("movie/"),
    POST_RATE_TV_SERIES_STARTING("tv/"),
    POST_RATE_ENDING("/rating"),
    GET_RATED_STARTING("account/"),
    GET_RATED_MOVIE_ENDING("/rated/movies"),
    GET_RATED_TV_SERIES_ENDING("/rated/tv")
}