package com.example.money.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class LoginViewModel (
    application: Application
): AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val _checkLogin = MutableLiveData<Boolean>()
    val checkLogin: LiveData<Boolean>
        get() = _checkLogin

    init {
        _checkLogin.value = false
    }

    fun checkLogin(){
        _checkLogin.value = true
    }
}