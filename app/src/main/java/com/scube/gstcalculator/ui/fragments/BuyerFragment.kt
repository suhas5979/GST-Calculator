package com.scube.gstcalculator.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.scube.gstcalculator.R
import com.scube.gstcalculator.adapters.CardAdapter
import com.scube.gstcalculator.models.Card
import com.scube.gstcalculator.viewmodels.BuyerViewModel
import kotlinx.android.synthetic.main.fragment_buyer.view.*
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.fragment_buyer.*

class BuyerFragment : Fragment() {

    lateinit var buyerViewModel: BuyerViewModel
    lateinit var mView: View
    private var mGstRate = 0.0
    private var mPrice = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buyerViewModel = ViewModelProvider(requireActivity()).get(BuyerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_buyer, container, false)
        setUpRecyclerView(mView)
        subscribeToObservers()
        mView.seekBarGstRate.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val gst:Double = p1.toDouble() * 30 /100
                mView.tvGstRate.text = gst.toString()
                mGstRate = gst
                Log.i("debug","$gst")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.i("debug","start")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.i("debug","end")
            }

        })
        mView.btnCalculate.setOnClickListener {
            var price = 0
            if(!TextUtils.isEmpty(mView.netPrice.text.toString())) price = mView.netPrice.text.toString().toInt()
            buyerViewModel.calculateGst(price.toDouble(), mGstRate)
        }
        return mView
    }

    private fun setUpRecyclerView(mView: View) {
        mView.buyersRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
    }

    private fun subscribeToObservers() {
        buyerViewModel.result.observe(viewLifecycleOwner) { cards ->
            val adapter = CardAdapter(requireActivity(), ArrayList(cards))
            mView.buyersRecyclerView.adapter = adapter
        }
    }
}