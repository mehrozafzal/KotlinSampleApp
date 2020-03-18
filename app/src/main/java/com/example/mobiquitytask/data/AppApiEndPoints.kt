package com.example.mobiquitytask.data


class AppApiEndPoints {
    companion object {
        var BASE_URL = "http://prod.iapps.se:8081/"
        const val ENDPOINT_SIGNUP = "iapps-auth-service/auth/sign-up"
        const val ENDPOINT_WORKSPACE = "iapps-auth-service/v1/auth/verify/domain"
        const val ENDPOINT_LOGIN = "iapps-auth-service/v1/auth/login/domain"
        const val ENDPOINT_FORGOT_PASSWORD = "iapps-auth-service/v1/auth/forgot-password"
        const val ENDPOINT_UPDATE_USER_PROFILE_IMAGE = "iapps-user-service/user/update-profile-image/"
        const val ENDPOINT_USER_STATISTICS = "iapps-user-service/user/get-user-statistics"
        const val ENDPOINT_EDIT_PROFILE = "/iapps-user-service/user/update-profile-mobile-app"
        const val ENDPOINT_USER_CONFIG = "/iapps-user-service/user/fetch-updated-config"
        const val ENDPOINT_CHANGE_PASSWORD = "/iapps-user-service/user/change-password-user"
    }
}
