package com.example.mobiquitytask.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mobiquitytask.RxSchedulersOverrideRule
import com.example.mobiquitytask.data.manager.remote.RemoteManager
import com.example.mobiquitytask.data.manager.remote.RemoteManagerImpl
import com.example.mobiquitytask.data.model.api.RestaurantResponse
import com.example.mobiquitytask.data.remote.ApiEndPoints
import com.example.mobiquitytask.data.remote.AppApiHelper
import com.example.mobiquitytask.view.category.MainViewModel
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.reactivestreams.Subscriber
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class MainRepoTest {
    @Rule
    @JvmField
    val server = MockWebServer()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var remoteManager: RemoteManager
    @Mock
    lateinit var appApiHelper:AppApiHelper

    private lateinit var testObserver: TestObserver<List<RestaurantResponse>>


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler{Schedulers.trampoline()}
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mobcategories.s3-website-eu-west-1.amazonaws.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(AppApiHelper::class.java)
        testObserver = TestObserver()

        //mainViewModel.fetchCategoryList()
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testCategoryResponse() {
        val responseBody = """
                            [{
                                "name": "Food",
                                "description": "",
                                "products": [{
                                        "id": "1",
                                        "categoryId": "36802",
                                        "name": "Bread",
                                        "url": "/Bread.jpg",
                                        "description": "",
                                        "salePrice": {
                                            "amount": "0.81",
                                            "currency": "EUR"
                                        }
                                    },{
                                        "id": "2",
                                        "categoryId": "36802",
                                        "name": "Sandwich",
                                        "url": "/Sandwich.jpg",
                                        "description": "",
                                        "salePrice": {
                                            "amount": "2.01",
                                            "currency": "EUR"
                                        }
                                    },{
                                        "id": "3",
                                        "categoryId": "36802",
                                        "name": "Milk",
                                        "url": "/Milk.jpg",
                                        "description": "",
                                        "salePrice": {
                                            "amount": "2.00",
                                            "currency": "EUR"
                                        }
                                    },{
                                        "id": "4",
                                        "categoryId": "36802",
                                        "name": "Cake",
                                        "url": "/Cake.jpg",
                                        "description": "",
                                        "salePrice": {
                                            "amount": "0.01",
                                            "currency": "EUR"
                                        }
                                    }]
                            """.trimIndent()

        server.enqueue(
            MockResponse()
                .setHeader("Content-Type", "application/json; charset=UTF-8")
                .setBody(responseBody)
        )

        var response: RestaurantResponse? = null
        var error: Throwable? = null

        Assert.assertNotNull(appApiHelper)

        //testObserver.awaitTerminalEvent()
        appApiHelper.getCategoryListResponse().subscribeWith(testObserver)

        testObserver.assertNoErrors()

    }

    @Before
    fun setUpRxSchedulers(){
        /*val immidiate = object: Scheduler() {
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

}