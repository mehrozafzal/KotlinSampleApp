package com.example.mobiquitytask.data.manager.app

import com.example.mobiquitytask.data.manager.remote.RemoteManager

interface IAppDataManager {
    fun getRemoteManager(): RemoteManager

    //Any other managers
}