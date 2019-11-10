package com.example.money.debtor

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.money.database.PersonDatabaseDao
import kotlinx.coroutines.*
import com.example.money.database.Person

class DebtorViewModel (
    val database: PersonDatabaseDao,
    application: Application
): AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var perSon = MutableLiveData<Person?>()
    val personAll = database.getAllPersons()

    init {
        Log.i("DebtorViewModel", "Craeted!!")
        initializePerson()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun initializePerson() {
        uiScope.launch {
            perSon.value = getPersonFromDatabase()
        }
    }

    private suspend fun getPersonFromDatabase(): Person? {
        return withContext(Dispatchers.IO) {
            var personAll = database.getPerson()
            personAll
        }
    }

}