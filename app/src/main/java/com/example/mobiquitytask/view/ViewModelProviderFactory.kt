package com.example.mobiquitytask.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobiquitytask.utils.rx.SchedulerProvider
import com.example.mobiquitytask.data.manager.app.IAppDataManager
import com.example.mobiquitytask.view.category.MainViewModel
import com.example.mobiquitytask.view.category.productDetail.DetailViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ViewModelProviderFactory @Inject constructor(
    private val iAppDataManager: IAppDataManager,
    private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                return MainViewModel(
                    iAppDataManager,
                    schedulerProvider
                ) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(
                    iAppDataManager,
                    schedulerProvider
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}