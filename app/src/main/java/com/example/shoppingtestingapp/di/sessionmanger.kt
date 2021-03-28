package com.example.shoppingtestingapp.di

import com.order.kiosk.configuration.SessionManger
import org.koin.dsl.module

val sessionManger= module {

    single { SessionManger(get()) }
}