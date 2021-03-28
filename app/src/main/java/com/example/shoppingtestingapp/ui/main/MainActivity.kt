package com.example.shoppingtestingapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingtestingapp.data.ResponseResult
import com.example.shoppingtestingapp.databinding.ActivityMainBinding
import com.example.shoppingtestingapp.extention.toast
import com.example.shoppingtestingapp.ui.auth.ProductAdapter
import com.order.kiosk.configuration.SessionManger
import com.raha.app.extention.hasNetwork
import com.raha.app.extention.invisible
import com.raha.app.extention.visible
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val sessionManger: SessionManger by inject()

    val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initiateView()

        val token = sessionManger.getValueString(sessionManger.accessToken)

        Log.e("token",token+"");


        if (hasNetwork()!!)
        getProduct(token, 9, 46)
        else
            toast("No Internet")
    }

    private fun initiateView() {

        val layoutManager = LinearLayoutManager(this)

        binding.product.layoutManager = layoutManager


    }

    private fun getProduct(token: String?, i: Int, i1: Int) {

        viewModel.getALLProducts(token!!, i, i1)


        viewModel.productResponse.observe(this, Observer {

            when (it) {


                is ResponseResult.isLoading -> {

                    binding.progressBar2.visible()

                }

                is ResponseResult.Success -> {
                    binding.progressBar2.invisible()
                    Log.e("productResponse",it.data.toString())
                    if (it.data.success) {

                        val adapter = ProductAdapter(this,it.data.data)
                        binding.product.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }

                }


                is ResponseResult.Error -> {

                    Log.e("productResponse",it.toString())
                    binding.progressBar2.invisible()

                }
            }
        })
    }
}