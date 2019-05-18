package com.poop9.poop.data.response

import java.util.*

data class SignInResponse(
    val updatedAt: Date,
    val createdAt: Date,
    val id: Int,
    val nickname: String,
    val uuid: String
)