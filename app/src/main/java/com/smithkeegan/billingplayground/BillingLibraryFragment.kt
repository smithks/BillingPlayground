package com.smithkeegan.billingplayground

import android.os.Bundle
import android.support.v4.app.Fragment;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by WillowTree on 3/22/18.
 */
class BillingLibraryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.billing_library_fragment, container, false)
    }

}