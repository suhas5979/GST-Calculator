package com.scube.gstcalculator.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.scube.gstcalculator.models.Card

class ManufacturerViewModel(application: Application): AndroidViewModel(application) {
    private val _result = MutableLiveData<List<Card>>()
    val result : LiveData<List<Card>> = _result

    fun calculateGstForManufacturer(amount:Double, profit:Double ,gst: Double){
        val GST = amount * (gst/100)
        val profitCalculated = GST * (profit /100)
        val total = amount + amount * (profit /100)
        val totalGst = GST + profitCalculated
        val CGST = totalGst/2
        val SGST = totalGst/2
        val card1 = Card("Total Cost of Production",total.toString())
        val card2 = Card("CGST",CGST.toString())
        val card3 = Card("SGST",SGST.toString())
        val card4 = Card("Total Tax",totalGst.toString())
        val cards = ArrayList<Card>()
        cards.add(card1)
        cards.add(card2)
        cards.add(card3)
        cards.add(card4)
        _result.value = cards
    }
}