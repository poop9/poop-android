package com.poop9.poop.data.response

data class ReportRankResponse(
    val updatedAt: String,
    val createAt: String,
    val id: Int,
    val userId: Int,
    val nickname: String,
    val uuid: String,
    val socketId: String,
    val count: String,
    val num: Int
)