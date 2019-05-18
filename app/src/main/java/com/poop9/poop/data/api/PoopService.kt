package com.poop9.poop.data.api

import com.poop9.poop.data.request.SignInRequest
import com.poop9.poop.data.response.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit 인터페이스
 */
interface PoopService {
    @POST("api/sign-in")
    fun signIn(
        @Body request: SignInRequest
    ): Response<SignInResponse>
}