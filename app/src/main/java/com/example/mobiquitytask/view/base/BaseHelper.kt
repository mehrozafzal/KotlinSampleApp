package com.example.mobiquitytask.view.base

import androidx.annotation.StringRes

interface BaseHelper {
    fun showToast(message: String?)
    fun showToast(@StringRes messageStringRes: Int)
    fun handleError(throwable: Throwable?)
}