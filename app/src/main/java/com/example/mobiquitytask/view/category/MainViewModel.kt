package com.example.mobiquitytask.view.category

import androidx.lifecycle.MutableLiveData
import com.example.mobiquitytask.data.manager.app.IAppDataManager
import com.example.mobiquitytask.data.model.api.RestaurantResponse
import com.example.mobiquitytask.utils.ErrorParser
import com.example.mobiquitytask.utils.rx.SchedulerProvider
import com.example.mobiquitytask.view.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class MainViewModel(
    iAppDataManager: IAppDataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<MainHelper>(iAppDataManager, schedulerProvider) {

    val restaurantLiveData = MutableLiveData<List<RestaurantResponse>>()
    val restaurantLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun fetchCategoryList() {
        loading.value = true
        setIsLoading(true)
        compositeDisposable.add(
            iAppDataManager.getRemoteManager().getCategoryListResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<RestaurantResponse>>() {
                    override fun onSuccess(restaurantListResponse: List<RestaurantResponse>) {
                        setIsLoading(false)
                        restaurantLiveData.value = restaurantListResponse
                        restaurantLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        restaurantLoadError.value = true
                        loading.value = false
                        setIsLoading(false)
                        helper?.showToast(ErrorParser.parseError(e))
                    }
                })
        )
    }


}