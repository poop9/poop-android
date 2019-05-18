package com.poop9.poop.data.api

import com.poop9.poop.data.request.SignInRequest
import com.poop9.poop.data.response.ReportCountResponse
import com.poop9.poop.data.response.ReportRankResponse
import com.poop9.poop.data.response.SignInResponse
import com.poop9.poop.data.response.TokenResponse
import java.util.*

class PoopRepositoryImpl(
    private val service: PoopService
) : PoopRepository {

    override suspend fun today(): ReportCountResponse {
        return service.today().await().result
    }

    override suspend fun week(): ReportCountResponse {
        return service.week().await().result
    }

    override suspend fun month(): ReportCountResponse {
        return service.month().await().result
    }

    override suspend fun list() : List<ReportRankResponse>{
        return service.list().await().result
    }

    private var nickname: String = ""

    override suspend fun signUp(nickname: String): TokenResponse {
        this.nickname = nickname

        return service.signIn(
            SignInRequest(
                UUID.randomUUID().toString(),
                nickname
            )
        ).await().result
    }

    override suspend fun signIn(): TokenResponse {
        if (nickname.isEmpty())
            throw IllegalStateException("Should sign up first.")

        return service.signUp(
            SignInRequest(
                UUID.randomUUID().toString(),
                nickname
            )
        ).await().result
    }
}