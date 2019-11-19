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

    val personAll = database.getAllPersons()

    init {
        Log.i("DebtorViewModel", "Craeted!!")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}