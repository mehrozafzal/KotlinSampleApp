package com.example.mobiquitytask.di.module

import android.app.Application
import android.content.Context
import com.example.mobiquitytask.R
import com.example.mobiquitytask.data.manager.app.AppDataManager
import com.example.mobiquitytask.data.manager.app.IAppDataManager
import com.example.mobiquitytask.data.manager.remote.RemoteManager
import com.example.mobiquitytask.data.manager.remote.RemoteManagerImpl
import com.example.mobiquitytask.data.remote.ApiEndPoints
import com.example.mobiquitytask.utils.rx.AppSchedulerProvider
import com.example.mobiquitytask.utils.rx.SchedulerProvider
import com.google.gson.Gson
import com.synavos.iapps.di.ApiInfo
import dagger.Module
import dagger.Provides
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import javax.inject.Singleton


@Module
open class AppModule {


    @Provides
    @ApiInfo
    open fun provideApiKey(): String {
        return ApiEndPoints.BASE_URL
    }


    @Provides
    @Singleton
    open fun provideCalligraphyDefaultConfig(): ViewPump {
        return ViewPump.builder()
            .addInterceptor(
                CalligraphyInterceptor(
                    CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Gerbera.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    open fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    open fun provideApiManager(remotemanager: RemoteManagerImpl): RemoteManager {
        return remotemanager
    }

    @Provides
    @Singleton
    open fun provideAppManager(appDataManager: AppDataManager): IAppDataManager {
        return appDataManager
    }


    @Provides
    @Singleton
    open fun provideGsonConverter(): Gson {
        return Gson()
    }


    @Provides
    open fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

}
