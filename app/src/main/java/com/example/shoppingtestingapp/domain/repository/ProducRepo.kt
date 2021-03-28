package com.example.shoppingtestingapp.domain.repository

import com.acwad.gazk.Webservice.ServiceBuilder
import com.example.shoppingtestingapp.data.ResponseResult
import com.example.shoppingtestingapp.di.servicebuilder
import com.example.shoppingtestingapp.domain.ProductResponse
import com.example.shoppingtestingapp.util.Constant

class ProducRepo(val serviceBuilder: ServiceBuilder) {


    suspend fun getAllproducts(token:String,brarchId:Int,shopId:Int):ResponseResult<ProductResponse>{

        return Resource.safeApiCall(call = { serviceBuilder.invoke().getProduct("Bearer "+token,brarchId,shopId)})
    }
}