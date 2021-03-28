package com.example.shoppingtestingapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingtestingapp.data.ResponseResult
import com.example.shoppingtestingapp.domain.ProductResponse
import com.example.shoppingtestingapp.domain.repository.ProducRepo
import kotlinx.coroutines.launch

class MainViewModel(val ProducRepo: ProducRepo) : ViewModel() {


    private val _productResponse = MutableLiveData<ResponseResult<ProductResponse>>()


    val productResponse: LiveData<ResponseResult<ProductResponse>>
        get() = _productResponse


    fun getALLProducts(token: String, brarchId: Int, shopId: Int) {


        _productResponse.postValue(ResponseResult.isLoading)


        viewModelScope.launch {

            val respo = ProducRepo.getAllproducts(token, brarchId, shopId)


            _productResponse.postValue(respo)
        }
    }


}