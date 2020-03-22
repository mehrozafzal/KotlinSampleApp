package com.example.mobiquitytask.view.category.productDetail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailProvider {
    @ContributesAndroidInjector
    abstract fun provideDetailFragFactory(): DetailFragment?
}