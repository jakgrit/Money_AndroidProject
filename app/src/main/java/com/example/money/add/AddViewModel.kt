package com.example.money.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.money.database.PersonDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class AddViewModel(
    val database: PersonDatabaseDao,
    application: Application
) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _addComplete = MutableLiveData<Boolean>()
    val addComplete: LiveData<Boolean>
        get() = _addComplete


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