package com.example.money.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.money.database.Person
import com.example.money.database.PersonDatabaseDao
import kotlinx.coroutines.*

class ListViewModel (
    val database: PersonDatabaseDao,
    application: Application
): AndroidViewModel(application){
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val personAll = database.getAllPersons()

    init {
        Log.i("DebtorViewModel", "Craeted!!")
    }

    fun clearDB(){
        uiScope.launch {
            clearDatabase()
        }
    }

    private suspend fun clearDatabase() {
        withContext(Dispatchers.IO){
            database.clear()
        }
    }
}