package com.poop9.poop.data.api

import com.poop9.poop.BuildConfig
import io.reactivex.schedulers.Schedulers
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object PoopServiceProvider : ServiceProvider(BuildConfig.DEBUG) {

    // TODO: Endpoint
    override fun provideBaseUrl(): String = ""

    override fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory
        .createWithScheduler(Schedulers.io())

    val service: PoopService by lazy { provideService(PoopService::class) }
}

// 간편하게 쓸 수 있는 메서드
fun poop(
    init: PoopServiceProvider.() -> Unit = {}
): PoopService = PoopServiceProvider.run {
    init(this)
    service
}
