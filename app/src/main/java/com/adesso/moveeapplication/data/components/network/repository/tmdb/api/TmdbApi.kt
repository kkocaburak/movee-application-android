package com.adesso.moveeapplication.data.components.network.repository.tmdb.api

import com.adesso.moveeapplication.BuildConfig
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.data.components.network.repository.tmdb.base.*
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.*
import com.adesso.moveeapplication.data.model.tmdb.FavoriteBody
import com.adesso.moveeapplication.data.model.tmdb.LoginInfo
import com.adesso.moveeapplication.data.model.tmdb.RateRequestBody
import com.adesso.moveeapplication.data.model.tmdb.RequestToken
import com.adesso.moveeapplication.data.util.NetworkUtil
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TmdbApi {
    private val TMDB_API: ITmdbAPI

    init {
        val client = OkHttpClient()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        TMDB_API = retrofit.create(ITmdbAPI::class.java)
    }

    //region MOVIE
    fun getMoviePopular(myCallback: Callback<MovieResponse>, page: Int) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val callMovieInfo = TMDB_API.getMoviePopularData(
            TmdbApiEndPoints.MOVIE_POPULAR.url,
            BuildConfig.API_KEY,
            MoveeApplication.appContext.getString(
                R.string.app_language),
            page
        )

        callMovieInfo.enqueue(myCallback)
    }

    fun getMovieGenre(myCallback: Callback<GenreResponse>) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val callMovieInfo = TMDB_API.getMovieGenreData(TmdbApiEndPoints.MOVIE_GENRE.url)
        callMovieInfo.enqueue(myCallback)
    }

    fun getMovieNowPlaying(myCallback: Callback<MovieResponse>) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val callMovieInfo = TMDB_API.getMovieVisionData(TmdbApiEndPoints.MOVIE_VISION.url)
        callMovieInfo.enqueue(myCallback)
    }

    fun getMovieCast(myCallback: Callback<MovieCastResponse>, movieId: Int) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val movieCastUrl = TmdbApiEndPoints.MOVIE_CAST_STARTING.url +
                movieId +
                TmdbApiEndPoints.MOVIE_CAST_ENDING.url

        val callMovieCastInfo
                = TMDB_API.getMovieCastData(movieCastUrl)
        callMovieCastInfo.enqueue(myCallback)
    }

    fun getMovieDetail(myCallback: Callback<MovieDetailResponse>, movieId: Int) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val movieDetailUrl = TmdbApiEndPoints.MOVIE_DETAIL.url +
                movieId

        val callMovieCastInfo
                = TMDB_API.getMovieDetailData(movieDetailUrl)
        callMovieCastInfo.enqueue(myCallback)
    }

    fun postMovieRate(myCallback: Callback<PostResponse>, movieId: Int, rateValue: Double) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val moviePostRateUrl = TmdbApiEndPoints.POST_RATE_MOVIE_STARTING.url +
                movieId +
                TmdbApiEndPoints.POST_RATE_ENDING.url

        val rateBody = RateRequestBody(rateValue)

        val callMovieCastInfo
                = TMDB_API.postRateData(moviePostRateUrl, body = rateBody)
        callMovieCastInfo.enqueue(myCallback)
    }

    fun postTvSeriesRate(myCallback: Callback<PostResponse>, tvSeriesId: Int, rateValue: Double) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val tvSeriesPostRateUrl = TmdbApiEndPoints.POST_RATE_TV_SERIES_STARTING.url +
                tvSeriesId +
                TmdbApiEndPoints.POST_RATE_ENDING.url

        val rateBody = RateRequestBody(rateValue)

        val callMovieCastInfo
                = TMDB_API.postRateData(tvSeriesPostRateUrl, body = rateBody)
        callMovieCastInfo.enqueue(myCallback)
    }

    //endregion

    //region TV SERIES
    fun getTvGenre(myCallback: Callback<GenreResponse>) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val callMovieInfo = TMDB_API.getTvGenreData(TmdbApiEndPoints.TV_GENRE.url)
        callMovieInfo.enqueue(myCallback)
    }



    fun getTvSeriesPopular(myCallback: Callback<TvSeriesResponse>) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val callTvSeriesInfo = TMDB_API.getTvPopularData(TmdbApiEndPoints.TV_POPULAR.url)
        callTvSeriesInfo.enqueue(myCallback)
    }

    fun getTvSeriesTopRated(myCallback: Callback<TvSeriesResponse>, page: Int) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val callTvSeriesInfo = TMDB_API.getTvTopRatedData(
            TmdbApiEndPoints.TV_TOP_RATED.url,
            BuildConfig.API_KEY,
            MoveeApplication.appContext.getString(
                R.string.app_language),
            page
        )
        callTvSeriesInfo.enqueue(myCallback)
    }

    fun getTvSeriesDetail(myCallback: Callback<TvSeriesDetailResponse>, tvSeriesId: Int) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val tvSeriesDetailUrl = TmdbApiEndPoints.TV_SERIES_DETAIL.url +
                tvSeriesId

        val callTvSeriesInfo
                = TMDB_API.getTvSeriesDetailData(tvSeriesDetailUrl)
        callTvSeriesInfo.enqueue(myCallback)
    }

    fun getTvSeriesCast(myCallback: Callback<TvSeriesCastResponse>, tvSeriesId: Int) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val tvSeriesCastUrl = TmdbApiEndPoints.TV_SERIES_CAST_STARTING.url +
                tvSeriesId +
                TmdbApiEndPoints.TV_SERIES_CAST_ENDING.url

        val callTvSeriesInfo
                = TMDB_API.getTvSeriesCastData(tvSeriesCastUrl)
        callTvSeriesInfo.enqueue(myCallback)
    }

    fun getTvSeriesCastDetail(myCallback: Callback<CastDetailResponse>, tvCastId: Int) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val tvSeriesCastDetailUrl =
            TmdbApiEndPoints.TV_SERIES_CAST_DETAIL.url +
            tvCastId

        val callTvSeriesInfo
                = TMDB_API.getTvSeriesCastDetailData(tvSeriesCastDetailUrl)
        callTvSeriesInfo.enqueue(myCallback)
    }

    //endregion

    //region CAST
    fun castMovieDetail(myCallback: Callback<CastMoviesResponse>, tvCastId: Int) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val castMoviesUrl =
            TmdbApiEndPoints.PERSON_MOVIES_STARTING.url +
            tvCastId +
            TmdbApiEndPoints.PERSON_MOVIES_ENDING.url

        val callTvSeriesInfo = TMDB_API.getCastMovieData(castMoviesUrl)
        callTvSeriesInfo.enqueue(myCallback)
    }
    //endregion

    //region SEARCH
    fun getSearchResult(myCallback: Callback<SearchTMDBResponse>, myQuery: String) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val searchEndPoint = TmdbApiEndPoints.MULTIPLE_SEARCH.url

        val callSearchInfo = TMDB_API.getSearchData(
            searchEndPoint,
            BuildConfig.API_KEY,
            MoveeApplication.appContext.getString(
                R.string.app_language),
            myQuery,
            TmdbAPIConstants.FALSE_TEXT
        )
        callSearchInfo.enqueue(myCallback)
    }
    //endregion

    //region USER
    fun getUserInfoResult(myCallback: Callback<ProfileResponse>) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val userInfoEndPoint = TmdbApiEndPoints.PROFILE_INFO_END_POINT.url

        val callUserInfo = TMDB_API.getUserInfo(
            userInfoEndPoint,
            BuildConfig.API_KEY,
            TmdbSessionSingleton.sessionId
        )

        callUserInfo.enqueue(myCallback)
    }

    fun getFavoriteMovies(myCallback: Callback<MovieResponse>, userId: Int?) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val favoriteMovieEndPoint =
            TmdbApiEndPoints.USER_FAVORITES_STARTING.url +
                    userId +
                    TmdbApiEndPoints.USER_FAVORITES_MOVIE_ENDING.url

        val callFavoriteMovie = TMDB_API.getFavoriteMovieInfo(
            favoriteMovieEndPoint
        )

        callFavoriteMovie.enqueue(myCallback)
    }

    fun getFavoriteTvSeries(myCallback: Callback<TvSeriesResponse>, userId: Int?) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val favoriteTvEndPoint =
            TmdbApiEndPoints.USER_FAVORITES_STARTING.url +
                    userId +
                    TmdbApiEndPoints.USER_FAVORITES_TV_ENDING.url

        val callFavoriteMovie = TMDB_API.getFavoriteTvInfo(
            favoriteTvEndPoint
        )

        callFavoriteMovie.enqueue(myCallback)

    }

    fun getRatedMovies(myCallback: Callback<MovieResponse>, userId: Int?) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val ratedMoviesUrl = TmdbApiEndPoints.GET_RATED_STARTING.url +
                userId +
                TmdbApiEndPoints.GET_RATED_MOVIE_ENDING.url

        val callFavoriteMovie = TMDB_API.getRatedMoviesData(
            ratedMoviesUrl
        )

        callFavoriteMovie.enqueue(myCallback)
    }

    fun getRatedTvSeries(myCallback: Callback<TvSeriesResponse>, userId: Int?) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val ratedTvSeriesUrl = TmdbApiEndPoints.GET_RATED_STARTING.url +
                userId +
                TmdbApiEndPoints.GET_RATED_TV_SERIES_ENDING.url

        val callFavoriteMovie = TMDB_API.getRatedTvSeriesData(
            ratedTvSeriesUrl
        )

        callFavoriteMovie.enqueue(myCallback)
    }

    fun postFavorite(
        myCallback: Callback<PostResponse>,
        itemType: String,
        itemId: Int,
        favorite: Boolean
    ) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val postFavoriteEndPoint =
            TmdbApiEndPoints.USER_FAVORITES_STARTING.url +
                    TmdbUser.user!!.id +
                    TmdbApiEndPoints.POST_FAVORITE_ENDING.url

        val requestBody = FavoriteBody(itemType, itemId, favorite)
        val callUserToken =
            TMDB_API.postFavorite(
                postFavoriteEndPoint,
                BuildConfig.API_KEY,
                TmdbSessionSingleton.sessionId,
                requestBody
            )
        callUserToken.enqueue(myCallback)
    }
    //endregion

    //region LOGIN
    fun getTokenResult(myCallback: Callback<TokenResponse>) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val userTokenEndPoint = TmdbApiEndPoints.TOKEN.url

        val callUserToken = TMDB_API.getToken(userTokenEndPoint)
        callUserToken.enqueue(myCallback)
    }

    fun getUserTokenResult(myCallback: Callback<TokenResponse>, user: LoginInfo) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val userTokenEndPoint = TmdbApiEndPoints.USER_TOKEN.url

        val callUserToken = TMDB_API.getUserToken(
            userTokenEndPoint,
            BuildConfig.API_KEY,
            user
        )
        callUserToken.enqueue(myCallback)
    }

    fun getUserSessionResult(myCallback: Callback<UserSessionResponse>, token: String) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val userSessionEndPoint = TmdbApiEndPoints.USER_SESSION.url

        val callUserToken = TMDB_API
            .getUserSession(
                userSessionEndPoint,
                BuildConfig.API_KEY,
                RequestToken(token)
            )
        callUserToken.enqueue(myCallback)
    }

    fun getGuestSessionResult(myCallback: Callback<GuestSessionResponse>) {
        if (!NetworkUtil.isNetworkAvailable(MoveeApplication.appContext)) {
            return
        }

        val userTokenEndPoint = TmdbApiEndPoints.GUEST_SESSION.url

        val callGuestSession = TMDB_API.getGuestSession(userTokenEndPoint)
        callGuestSession.enqueue(myCallback)
    }
    //endregion

}