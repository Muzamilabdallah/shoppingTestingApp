package com.example.shoppingtestingapp.domain.repository
import com.acwad.gazk.Webservice.ServiceBuilder
import com.example.shoppingtestingapp.data.ResponseResult
import com.example.shoppingtestingapp.domain.login.LoginResponse
import com.example.shoppingtestingapp.domain.model.registration.AuthRequestData
import com.example.shoppingtestingapp.domain.model.registration.RegisterResponse
import com.example.shoppingtestingapp.domain.usecase.Auth
import com.example.shoppingtestingapp.util.Constant

class AuthRepo  (val apiService: ServiceBuilder) :Auth{


    override suspend fun register(authRequestData: AuthRequestData): ResponseResult<RegisterResponse> {

        return  Resource.safeApiCall(call={apiService.invoke().register(authRequestData)})
    }


    override suspend fun login(authRequestData: AuthRequestData): ResponseResult<LoginResponse> {

        return Resource.safeApiCall(call = {apiService.invoke().login(authRequestData)})
    }
}