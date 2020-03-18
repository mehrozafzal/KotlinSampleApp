package com.example.mobiquitytask.di.module

import android.app.Application
import android.content.Context
import com.example.mobiquitytask.R

import com.google.gson.Gson
import com.synavos.iapps.di.ApiInfo
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


@Module
class AppModule {


    @Provides
    @ApiInfo
    fun provideApiKey(): String {
//        return BuildConfig.API_KEY
        return "BuildConfig.API_KEY"
    }
/*  @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build()
    }*/

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return  CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }


/*    @Provides
    @Singleton
    fun provideApiManager(remotemanager: RemoteManagerImpl): RemoteManager {
        return remotemanager
    }*/


    @Provides
    @Singleton
    fun provideGsonConverter(): Gson {
        return Gson()
    }


  /*  @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }*/

}
