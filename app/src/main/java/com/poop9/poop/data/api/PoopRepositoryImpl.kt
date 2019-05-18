package com.poop9.poop.data.api

import com.poop9.poop.data.request.SignInRequest
import com.poop9.poop.data.response.ReportCountResponse
import com.poop9.poop.data.response.ReportRankResponse
import com.poop9.poop.data.response.SignInResponse
import java.util.*

class PoopRepositoryImpl(
    private val service: PoopService
) : PoopRepository {

    override suspend fun today(): ReportCountResponse {
        return service.today().await()
    }

    override suspend fun week(): ReportCountResponse {
        return service.week().await()
    }

    override suspend fun month(): ReportCountResponse {
        return service.month().await()
    }

    override suspend fun list() : List<ReportRankResponse>{
        return service.list().await()
    }

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