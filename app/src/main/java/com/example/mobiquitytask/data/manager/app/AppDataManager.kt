package com.example.mobiquitytask.data.manager.app;

import com.example.mobiquitytask.data.manager.app.IAppDataManager
import com.example.mobiquitytask.data.manager.remote.RemoteManager

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class AppDataManager @Inject constructor(remoteManager: RemoteManager) : IAppDataManager {

    private var _remoteManager: RemoteManager = remoteManager

    override fun getRemoteManager(): RemoteManager {
        return _remoteManager
    }

}
