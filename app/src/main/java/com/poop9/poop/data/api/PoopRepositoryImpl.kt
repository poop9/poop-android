package com.poop9.poop.data.api

import com.poop9.poop.data.request.SignInRequest
import com.poop9.poop.data.response.SignInResponse
import java.util.*

class PoopRepositoryImpl(
    private val service: PoopService
) : PoopRepository {
    private var nickname: String = ""

    override suspend fun signUp(nickname: String): SignInResponse {
        this.nickname = nickname

        return service.signIn(
            SignInRequest(
                UUID.randomUUID().toString(),
                nickname
            )
        ).await()
    }

    override suspend fun signIn(): SignInResponse {
        if (nickname.isEmpty())
            throw IllegalStateException("Should sign up first.")

        return service.signUp(
            SignInRequest(
                UUID.randomUUID().toString(),
                nickname
            )
        ).await()
    }
}