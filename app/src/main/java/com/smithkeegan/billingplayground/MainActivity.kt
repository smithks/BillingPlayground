package com.smithkeegan.billingplayground

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_billing_library_tab -> {
                    title = getString(R.string.billing_library_test)
                    val billingLibraryFragment = BillingLibraryFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.main_parent_layout, billingLibraryFragment, "Library")
                    fragmentTransaction.commit()
                }
                R.id.menu_billing_api_tab -> {
                    title = getString(R.string.billing_api_test)
                    val billingApiFragment = BillingAPIFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.main_parent_layout, billingApiFragment, "API")
                    fragmentTransaction.commit()
                }
            }
            true
        }

        bottom_navigation_bar.selectedItemId = R.id.menu_billing_library_tab
    }
}
