package com.example.mobiquitytask.view.category

import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun provideLinearLayoutManager(activity: MainActivity): LinearLayoutManager {
        return LinearLayoutManager(activity)
    }
}