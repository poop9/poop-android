package com.poop9.poop.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.poop9.poop.BuildConfig
import retrofit2.CallAdapter

object PoopServiceProvider : ServiceProvider(BuildConfig.DEBUG) {

    // TODO: Endpoint
    override fun provideBaseUrl(): String = ""

    override fun provideCallAdapterFactory(): CallAdapter.Factory = CoroutineCallAdapterFactory()

    val service: PoopService by lazy { provideService(PoopService::class) }
}

// 간편하게 쓸 수 있는 메서드
fun poop(
    init: PoopServiceProvider.() -> Unit = {}
): PoopService = PoopServiceProvider.run {
    init(this)
    service
}
