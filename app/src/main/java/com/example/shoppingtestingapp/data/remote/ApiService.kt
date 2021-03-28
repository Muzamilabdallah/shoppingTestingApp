package com.example.shoppingtestingapp.data.remote

import com.example.shoppingtestingapp.domain.ProductResponse
import com.example.shoppingtestingapp.domain.login.LoginResponse
import com.example.shoppingtestingapp.domain.model.registration.AuthRequestData
import com.example.shoppingtestingapp.domain.model.registration.RegisterResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @Headers("Accept", " application/json")
@POST("authRequestData")
suspend fun register( @Body authRequestData: AuthRequestData):Response<RegisterResponse>



    @POST("login")
    suspend fun login(@Body authRequestData: AuthRequestData):Response<LoginResponse>




    @GET("shop/products")
    suspend fun getProduct( @Header("Authorization") token:String, @Header("X-Branch") barnchid:Int,@Header("X-Shop") shopId:Int):Response<ProductResponse>

}