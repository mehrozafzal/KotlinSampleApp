package com.example.mobiquitytask.di.builder

import com.example.mobiquitytask.view.category.MainActivity
import com.example.mobiquitytask.view.category.MainModule
import com.example.mobiquitytask.view.category.productDetail.DetailProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class, DetailProvider::class])
    abstract fun bindMainActivity(): MainActivity
}