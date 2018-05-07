package com.smithkeegan.billingplayground.billingLibrary

import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smithkeegan.billingplayground.R
import kotlinx.android.synthetic.main.billing_library_fragment.*

/**
 * Created by WillowTree on 3/22/18.
 */
class BillingLibraryFragment : Fragment(){

    lateinit var viewModel : BillingLibraryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.billing_library_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(BillingLibraryViewModel::class.java)
        viewModel.alertString.observe(this, Observer<String> { t -> t?.let { displayDialog(it) } })
        return view
    }

    override fun onResume() {

        check_managed_products_button.setOnClickListener {
            viewModel.fetchManagedProducts()
        }

        check_subscription_products_button.setOnClickListener{
            viewModel.fetchSubscriptionProducts()
        }

        purchase_managed_product.setOnClickListener {
            viewModel.purchaseManagedProducts(activity!!)
        }

        purchase_subscription_product.setOnClickListener {
            viewModel.purchaseSubscriptionProducts(activity!!)
        }

        super.onResume()
    }

    fun displayDialog(message: String, title: String = "Product Found") {
        val dialog = AlertDialog.Builder(context!!)
        dialog.setMessage(message)
        dialog.setTitle(title)
        dialog.show()
    }

}