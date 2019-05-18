package com.poop9.poop.base

import com.poop9.poop.data.api.PoopRepository
import com.poop9.poop.data.api.PoopRepositoryImpl
import com.poop9.poop.data.api.poop
import org.koin.dsl.module

val appModule = module {
    single { poop() }
    single<PoopRepository> { PoopRepositoryImpl(get()) }
}