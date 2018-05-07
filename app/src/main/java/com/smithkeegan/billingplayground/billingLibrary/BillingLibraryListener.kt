package com.smithkeegan.billingplayground.billingLibrary

import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsResponseListener

/**
 * Created by WillowTree on 5/2/18.
 */
interface BillingLibraryListener : SkuDetailsResponseListener {
    override fun onSkuDetailsResponse(responseCode: Int, skuDetailsList: MutableList<SkuDetails>?)

    fun onPurchaseInfoReceived(responseCode: Int, purchases: MutableList<Purchase>?)

    fun onBillingDisconnected()

}