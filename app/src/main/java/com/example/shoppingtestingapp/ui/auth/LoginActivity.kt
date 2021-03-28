package com.example.shoppingtestingapp.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.shoppingtestingapp.data.ResponseResult
import com.example.shoppingtestingapp.databinding.ActivityLoginBinding
import com.example.shoppingtestingapp.domain.model.registration.AuthRequestData
import com.example.shoppingtestingapp.extention.toast
import com.example.shoppingtestingapp.ui.main.MainActivity
import com.order.kiosk.configuration.SessionManger
import com.raha.app.extention.hasNetwork
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    val viewModel: AuthViewModel by viewModel()
    val sessionManger: SessionManger by inject()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val islogin = sessionManger.getValueBoolien(sessionManger.isLogin, false)




//        if (islogin){
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//
//        }


            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)



        binding.signup.setOnClickListener {

            startActivity(Intent(this, Registration::class.java))
        }



        binding.Login.setOnClickListener {

            val email = binding.Email.text.toString()

            val passord = binding.password.text.toString()

            if (email.isEmpty())
                binding.textInputLayout.error = "Enter Email Address"
            else if (passord.isEmpty()) {
                binding.textInputLayout2.error = "Enter your password"

                binding.textInputLayout.error = null
            } else {


                val authRequestData = AuthRequestData(email, passord)

                if (hasNetwork()!!)
                    login(authRequestData)
                else
                    toast("NO Internet available")

            }

        }
    }


    private fun login(data: AuthRequestData) {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Login...")

        Log.e("logindata", data.toString() + "")
        viewModel.login(data)

        viewModel.loginResponse.observe(this, Observer { response ->

            when (response) {


                is ResponseResult.isLoading -> {


                    progressDialog.show()

                }


                is ResponseResult.Success -> {
                    progressDialog.hide()

                    Log.e("loginResponse", response.data.toString() + "")

                    if (response.data.success) {

                        sessionManger.save(sessionManger.accessToken, response.data.data.token)

                        startActivity(Intent(this, MainActivity::class.java))

                        finish()

                    }
                }

                is ResponseResult.Error -> {
                    Log.e("loginResponse", response.toString() + "")

                    progressDialog.hide()

                    if (response.exception.equals("401"))

                        toast("Invalid Credential")


                }
            }
        })

    }

}