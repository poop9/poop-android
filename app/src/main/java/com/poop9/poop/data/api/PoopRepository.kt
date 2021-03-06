package com.poop9.poop.data.api

import com.poop9.poop.data.response.ReportCountResponse
import com.poop9.poop.data.response.ReportRankResponse
import com.poop9.poop.data.response.TokenResponse
import com.poop9.poop.model.LocationData

interface PoopRepository {
    suspend fun getToken(): String
    suspend fun signUp(nickname: String): TokenResponse
    suspend fun signIn(): TokenResponse
    suspend fun today(): ReportCountResponse
    suspend fun week(): ReportCountResponse
    suspend fun month(): ReportCountResponse
    suspend fun list(): List<ReportRankResponse>

    suspend fun gps(locationData: LocationData)
    fun addPoopCount()
}