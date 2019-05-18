package com.poop9.poop.data.api

import com.poop9.poop.data.response.TokenResponse

interface PoopRepository {
    suspend fun signUp(nickname: String): TokenResponse
    suspend fun signIn(): TokenResponse
}