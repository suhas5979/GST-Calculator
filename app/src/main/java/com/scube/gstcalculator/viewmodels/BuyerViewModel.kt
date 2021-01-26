package com.scube.gstcalculator.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.scube.gstcalculator.models.Card

class BuyerViewModel(application: Application): AndroidViewModel(application) {
    private val _result = MutableLiveData<List<Card>>()
    val result : LiveData<List<Card>> = _result

    private val _manufacturerCards = MutableLiveData<List<Card>>()
    val manufacturerCards : LiveData<List<Card>> = _manufacturerCards

    private val _wholesalerCards = MutableLiveData<List<Card>>()
    val wholesalerCards : LiveData<List<Card>> = _wholesalerCards

    fun calculateGst(amount:Double ,gst: Double){
       val GST = amount * (gst/100)
        val total = amount + GST
        val CGST = GST/2
        val SGST = GST/2
        val card1 = Card("Total Cost",total.toString())
        val card2 = Card("CGST",CGST.toString())
        val card3 = Card("SGST",SGST.toString())
        val card4 = Card("Total Tax",GST.toString())
       val cards = ArrayList<Card>()
        cards.add(card1)
        cards.add(card2)
        cards.add(card3)
        cards.add(card4)
        _result.value = cards
    }
    fun calculateGstForManufacturer(amount:Double, profit:Double ,gst: Double){
        val GST = amount * (gst/100)
        val profitCalculated = GST * (profit /100)
        val total = amount + amount * (profit /100)
        val totalGst = GST + profitCalculated
        val CGST = totalGst/2
        val SGST = totalGst/2
        val card1 = Card("Total Cost",total.toString())
        val card2 = Card("CGST",CGST.toString())
        val card3 = Card("SGST",SGST.toString())
        val card4 = Card("Total Tax",totalGst.toString())
        val cards = ArrayList<Card>()
        cards.add(card1)
        cards.add(card2)
        cards.add(card3)
        cards.add(card4)
        _manufacturerCards.value = cards
    }

    fun calculateGstForWholesaler(amount:Double, profit:Double ,gst: Double){
        val GST = amount * (gst/100)
        val profitCalculated = GST * (profit /100)
        val total = amount + amount * (profit /100)
        val totalGst = GST + profitCalculated
        val CGST = totalGst/2
        val SGST = totalGst/2
        val card1 = Card("Total Cost",total.toString())
        val card2 = Card("CGST",CGST.toString())
        val card3 = Card("SGST",SGST.toString())
        val card4 = Card("Total Tax",totalGst.toString())
        val cards = ArrayList<Card>()
        cards.add(card1)
        cards.add(card2)
        cards.add(card3)
        cards.add(card4)
        _wholesalerCards.value = cards
    }
}