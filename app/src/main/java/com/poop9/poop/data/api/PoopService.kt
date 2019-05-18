package com.poop9.poop.data.api

import com.poop9.poop.data.request.GpsRequest
import com.poop9.poop.data.request.SignInRequest
import com.poop9.poop.data.response.ReportCountResponse
import com.poop9.poop.data.response.ReportRankResponse
import com.poop9.poop.data.response.Response
import com.poop9.poop.data.response.TokenResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Retrofit 인터페이스
 */
interface PoopService {
    @POST("api/sign-in")
    fun signIn(
        @Body request: SignInRequest
    ): Deferred<Response<TokenResponse>>

    @POST("api/sign-up")
    fun signUp(
        @Body request: SignInRequest
    ): Deferred<Response<TokenResponse>>

    @GET("api/today")
    fun today(
        @Header("authorization") authorization: String
    ): Deferred<Response<ReportCountResponse>>

    @GET("api/month")
    fun month(
        @Header("authorization") authorization: String
    ): Deferred<Response<ReportCountResponse>>

    @GET("api/week")
    fun week(
        @Header("authorization") authorization: String
    ): Deferred<Response<ReportCountResponse>>

    @GET("api/list")
    fun list(
        @Header("authorization") authorization: String
    ): Deferred<List<ReportRankResponse>>

    @POST("api/gps")
    fun gps(
        @Body request: GpsRequest,
        @Header("authorization") authorization: String
    ): Deferred<Unit>
}