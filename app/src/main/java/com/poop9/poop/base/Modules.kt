package com.poop9.poop.base

import android.content.Context
import android.content.SharedPreferences
import com.poop9.poop.data.api.PoopRepository
import com.poop9.poop.data.api.PoopRepositoryImpl
import com.poop9.poop.data.api.poop
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { poop() }
    single<SharedPreferences> {
        androidContext().getSharedPreferences("poop", Context.MODE_PRIVATE)
    }
    single<PoopRepository> {
        PoopRepositoryImpl(get(), get())
    }
}