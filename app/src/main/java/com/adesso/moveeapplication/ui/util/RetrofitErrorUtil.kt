package com.adesso.moveeapplication.ui.util

import com.adesso.moveeapplication.data.base.ModelConstants
import com.adesso.moveeapplication.data.components.network.repository.tmdb.model.TokenResponse
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by Burak Karakoca on 30.10.2020.
 */
object RetrofitErrorUtil {
    fun <T> errorBodyConverter(errorBody: Response<T>) : String {
        val jsonObj = JSONObject(errorBody.errorBody()!!.charStream().readText())
        return jsonObj.getString(ModelConstants.STATUS_MESSAGE_STRING)
    }
}