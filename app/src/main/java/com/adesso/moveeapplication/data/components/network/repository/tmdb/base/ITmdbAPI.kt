package com.adesso.moveeapplication.data.components.network.repository.tmdb.base

import com.adesso.moveeapplication.BuildConfig
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.*
import com.adesso.moveeapplication.data.model.tmdb.FavoriteBody
import com.adesso.moveeapplication.data.model.tmdb.LoginInfo
import com.adesso.moveeapplication.data.model.tmdb.RateRequestBody
import com.adesso.moveeapplication.data.model.tmdb.RequestToken
import retrofit2.Call
import retrofit2.http.*

interface ITmdbAPI {

    //region MOVIE
    @GET
    fun getMoviePopularData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String,
        @Query(TmdbAPIConstants.PAGE_TEXT) page: Int
    ): Call<MovieResponse>

    @GET
    fun getMovieGenreData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language)
    ): Call<GenreResponse>

    @GET
    fun getMovieVisionData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language),
        @Query(TmdbAPIConstants.PAGE_TEXT) page: Int = TmdbAPIConstants.PAGE_NUMBER
    ): Call<MovieResponse>

    @GET
    fun getMovieCastData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language),
        @Query(TmdbAPIConstants.PAGE_TEXT) page: Int = TmdbAPIConstants.PAGE_NUMBER
    ): Call<MovieCastResponse>

    @GET
    fun getMovieDetailData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language)
    ): Call<MovieDetailResponse>

    @POST
    fun postRateData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.SESSION_TEXT) session: String = TmdbSessionSingleton.sessionId,
        @Body body: RateRequestBody
    ): Call<PostResponse>
    //endregion

    //region TV SERIES
    @GET
    fun getTvGenreData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language)
    ): Call<GenreResponse>

    @GET
    fun getTvPopularData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language),
        @Query(TmdbAPIConstants.PAGE_TEXT) page: Int = TmdbAPIConstants.PAGE_NUMBER
    ): Call<TvSeriesResponse>

    @GET
    fun getTvTopRatedData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String,
        @Query(TmdbAPIConstants.PAGE_TEXT) page: Int
    ): Call<TvSeriesResponse>

    @GET
    fun getTvSeriesDetailData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language),
        @Query(TmdbAPIConstants.PAGE_TEXT) page: Int = TmdbAPIConstants.PAGE_NUMBER
    ): Call<TvSeriesDetailResponse>

    @GET
    fun getTvSeriesCastData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language)
    ): Call<TvSeriesCastResponse>

    @GET
    fun getTvSeriesCastDetailData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language)
    ): Call<CastDetailResponse>

    //endregion

    //region CAST
    @GET
    fun getCastMovieData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language)
    ): Call<CastMoviesResponse>

    //endregion

    //region SEARCH
    @GET
    fun getSearchData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String,
        @Query(TmdbAPIConstants.QUERY_TEXT) query: String,
        @Query(TmdbAPIConstants.ADULT_TEXT) adult: String
    ): Call<SearchTMDBResponse>

    //endregion

    //region USER
    @GET
    fun getUserInfo(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String,
        @Query(TmdbAPIConstants.SESSION_TEXT) session: String
    ): Call<ProfileResponse>

    @GET
    fun getFavoriteMovieInfo(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.SESSION_TEXT) session: String = TmdbSessionSingleton.sessionId,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language),
        @Query(TmdbAPIConstants.PAGE_TEXT) page: Int = TmdbAPIConstants.PAGE_NUMBER
    ): Call<MovieResponse>

    @GET
    fun getFavoriteTvInfo(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.SESSION_TEXT) session: String = TmdbSessionSingleton.sessionId,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language),
        @Query(TmdbAPIConstants.PAGE_TEXT) page: Int = TmdbAPIConstants.PAGE_NUMBER
    ): Call<TvSeriesResponse>

    @POST
    fun postFavorite(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String,
        @Query(TmdbAPIConstants.SESSION_TEXT) session: String,
        @Body body: FavoriteBody
    ): Call<PostResponse>

    @GET
    fun getRatedMoviesData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language),
        @Query(TmdbAPIConstants.SESSION_TEXT) session: String = TmdbSessionSingleton.sessionId
    ): Call<MovieResponse>

    @GET
    fun getRatedTvSeriesData(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY,
        @Query(TmdbAPIConstants.LANGUAGE_TEXT) lang: String = MoveeApplication.appContext.getString(
            R.string.app_language),
        @Query(TmdbAPIConstants.SESSION_TEXT) session: String = TmdbSessionSingleton.sessionId
    ): Call<TvSeriesResponse>
    //endregion

    //region LOGIN
    @GET
    fun getToken(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY
    ): Call<TokenResponse>

    @GET
    fun getGuestSession(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String = BuildConfig.API_KEY
    ): Call<GuestSessionResponse>

    @POST
    fun getUserToken(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String,
        @Body user: LoginInfo
    ): Call<TokenResponse>

    @POST
    fun getUserSession(
        @Url url: String?,
        @Query(TmdbAPIConstants.API_TEXT) apiKey: String,
        @Body token: RequestToken
    ): Call<UserSessionResponse>

    //endregion

}