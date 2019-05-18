package com.poop9.poop.data.api

import android.content.SharedPreferences
import com.poop9.poop.data.request.GpsRequest
import com.poop9.poop.data.request.SignInRequest
import com.poop9.poop.data.response.ReportCountResponse
import com.poop9.poop.data.response.ReportRankResponse
import com.poop9.poop.data.response.TokenResponse
import com.poop9.poop.model.LocationData
import java.util.*

class PoopRepositoryImpl(
    private val service: PoopService,
    private val pref: SharedPreferences
) : PoopRepository {

    companion object {
        const val KEY_NICKNAME = "nickname"
        const val KEY_TOKEN = "token"
    }

    override suspend fun getToken(): String {
        return token()
    }

    private fun token(): String {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1dWlkIjoiMmY0OGYyNDEtOWQ2NC00ZDE2LWJmNTYtNzBiOWQ0ZTBlNzlhIiwibmlja25hbWUiOiJoZWV3b24iLCJpYXQiOjE1NTgyMDQ0NDUsImV4cCI6MTU2MDc5NjQ0NX0.emRjXgTMuD5hT58l55G5D0muzozIEDjmcGIUX3ax5cQ"
    }

    override suspend fun today(): ReportCountResponse {
        return service.today(token()).await().result
    }

    override suspend fun week(): ReportCountResponse {
        return service.week(token()).await().result
    }

    override suspend fun month(): ReportCountResponse {
        return service.month(token()).await().result
    }

    override suspend fun list(): List<ReportRankResponse> {
        return service.list(token()).await().result
    }

    override suspend fun signUp(nickname: String): TokenResponse {
        val token = service.signIn(
            SignInRequest(
                UUID.randomUUID().toString(),
                nickname
            )
        ).await().result

        pref.edit()
            .putString(KEY_NICKNAME, nickname)
            .putString(KEY_TOKEN, token.token)
            .apply()

        return token
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

    override suspend fun gps(locationData: LocationData) {
        service.gps(
            GpsRequest(
                locationData.latitude,
                locationData.longitude
            ),
            token()
        ).await()
    }
}