package com.example.mobiquitytask.utils

import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.util.*

object ErrorParser {
    fun parseError(e: Throwable): String? {
        var message: String? = ""
        if (e is HttpException) {
            try {
                val body =
                    Objects.requireNonNull(e.response())
                        ?.errorBody()!!
                val jObjError = JSONObject(body.string())
                message = jObjError.getString("message")
            } catch (ex: JSONException) {
                ex.printStackTrace()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        } else message = e.message
        return message
    }
}