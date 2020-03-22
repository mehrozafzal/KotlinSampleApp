package com.example.mobiquitytask.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mobiquitytask.data.manager.app.AppDataManager
import com.example.mobiquitytask.data.manager.remote.RemoteManager
import com.example.mobiquitytask.data.model.api.ProductsItem
import com.example.mobiquitytask.data.model.api.RestaurantResponse
import com.example.mobiquitytask.data.model.api.SalePrice
import com.example.mobiquitytask.data.remote.AppApiHelper
import com.example.mobiquitytask.utils.rx.AppSchedulerProvider
import com.example.mobiquitytask.view.category.MainViewModel
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class ViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var iAppDataManager: AppDataManager

    @InjectMocks
    lateinit var schedulerProvider: AppSchedulerProvider

    @Mock
    lateinit var remoteManager: RemoteManager

    @Mock
    lateinit var appApiHelper: AppApiHelper

    lateinit var mainViewModel: MainViewModel

    private lateinit var testObserver: TestObserver<List<RestaurantResponse>>

    private var testSingle: Single<List<RestaurantResponse>>? = null


    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        testObserver = TestObserver()
        mainViewModel = MainViewModel(iAppDataManager, schedulerProvider)
    }

    @Test
    fun shouldRunCategorySuccessResponse() {
        val salePrice = SalePrice("800", "EUR")
        val productItem = ProductsItem(salePrice, "", "", "", "", "")
        val productList = arrayListOf(productItem)
        val restaurantResponse = RestaurantResponse("", "", "", productList)
        val restaurantList = arrayListOf(restaurantResponse)
        testSingle = Single.just(restaurantList)

        Assert.assertNotNull(mainViewModel)
        Assert.assertNotNull(appApiHelper)

        whenever(appApiHelper.getCategoryListResponse()).thenReturn(testSingle)

        mainViewModel.fetchCategoryList()

        Assert.assertEquals(1, mainViewModel.restaurantLiveData.value?.size)

        Assert.assertEquals(false, mainViewModel.restaurantLoadError)

        Assert.assertEquals(false, mainViewModel.loading.value)

    }

    
    @Test
    fun shouldRunCategoryFailureResponse() {
        testSingle = Single.error(Throwable())

        whenever(appApiHelper.getCategoryListResponse()).thenReturn(testSingle)

        mainViewModel.fetchCategoryList()

        Assert.assertNotNull(mainViewModel)

        Assert.assertEquals(true, mainViewModel.restaurantLoadError)

        Assert.assertEquals(false, mainViewModel.loading.value)

    }

    @Before
    fun setUpRxSchedulers() {
        /* val immidiate = object: Scheduler() {
             override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                 return super.scheduleDirect(run, 0, unit)
             }

             override fun createWorker(): Worker {
                 return ExecutorScheduler.ExecutorWorker(Executor { it.run() },false)
             }
         }

         RxJavaPlugins.setInitIoSchedulerHandler{scheduler: Callable<Scheduler>? -> immidiate }
         RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Callable<Scheduler>? -> immidiate }
         RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler>? ->  immidiate}
         RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Callable<Scheduler>? -> immidiate }
         RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler>? -> immidiate }*/
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

}