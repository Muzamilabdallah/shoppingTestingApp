package com.example.shoppingtestingapp.domain.usecase

import com.example.shoppingtestingapp.data.ResponseResult
import com.example.shoppingtestingapp.domain.login.LoginResponse
import com.example.shoppingtestingapp.domain.model.registration.AuthRequestData
import com.example.shoppingtestingapp.domain.model.registration.RegisterResponse

interface Auth {

    suspend fun register(authRequestData: AuthRequestData):ResponseResult<RegisterResponse>


    suspend fun login(authRequestData: AuthRequestData):ResponseResult<LoginResponse>
}