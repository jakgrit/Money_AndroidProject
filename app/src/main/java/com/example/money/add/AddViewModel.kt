package com.example.money.add

import android.app.Application
//import android.app.Person
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.money.database.PersonDatabaseDao
import kotlinx.coroutines.*
import com.example.money.database.Person

class AddViewModel(
    val database: PersonDatabaseDao,
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _addComplete = MutableLiveData<Boolean>()
    val addComplete: LiveData<Boolean>
        get() = _addComplete

    private val _goBackToMenu = MutableLiveData<Boolean>()
    val goBackToMenu: LiveData<Boolean>
        get() = _goBackToMenu

    var fname = MutableLiveData<String>()
    var lname = MutableLiveData<String>()
    var amout = MutableLiveData<String>()

    init {
        Log.i("initViewModel", "InitAddViewModel")
        _goBackToMenu.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("AddViewModel","AddViewModel destroyed")
        viewModelJob.cancel()
    }

    fun validateView(){
        uiScope.launch {
            if(!checkInputNull()){
                var newPerson = Person(firstName = fname.value!!, lastName = lname.value!!, amount = amout.value!!.toDouble())
                insert(newPerson)
                _addComplete.value = true
                Log.i("SaveToDB", "Success")
            }
        }
    }

    fun clickBack(){
        _goBackToMenu.value = true
    }

    private fun checkInputNull(): Boolean{
        return (fname.value == null || lname.value == null || amout.value == null)
    }

    private suspend fun insert(newPerson: Person) {
        withContext(Dispatchers.IO){
            database.insert(newPerson)
        }
    }
}