package com.poop9.poop.data.api

import com.poop9.poop.data.response.SignInResponse

interface PoopRepository {
    suspend fun signUp(nickname: String): SignInResponse
    suspend fun signIn(): SignInResponse
}