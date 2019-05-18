package com.poop9.poop.data.api

import com.poop9.poop.data.request.SignInRequest
import com.poop9.poop.data.response.ReportCountResponse
import com.poop9.poop.data.response.ReportRankResponse
import com.poop9.poop.data.response.SignInResponse
import kotlinx.coroutines.Deferred

/**
 * Retrofit 인터페이스
 */
interface PoopService {
    @POST("api/sign-in")
    fun signIn(
        @Body request: SignInRequest
    ): Deferred<SignInResponse>

    @POST("api/sign-up")
    fun signUp(
        @Body request: SignInRequest
    ): Deferred<SignInResponse>

    @GET("api/today")
    fun today(): Deferred<ReportCountResponse>

    @GET("api/month")
    fun month(): Deferred<ReportCountResponse>

    @GET("api/week")
    fun week(): Deferred<ReportCountResponse>

    @GET("api/list")
    fun list(): Deferred<List<ReportRankResponse>>
}