package com.poop9.poop.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

abstract class ServiceProvider(
    private val debug: Boolean = true,
    var loggingLevel: Level = if (debug) BODY else NONE
) {

    private fun Builder.cache(debug: Boolean): Builder = when {
        debug -> cache(null)
        else -> this
    }

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply { level = loggingLevel }

    private fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .cache(debug)
        .build()

    abstract fun provideBaseUrl(): String

    abstract fun provideCallAdapterFactory(): CallAdapter.Factory

    private fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder = Retrofit
        .Builder()
        .client(client)
        .baseUrl(provideBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(provideCallAdapterFactory())

    protected fun <T : Any> provideService(
        service: KClass<T>
    ): T = provideHttpLoggingInterceptor()
        .let(::provideOkHttpClient)
        .let(::provideRetrofitBuilder)
        .build()
        .create(service.java)
}