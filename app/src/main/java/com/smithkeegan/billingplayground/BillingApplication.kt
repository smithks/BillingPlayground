package com.smithkeegan.billingplayground

import android.app.Application
import com.smithkeegan.billingplayground.di.ApplicationComponent
import com.smithkeegan.billingplayground.di.ApplicationModule
import com.smithkeegan.billingplayground.di.DaggerApplicationComponent

/**
 * Created by WillowTree on 5/1/18.
 */
class BillingApplication : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}