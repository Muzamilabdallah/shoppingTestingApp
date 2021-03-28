package com.example.shoppingtestingapp.di


import com.acwad.gazk.Webservice.ServiceBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val servicebuilder = module {
    // 1

    single {

        ServiceBuilder(androidApplication())
    }


}



