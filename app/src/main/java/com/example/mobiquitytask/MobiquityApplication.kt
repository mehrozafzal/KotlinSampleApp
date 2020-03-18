package com.example.mobiquitytask

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.mobiquitytask.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject


class MobiquityApplication : MultiDexApplication(), HasAndroidInjector {


    companion object {
        private lateinit var instance: MobiquityApplication
        private var wasInBackground: Boolean = false
        fun getInstance(): MobiquityApplication {
            return instance
        }

    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var mCalligraphyConfig: CalligraphyConfig

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
        createDaggerComponent()
        CalligraphyConfig.initDefault(mCalligraphyConfig)
    }

    private fun createDaggerComponent() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}
