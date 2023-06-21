package com.example.smartremainder.addRemainder

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.Calendar

class addViewModel : ViewModel() {
    var calendar = Calendar.getInstance()
    var tybe = ""
    var name = ""
    var discription = ""
    init {

    }
    fun getTodaysDate(): String? {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        var month: Int = cal.get(Calendar.MONTH)
        month = month + 1
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)
        return "${day}/${month}/${year}"
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("addViewModel","addViewModel Cleared")
    }
}