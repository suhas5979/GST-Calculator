package com.scube.gstcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.scube.gstcalculator.adapters.PagerAdapter
import com.scube.gstcalculator.ui.fragments.BuyerFragment
import com.scube.gstcalculator.ui.fragments.ManufacturerFragment
import com.scube.gstcalculator.ui.fragments.WholesalerFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragments = ArrayList<Fragment>()
        fragments.add(BuyerFragment())
        fragments.add(ManufacturerFragment())
        fragments.add(WholesalerFragment())

        val titles = ArrayList<String>()
        titles.add("Buyer")
        titles.add("Manufacturer")
        titles.add("Wholesaler")

        val bundle = Bundle()
        bundle.putString("Test","String")
        val adapter = PagerAdapter(bundle,fragments,titles,supportFragmentManager)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }
}