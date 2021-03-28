package com.example.shoppingtestingapp.di

import com.example.shoppingtestingapp.domain.repository.AuthRepo
import com.example.shoppingtestingapp.domain.repository.ProducRepo
import com.example.shoppingtestingapp.ui.auth.AuthViewModel
import com.example.shoppingtestingapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val registerRepo= module {

    single { AuthRepo(get()) }
}


val registationviewModel= module {
    viewModel { AuthViewModel(get()) }
}


val producRepo= module {
    single { ProducRepo(get()) }
}


val mainViewModel= module {
    viewModel { MainViewModel(get()) }
}