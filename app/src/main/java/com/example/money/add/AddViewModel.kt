package com.example.money.add

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlin.math.log

class AddViewModel : ViewModel() {
    var first_name = ""
    var last_name = ""
    var amout = 0.0

    init {
        Log.i("initViewModel", "InitAddViewModel")
    }

    fun testLog(){
        Log.i("initViewModel", "TestLog!!")
    }
}