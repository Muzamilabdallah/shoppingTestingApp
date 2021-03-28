package com.example.shoppingtestingapp.di

import android.app.Application

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@application)  // 3
            modules(
                listOf(

                     sessionManger,registerRepo,registationviewModel,servicebuilder,producRepo,mainViewModel
                )
            )
        }
    }


}