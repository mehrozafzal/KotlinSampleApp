package com.example.mobiquitytask.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mobiquitytask.data.manager.app.AppDataManager
import com.example.mobiquitytask.data.manager.remote.RemoteManagerImpl
import com.example.mobiquitytask.data.model.api.RestaurantResponse
import com.example.mobiquitytask.data.remote.AppApiHelper
import com.example.mobiquitytask.utils.rx.SchedulerProvider
import com.example.mobiquitytask.view.category.MainViewModel
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var gson: Gson

    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    @Mock
    lateinit var restaurantObserver: Observer<List<RestaurantResponse>>

    @Mock
    lateinit var restaurantErrorObserver: Observer<Boolean>

    @Mock
    lateinit var loadingObserver: Observer<Boolean>

    @Mock
    lateinit var appApiHelper: AppApiHelper

    private lateinit var mainViewModel: MainViewModel
    private lateinit var appDataManager: AppDataManager
    private lateinit var remoteManagerImpl: RemoteManagerImpl


    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler{Schedulers.trampoline()}
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        MockitoAnnotations.initMocks(this)
        remoteManagerImpl = RemoteManagerImpl(gson, appApiHelper)
        appDataManager = AppDataManager(remoteManagerImpl)
        mainViewModel = MainViewModel(appDataManager, schedulerProvider)
        mainViewModel.restaurantLiveData.observeForever(restaurantObserver)
        mainViewModel.restaurantLoadError.observeForever(restaurantErrorObserver)
        mainViewModel.loading.observeForever(loadingObserver)
    }

    @Test
    fun testNull() {
        Mockito.`when`(appApiHelper.getCategoryListResponse()).thenReturn(null)
        Assert.assertNotNull(mainViewModel.restaurantLiveData)
        Assert.assertTrue(mainViewModel.restaurantLiveData.hasObservers())
    }

    @Test
    fun testApiFetchDataSuccess() {
        Mockito.`when`(appApiHelper.getCategoryListResponse())
            .thenReturn(Single.just(ArrayList()))
        mainViewModel.fetchCategoryList()
        Mockito.verify(loadingObserver).onChanged(true)
        Mockito.verify(restaurantObserver).onChanged(ArrayList())
        Mockito.verify(loadingObserver).onChanged(false)
        Mockito.verify(restaurantErrorObserver).onChanged(false)
    }

    @Test
    fun testApiFetchDataFailure() {
        Mockito.`when`(appApiHelper.getCategoryListResponse())
            .thenReturn(Single.error(Throwable("Api error")))
        mainViewModel.fetchCategoryList()
        Mockito.verify(loadingObserver).onChanged(true)
        Mockito.verify(restaurantErrorObserver).onChanged(true)
        Mockito.verify(loadingObserver).onChanged(false)
    }
}