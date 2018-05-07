package com.smithkeegan.billingplayground.billingApi

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smithkeegan.billingplayground.R

/**
 * Created by WillowTree on 3/22/18.
 */
class BillingAPIFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.billing_api_fragment, container, false)
    }
}