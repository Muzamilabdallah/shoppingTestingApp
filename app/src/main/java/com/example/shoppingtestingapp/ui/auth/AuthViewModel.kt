package com.example.shoppingtestingapp.ui.auth


import androidx.lifecycle.*
import com.example.shoppingtestingapp.data.ResponseResult
import com.example.shoppingtestingapp.domain.login.LoginResponse
import com.example.shoppingtestingapp.domain.model.registration.AuthRequestData
import com.example.shoppingtestingapp.domain.model.registration.RegisterResponse
import com.example.shoppingtestingapp.domain.repository.AuthRepo

import kotlinx.coroutines.launch



class AuthViewModel   (private val registerRepo: AuthRepo):ViewModel() {


    private val _response = MutableLiveData<ResponseResult<RegisterResponse>>()

    val registerResponse : LiveData<ResponseResult<RegisterResponse>>
        get() = _response



    fun  register(authRequestData: AuthRequestData){

        _response.postValue(ResponseResult.isLoading)

        viewModelScope.launch {

            val  response=registerRepo.register(authRequestData)

            _response.postValue(response)

        }
    }





    private val _loginresponse = MutableLiveData<ResponseResult<LoginResponse>>()

    val loginResponse : LiveData<ResponseResult<LoginResponse>>
        get() = _loginresponse



    fun  login(authRequestData: AuthRequestData){

        _loginresponse.postValue(ResponseResult.isLoading)

        viewModelScope.launch {

            val  response=registerRepo.login(authRequestData)

            _loginresponse.postValue(response)

        }
    }




}