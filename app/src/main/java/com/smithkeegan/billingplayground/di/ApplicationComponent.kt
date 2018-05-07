package com.smithkeegan.billingplayground.di

import com.smithkeegan.billingplayground.billingLibrary.BillingLibraryClient
import com.smithkeegan.billingplayground.billingLibrary.BillingLibraryFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by WillowTree on 5/1/18.
 */
@Component(modules = arrayOf(ApplicationModule::class))
@Singleton
interface ApplicationComponent {
    fun billingClient() : BillingLibraryClient
}