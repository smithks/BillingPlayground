package com.smithkeegan.billingplayground.billingLibrary

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.SkuType
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by WillowTree on 5/1/18.
 */
@Singleton
class BillingLibraryClient @Inject constructor(context: Context) : BillingClientStateListener, PurchasesUpdatedListener {

    private val billingClient: BillingClient = BillingClient.newBuilder(context).setListener(this).build()
    var libraryListener : BillingLibraryListener? = null
    var serviceConnected : Boolean = false
    var pendingRunnable : Runnable? = null

    fun connect() = billingClient.startConnection(this)

    fun disconnect() = billingClient.endConnection()

    fun setListener(listener: BillingLibraryListener) {
        libraryListener = listener
    }

    val managedSku = "playgroundmanaged1"
    val subscriptionSku = "playgroundsubscription1"

    fun processRequest(){
        if (serviceConnected) {
            pendingRunnable?.run()
            pendingRunnable = null
        }
        else {
            connect()
        }
    }

    fun checkProducts(productType : String) {
        val runnable = Runnable {
            val paramsBuilder = SkuDetailsParams.newBuilder()
            paramsBuilder.setSkusList(if (productType == SkuType.INAPP) arrayListOf(managedSku) else arrayListOf(subscriptionSku)).setType(productType)
            billingClient.querySkuDetailsAsync(paramsBuilder.build(), libraryListener)
        }
        pendingRunnable = runnable
        processRequest()
    }

    fun purchaseProduct(activity: Activity, productType: String) {
            val runnable = Runnable {
                if (billingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS) == BillingClient.BillingResponse.OK) {
                    val params = BillingFlowParams.newBuilder()
                            .setSku(if (productType == SkuType.INAPP) managedSku else subscriptionSku)
                            .setType(productType)
                            .build()
                    billingClient.launchBillingFlow(activity, params)
                }
            }
            pendingRunnable = runnable
            processRequest()
    }

    override fun onPurchasesUpdated(responseCode: Int, purchases: MutableList<Purchase>?) {
        libraryListener?.onPurchaseInfoReceived(responseCode, purchases)
    }


    override fun onBillingServiceDisconnected() {
        libraryListener?.onBillingDisconnected()
    }

    override fun onBillingSetupFinished(responseCode: Int) {
        if (responseCode == BillingClient.BillingResponse.OK){
            serviceConnected = true
            pendingRunnable?.run()
            pendingRunnable = null
        }
    }

}