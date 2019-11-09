package com.example.money.add

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.money.database.PersonDatabaseDao
import java.lang.IllegalArgumentException

class AddViewModelFactory (
    private val dataSource: PersonDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(dataSource,application) as T
        }else{
            throw IllegalArgumentException("Unknown Register ViewModel class")
        }
    }
}