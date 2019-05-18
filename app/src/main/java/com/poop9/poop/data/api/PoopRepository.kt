package com.poop9.poop.data.api

import com.poop9.poop.data.response.ReportCountResponse
import com.poop9.poop.data.response.ReportRankResponse
import com.poop9.poop.data.response.SignInResponse

interface PoopRepository {
    suspend fun signUp(nickname: String): SignInResponse
    suspend fun signIn(): SignInResponse
    suspend fun today(): ReportCountResponse
    suspend fun week(): ReportCountResponse
    suspend fun month(): ReportCountResponse
    suspend fun list(): List<ReportRankResponse>
}