package com.example.mobiquitytask.view.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.mobiquitytask.data.manager.app.IAppDataManager
import com.example.mobiquitytask.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(
    protected val iAppDataManager: IAppDataManager,
    private val mSchedulerProvider: SchedulerProvider?
) :
    ViewModel() {
    public val isLoading = ObservableBoolean()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var mNavigator: WeakReference<N>? = null

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    val helper: N? get() = mNavigator?.get()

    fun setHelper(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

    val schedulerProvider: SchedulerProvider?
        get() = mSchedulerProvider

}