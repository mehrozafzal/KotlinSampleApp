package com.example.mobiquitytask.di.component

import android.app.Application
import com.example.mobiquitytask.MobiquityApplication
import com.example.mobiquitytask.di.module.AppModule
import com.example.mobiquitytask.di.module.RetrofitModule
import com.example.mobiquitytask.di.builder.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class, RetrofitModule::class])
interface AppComponent {
    fun inject(app: MobiquityApplication)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}
