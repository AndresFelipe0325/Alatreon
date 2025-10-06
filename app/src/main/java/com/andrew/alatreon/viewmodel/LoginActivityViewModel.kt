package com.andrew.alatreon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginActivityViewModel : ViewModel() {
    private lateinit var _authSuccess: MutableLiveData<Boolean>
    val authSuccess: LiveData<Boolean>
        get() = _authSuccess
    private lateinit var _authError: MutableLiveData<Boolean>
    val authError: LiveData<Boolean>
        get() = _authError
    private lateinit var _loading: MutableLiveData<Boolean>
    val loading: LiveData<Boolean>
        get() = _loading


    init {
        _authSuccess.value = false
        _authError.value = false
        _loading.value = false
    }

    fun login(username: String, password: String) {
        if(!username.isEmpty() && !password.isEmpty()) {
            _authSuccess.value = true
            _loading.value = false

        }
        else {
            _loading.value = true
            _authSuccess.value = false
        }
    }

}