package com.poop9.poop.data.api

import com.poop9.poop.data.request.SignInRequest
import com.poop9.poop.data.response.Response
import com.poop9.poop.data.response.TokenResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
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
}