package com.example.mobiquitytask.data.remote

import com.example.mobiquitytask.data.model.api.RestaurantResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AppApiHelper {

    @GET(ApiEndPoints.BASE_URL)
    fun getCategoryListResponse(): Single<List<RestaurantResponse>>
}

