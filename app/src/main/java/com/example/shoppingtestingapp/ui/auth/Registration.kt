package com.example.shoppingtestingapp.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.example.shoppingtestingapp.data.ResponseResult
import com.example.shoppingtestingapp.databinding.ActivityRegistrationBinding
import com.example.shoppingtestingapp.domain.model.registration.AuthRequestData
import com.example.shoppingtestingapp.extention.toast
import com.example.shoppingtestingapp.ui.main.MainActivity
import com.example.shoppingtestingapp.util.Constant
import com.example.shoppingtestingapp.util.Constant.EMAIL_ADDRESS_PATTERN
import com.order.kiosk.configuration.SessionManger
import com.raha.app.extention.hasNetwork
import com.raha.app.extention.invisible
import com.raha.app.extention.visible
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class Registration : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    val sessionManger: SessionManger by inject()
    val viewModel: AuthViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)

        setContentView(binding.root)


        Constant.androidV = "Android" + "" + Build.VERSION.SDK_INT



        validateEmail()

        validatePassword()

        binding.register.setOnClickListener {


            if (hasNetwork()!!)
                register()
            else
                toast("No Internet available")
        }


    }


    fun register() {

        val email = binding.Email.text.toString()
        val password = binding.password.text.toString()
        val confirm = binding.confirm.text.toString()


        if (!isValidEmail(email))
            binding.textInputLayout.error = "The Email is not valid"
        else if (!Constant.isValidPassword(password)) {
            binding.textInputLayout2.error =
                "passowrd must be combination from digits and char and symbols using at least one letter in uppercase"

            binding.textInputLayout.error = null

        } else if (!password.equals(confirm)) {
            binding.textInputLayout3.error = "passowrd dosent match"
            binding.textInputLayout2.error = null

        } else {
            val register = AuthRequestData(email, password)

            viewModel.register(register)

            viewModel.registerResponse.observe(this, Observer {

                when (it) {


                    is ResponseResult.isLoading -> {
                        binding.progressBar.visible()

                    }


                    is ResponseResult.Success -> {
                        Log.e("SignupResponse", it.data.toString() + "")
                        binding.progressBar.invisible()

                        if (it.data.success) {
                            sessionManger.save(sessionManger.accessToken, it.data.data.token)
                            sessionManger.save(sessionManger.isLogin, true)



                            login(register)


                        } else {
                            toast("email already taken")


                        }


                    }

                    is ResponseResult.Error -> {
                        binding.progressBar.invisible()
                        Log.e("data", it.toString() + "")

                    }

                    is ResponseResult.NetworkError -> {

                        toast("check internet")
                    }


                }
            })

        }
    }

    private fun login(data: AuthRequestData) {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Login...")
        viewModel.login(data)

        viewModel.loginResponse.observe(this, Observer { response ->

            when (response) {


                is ResponseResult.isLoading -> {


                    progressDialog.show()

                }


                is ResponseResult.Success -> {
                    progressDialog.hide()

                    Log.e("loginResponse",response.data.toString()+"")

                    if (response.data.success){

                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }

                }

                is ResponseResult.Error -> {
                    Log.e("loginResponse",response.toString()+"")
                    progressDialog.hide()

                }
            }
        })

    }


    private fun isValidEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }


    private fun validateEmail() {

        binding.Email.doAfterTextChanged {

            if (!isValidEmail(binding.Email.text.toString()))
                binding.textInputLayout.error = "The Email is not valid"
            else
                binding.textInputLayout.error = null

        }


    }

    private fun validatePassword() {
        binding.password.doAfterTextChanged {

            if (!Constant.isValidPassword(binding.password.text.toString()))
                binding.textInputLayout2.error =
                    "passowrd must be combination from number and char using at least one letter in uppercase"
            else
                binding.textInputLayout2.error = null


        }

    }


}