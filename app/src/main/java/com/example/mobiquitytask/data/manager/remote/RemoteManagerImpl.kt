package com.example.mobiquitytask.data.manager.remote


import com.example.mobiquitytask.data.model.api.RestaurantResponse
import com.example.mobiquitytask.data.remote.AppApiHelper
import com.google.gson.Gson
import io.reactivex.Single


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteManagerImpl @Inject constructor(
    val mGson: Gson,
    val apiHelper: AppApiHelper
) : RemoteManager {
    override fun getCategoryListResponse(): Single<List<RestaurantResponse>> {
        return apiHelper.getCategoryListResponse()
    }
}
