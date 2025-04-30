package com.example.hola.dataclassResponse

data class LoginResponse(
    val message: String,
    val token: String,
    val is_completed: Boolean
)
