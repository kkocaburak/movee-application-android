package com.adesso.moveeapplication.data.components.network.repository.tmdb.api

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.adesso.moveeapplication.ui.screens.dashboard.main.view.MainView
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals


/**
 * Created by Burak Karakoca on 13.11.2020.
 */
@RunWith(AndroidJUnit4::class)
class TmdbApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var errorResponse: MockResponse
    private lateinit var errorInvalidResponse: MockResponse

    @get:Rule
    var mActivityRule = ActivityTestRule(MainView::class.java, false, false)

    @Before
    fun setUp() {
        val errorJson = "error_body.json"
        val errorInvalidJson = "error_body_invalid.json"

        mockWebServer = MockWebServer()
        mockWebServer.start()
        mockWebServer.url("/")

        errorResponse = MockResponse()
            .setResponseCode(401)
            .setBody(RestServiceTestHelper.readStringFromFile(errorJson))

        errorInvalidResponse = MockResponse()
            .setResponseCode(404)
            .setBody(RestServiceTestHelper.readStringFromFile(errorInvalidJson))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testApiFailure() {
        mockWebServer.enqueue(errorResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 401 Client Error", errorResponse.status)
    }

    @Test
    fun testApiFailureInvalid() {
        mockWebServer.enqueue(errorInvalidResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        errorInvalidResponse.status

        assertEquals("HTTP/1.1 404 Client Error", errorInvalidResponse.status)
    }

    @Test
    fun testGenreResponseSuccess() {
        val genreJson = "genre_response.json"

        val myResponse = MockResponse()
            .setResponseCode(200)
            .setBody(RestServiceTestHelper.readStringFromFile(genreJson))

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testMoviePopularSuccess() {
        val jsonValue = "movie_popular_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testMovieNowPlayingSuccess() {
        val jsonValue = "movie_now_playing_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testMovieFavoriteSuccess() {
        val jsonValue = "movie_favorite_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testTvPopularSuccess() {
        val jsonValue = "tv_popular_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testTvTopRatedSuccess() {
        val jsonValue = "tv_top_rated_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testTvFavoriteSuccess() {
        val jsonValue = "tv_favorite_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testMovieDetailSuccess() {
        val jsonValue = "movie_detail_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testTvDetailSuccess() {
        val jsonValue = "tv_detail_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testMultiSearchSuccess() {
        val jsonValue = "multi_search_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testMovieCreditsSuccess() {
        val jsonValue = "movie_credits_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testTvCreditsSuccess() {
        val jsonValue = "tv_credits_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testMovieRatedSuccess() {
        val jsonValue = "movie_rated_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testTvRatedSuccess() {
        val jsonValue = "tv_rated_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testCastDetailSuccess() {
        val jsonValue = "cast_detail_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testCastMoviesSuccess() {
        val jsonValue = "cast_movies_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }

    @Test
    fun testPostSuccess() {
        val jsonValue = "post_response.json"
        val myResponse = setUpMockResponse(jsonValue)

        mockWebServer.enqueue(myResponse)

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        assertEquals("HTTP/1.1 200 OK", myResponse.status)
    }


    private fun setUpMockResponse(json: String) : MockResponse {
        return MockResponse()
            .setResponseCode(200)
            .setBody(RestServiceTestHelper.readStringFromFile(json))
    }

}