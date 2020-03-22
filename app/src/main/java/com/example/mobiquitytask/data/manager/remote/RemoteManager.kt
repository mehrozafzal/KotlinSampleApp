package com.example.mobiquitytask.data.manager.remote

import com.example.mobiquitytask.data.model.api.RestaurantResponse
import io.reactivex.Single

interface RemoteManager {
    fun getCategoryListResponse(): Single<List<RestaurantResponse>>
}
