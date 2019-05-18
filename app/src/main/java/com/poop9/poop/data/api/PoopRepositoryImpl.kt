package com.poop9.poop.data.api

import android.content.SharedPreferences
import com.poop9.poop.data.request.SignInRequest
import com.poop9.poop.data.response.ReportCountResponse
import com.poop9.poop.data.response.ReportRankResponse
import com.poop9.poop.data.response.SignInResponse
import com.poop9.poop.data.response.TokenResponse
import java.util.*

class PoopRepositoryImpl(
    private val service: PoopService,
    private val pref: SharedPreferences
) : PoopRepository {

    companion object {
        const val KEY_NICKNAME = "nickname"
        const val KEY_TOKEN = "token"
    }

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
        pref.edit()
            .putString(KEY_NICKNAME, nickname)
            .apply()

        return service.signIn(
            SignInRequest(
                UUID.randomUUID().toString(),
                nickname
            )
        ).await().result
    }

    override suspend fun signIn(): TokenResponse {
        val nickname = pref.getString(KEY_NICKNAME, null)
        if (nickname.isNullOrEmpty())
            throw IllegalStateException("Should sign up first.")

        return service.signUp(
            SignInRequest(
                UUID.randomUUID().toString(),
                nickname
            )
        ).await().result
    }
}