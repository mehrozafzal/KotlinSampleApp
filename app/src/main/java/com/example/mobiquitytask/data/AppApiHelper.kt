package com.example.mobiquitytask.data

import com.example.mobiquitytask.data.AppApiEndPoints
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AppApiHelper {

  /*  @POST(AppApiEndPoints.ENDPOINT_SIGNUP)
    fun signup(@Body signupRequest: SignupRequest): Single<SignupResponse>

    @POST(AppApiEndPoints.ENDPOINT_UPDATE_USER_PROFILE_IMAGE)
    fun getUpdateProfileImageResponse(@Body requestBody: RequestBody): Single<UpdateProfileImageResponse>

    @GET(AppApiEndPoints.ENDPOINT_USER_STATISTICS)
    fun getDetailedUserStatistics(): Single<UserStatisticsResponse>

    @GET(AppApiEndPoints.ENDPOINT_USER_CONFIG)
    fun getUserConfigOnResume(): Single<ConfigResponse>

    @POST(AppApiEndPoints.ENDPOINT_EDIT_PROFILE)
    fun getEditProfileResponse(@Body editProfileRequest: EditProfileRequest): Single<EditProfileResponse>

    @POST(AppApiEndPoints.ENDPOINT_CHANGE_PASSWORD)
    fun getChangePasswordResponse(@Body changePasswordRequest: ChangePasswordRequest): Single<ChangePasswordResponse>*/
}

