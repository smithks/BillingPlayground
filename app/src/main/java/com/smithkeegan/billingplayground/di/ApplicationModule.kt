package com.smithkeegan.billingplayground.di

import android.content.Context
import com.smithkeegan.billingplayground.billingLibrary.BillingLibraryClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by WillowTree on 5/1/18.
 */
@Module
class ApplicationModule (private val appContext: Context){

    @Provides
    fun applicationContext(): Context = appContext

}