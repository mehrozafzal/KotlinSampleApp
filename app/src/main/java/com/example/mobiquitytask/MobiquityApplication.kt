package com.example.mobiquitytask

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.mobiquitytask.di.component.AppComponent
import com.example.mobiquitytask.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.github.inflationx.viewpump.ViewPump
import javax.inject.Inject


open class MobiquityApplication : MultiDexApplication(), HasAndroidInjector {

    companion object {
        private lateinit var instance: MobiquityApplication
        fun getInstance(): MobiquityApplication {
            return instance
        }
    }

    lateinit var appComponent: AppComponent

    @Inject
    open lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewPump: ViewPump

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
        val appComponent = createDaggerComponent()
        appComponent.inject(this)
        ViewPump.init(viewPump)
    }

    open fun createDaggerComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    /**
     * Visible only for testing purposes.
     */
    // @VisibleForTesting
    open fun setTestComponent(appComponent: AppComponent) {
        this.appComponent = appComponent
    }
}
