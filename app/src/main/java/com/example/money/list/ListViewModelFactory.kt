package com.example.money.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.money.database.PersonDatabaseDao
import com.example.money.debtor.DebtorViewModel
import java.lang.IllegalArgumentException

class ListViewModelFactory (
    private val dataSource : PersonDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListViewModel::class.java)){
            return ListViewModel(dataSource,application) as T
        }else{
            throw IllegalArgumentException("Unknow!")
        }
    }

}