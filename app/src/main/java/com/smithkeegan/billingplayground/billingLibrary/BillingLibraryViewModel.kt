package com.smithkeegan.billingplayground.billingLibrary

import android.app.Activity
import android.arch.lifecycle.ViewModel
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsResponseListener
import com.smithkeegan.billingplayground.BillingApplication
import com.smithkeegan.billingplayground.SingleLiveEvent
import java.lang.StringBuilder

/**
 * Created by WillowTree on 5/1/18.
 */
class BillingLibraryViewModel : ViewModel(), BillingLibraryListener{

    var billingClient: BillingLibraryClient

    init {
        billingClient = BillingApplication.appComponent.billingClient()
        billingClient.setListener(this)
    }

    val alertString : SingleLiveEvent<String> = SingleLiveEvent()

    fun fetchManagedProducts() {
        billingClient.checkProducts(BillingClient.SkuType.INAPP)
    }

    fun fetchSubscriptionProducts() {
        billingClient.checkProducts(BillingClient.SkuType.SUBS)
    }

    fun purchaseManagedProducts(activity: Activity) {
        billingClient.purchaseProduct(activity, BillingClient.SkuType.INAPP)
    }

    fun purchaseSubscriptionProducts(activity: Activity) {
        billingClient.purchaseProduct(activity, BillingClient.SkuType.SUBS)
    }

    override fun onPurchaseInfoReceived(responseCode: Int, purchases: MutableList<Purchase>?) {
        if (responseCode == BillingClient.BillingResponse.OK){
            alertString.setValue("Purchase OK")
        }
    }

    override fun onSkuDetailsResponse(responseCode: Int, skuDetailsList: MutableList<SkuDetails>?) {
        if (responseCode == BillingClient.BillingResponse.OK){
            val details = StringBuilder()
            skuDetailsList?.forEach {
                details.append(it.title).append("\n")
                        .append(it.price).append("\n")
                        .append(it.description).append("\n")
            }
            alertString.setValue(details.toString())
        }
    }

    override fun onBillingDisconnected() {
        alertString.setValue("Error occurred")
    }

}