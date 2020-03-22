package com.example.mobiquitytask.di

import android.app.Application
import com.example.mobiquitytask.MobiquityApplication
import com.example.mobiquitytask.data.remote.ApiEndPoints
import com.synavos.iapps.di.ApiInfo
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import io.github.inflationx.viewpump.ViewPump
import javax.inject.Singleton
import android.content.Context
import com.example.mobiquitytask.R
import com.example.mobiquitytask.TestApplication
import com.example.mobiquitytask.data.manager.app.AppDataManager
import com.example.mobiquitytask.data.manager.app.IAppDataManager
import com.example.mobiquitytask.data.manager.remote.RemoteManager
import com.example.mobiquitytask.data.manager.remote.RemoteManagerImpl
import com.example.mobiquitytask.data.remote.AppApiHelper
import com.example.mobiquitytask.di.component.AppComponent
import com.example.mobiquitytask.main.ViewModelTest
import com.example.mobiquitytask.utils.rx.AppSchedulerProvider
import com.example.mobiquitytask.utils.rx.SchedulerProvider
import com.example.mobiquitytask.view.category.MainActivity
import com.example.mobiquitytask.view.category.MainModule
import com.example.mobiquitytask.view.category.productDetail.DetailProvider
import com.google.gson.Gson
import dagger.android.ContributesAndroidInjector
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Singleton
@Component(modules = [AndroidInjectionModule::class, MockComponent.MockAppModule::class, MockComponent.ActivityBuilder::class, MockComponent.MockRetrofitModule::class])
interface MockComponent:AppComponent {
    fun inject(app: TestApplication)
    fun inject(viewModelTest: ViewModelTest)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }


    @Module
   open class MockAppModule {
        @Provides
        @ApiInfo
        fun provideApiKey(): String {
            return ApiEndPoints.BASE_URL
        }


        @Provides
        @Singleton
        fun provideCalligraphyDefaultConfig(): ViewPump {
            return ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/Gerbera.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        }

        @Provides
        @Singleton
        fun provideContext(application: Application): Context {
            return application
        }


        @Provides
        @Singleton
        fun provideApiManager(remotemanager: RemoteManagerImpl): RemoteManager {
            return remotemanager
        }

        @Provides
        @Singleton
        fun provideAppManager(appDataManager: AppDataManager): IAppDataManager {
            return appDataManager
        }


        @Provides
        @Singleton
        fun provideGsonConverter(): Gson {
            return Gson()
        }


        @Provides
        fun provideSchedulerProvider(): SchedulerProvider {
            return AppSchedulerProvider()
        }

    }

    @Module
    class MockRetrofitModule {
        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): AppApiHelper {
            return retrofit.create(AppApiHelper::class.java)
        }

        @Provides
        fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(ApiEndPoints.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }

        @Provides
        @Singleton
        fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.connectTimeout(20, TimeUnit.SECONDS)
            okHttpClient.readTimeout(20, TimeUnit.SECONDS)
            okHttpClient.writeTimeout(20, TimeUnit.SECONDS)
            okHttpClient.addInterceptor(httpLoggingInterceptor)
            okHttpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .header("Accept", "application/json")
                    .build()
                chain.proceed(request)
            })
            return okHttpClient.build()
        }

        @get:Singleton
        @get:Provides
        val httpLoggingInterceptor: HttpLoggingInterceptor
            get() {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                return httpLoggingInterceptor
            }
    }

    @Module
    abstract class ActivityBuilder {
        @ContributesAndroidInjector(modules = [MainModule::class, DetailProvider::class])
        abstract fun bindMainActivity(): MainActivity
    }
}