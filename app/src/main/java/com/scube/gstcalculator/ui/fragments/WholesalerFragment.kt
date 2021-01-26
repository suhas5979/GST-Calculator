package com.scube.gstcalculator.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.scube.gstcalculator.R
import com.scube.gstcalculator.adapters.CardAdapter
import com.scube.gstcalculator.viewmodels.WholesalerViewModel
import kotlinx.android.synthetic.main.fragment_manufacturer.view.*
import kotlinx.android.synthetic.main.fragment_wholesaler.view.*

class WholesalerFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var wholesalerViewModel: WholesalerViewModel
    private  var mGstRate =0.0
    private var mProfitRate = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wholesalerViewModel = ViewModelProvider(requireActivity()).get(WholesalerViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_wholesaler, container, false)
        setUpRecyclerView(mView)
        subscribeToObservers()
        mView.seekBarGstRateWholesaler.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val gst:Double = p1.toDouble() * 30 /100
                mView.tvGstRateWholesaler.text = gst.toString()
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
        mView.seekBarProfitRateWholesaler.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val gst:Double = p1.toDouble() * 30 /100
                mView.tvProfitRateWholesaler.text = gst.toString()
                mProfitRate = gst
                Log.i("debug","$gst")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.i("debug","start")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.i("debug","end")
            }

        })
        mView.btnCalculateWholesaler.setOnClickListener {
            var price = 0
            if(!TextUtils.isEmpty(mView.costGoods.text.toString())) price = mView.costGoods.text.toString().toInt()
            wholesalerViewModel.calculateGstForWholesaler(price.toDouble(),mProfitRate, mGstRate)
        }

        return mView
    }

    private fun setUpRecyclerView(mView: View) {
        mView.wholesalerRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
    }

    private fun subscribeToObservers() {
        wholesalerViewModel.result.observe(viewLifecycleOwner) { cards ->
            for(card in cards) Log.i("debug",card.title.toString())
            val adapter = CardAdapter(requireActivity(), ArrayList(cards))
            mView.wholesalerRecyclerView.adapter = adapter
        }
    }
}