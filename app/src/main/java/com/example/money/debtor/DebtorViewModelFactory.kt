package com.example.money.debtor

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.money.database.PersonDatabaseDao
import java.lang.IllegalArgumentException

class DebtorViewModelFactory (
    private val dataSource : PersonDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DebtorViewModel::class.java)){
            return DebtorViewModel(dataSource,application) as T
        }else{
            throw IllegalArgumentException("Unknow!")
        }
    }

}