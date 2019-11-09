package com.example.money.add

import android.app.Application
//import android.app.Person
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.money.database.PersonDatabaseDao
import kotlinx.coroutines.*
import com.example.money.database.Person

class AddViewModel(
    val database: PersonDatabaseDao,
    application: Application
) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _addComplete = MutableLiveData<Boolean>()
    val addComplete: LiveData<Boolean>
        get() = _addComplete

    var first_name = MutableLiveData<String>()
    var last_name = MutableLiveData<String>()
    var amout = MutableLiveData<String>()

    init {
        Log.i("initViewModel", "InitAddViewModel")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("AddViewModel","AddViewModel destroyed")
        viewModelJob.cancel()
    }

    fun validateView(){
        uiScope.launch {
            if(!checkInputNull()){
                var newPerson = Person(firstName = first_name.value!!, lastName = last_name.value!!, amount = amout.value!!.toDouble())
                insert(newPerson)
            }
        }
    }

    private fun checkInputNull(): Boolean{
        return (first_name.value == null || last_name.value == null || amout.value == null)
    }

    private suspend fun insert(newPerson: Person) {
        withContext(Dispatchers.IO){
            database.insert(newPerson)
        }
    }
}