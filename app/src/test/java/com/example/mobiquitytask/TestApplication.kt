package com.example.mobiquitytask

import android.app.Application
import android.content.Context
import com.example.mobiquitytask.data.manager.app.AppDataManager
import com.example.mobiquitytask.data.manager.app.IAppDataManager
import com.example.mobiquitytask.data.manager.remote.RemoteManager
import com.example.mobiquitytask.data.manager.remote.RemoteManagerImpl
import com.example.mobiquitytask.data.remote.ApiEndPoints
import com.example.mobiquitytask.data.remote.AppApiHelper
import com.example.mobiquitytask.di.component.AppComponent
import com.example.mobiquitytask.di.component.DaggerAppComponent
import com.example.mobiquitytask.di.module.AppModule
import com.example.mobiquitytask.di.module.RetrofitModule
import com.example.mobiquitytask.utils.rx.AppSchedulerProvider
import com.example.mobiquitytask.utils.rx.SchedulerProvider
import com.google.gson.Gson
import com.synavos.iapps.di.ApiInfo
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

class TestApplication : Application(), HasAndroidInjector {


    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

/*    override fun createDaggerComponent(): AppComponent {
        return DaggerAppComponent.builder().appModule(MockAppModule())
            .retrofitModule(MockRetrofitModule()).build()
    }*/


    /*  @Module
      class MockAppModule {


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
      }*/

}