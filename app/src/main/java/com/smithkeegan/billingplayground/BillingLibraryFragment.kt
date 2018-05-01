package com.smithkeegan.billingplayground

import android.os.Bundle
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.billingclient.api.*
import kotlinx.android.synthetic.main.billing_library_fragment.*

/**
 * Created by WillowTree on 3/22/18.
 */
class BillingLibraryFragment : Fragment(), PurchasesUpdatedListener, BillingClientStateListener{

    lateinit var billingClient: BillingClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.billing_library_fragment, container, false)

        return view
    }

    override fun onResume() {

        billingClient = BillingClient.newBuilder(context!!).setListener(this).build()
        billingClient.startConnection(this)

        check_managed_products_button.setOnClickListener {
            val skulist = ArrayList<String>()
            skulist.add("playgroundmanaged1")
            val paramsBuilder = SkuDetailsParams.newBuilder()
            paramsBuilder.setSkusList(skulist).setType(BillingClient.SkuType.INAPP)
            billingClient.querySkuDetailsAsync(paramsBuilder.build(), object : SkuDetailsResponseListener {
                override fun onSkuDetailsResponse(responseCode: Int, skuDetailsList: MutableList<SkuDetails>?) {
                    var details = String()
                    skuDetailsList?.forEach {
                        details += it.description
                    }
                    val dialog = AlertDialog.Builder(context!!)
                    dialog.setMessage(details)
                    dialog.setTitle("Managed Items")
                    dialog.show()
                }

            })
        }

        super.onResume()
    }

    override fun onPurchasesUpdated(responseCode: Int, purchases: MutableList<Purchase>?) {

    }


    override fun onBillingServiceDisconnected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBillingSetupFinished(responseCode: Int) {
        Toast.makeText(context,"Billing Connected",Toast.LENGTH_SHORT).show()
    }
}