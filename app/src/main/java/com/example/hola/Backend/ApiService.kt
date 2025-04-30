package com.example.hola.Backend


import com.example.hola.dataclassResponse.LoginResponse
import com.example.hola.dataclassResponse.LogoutResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//    @POST("api/user/login/")
//    suspend fun login(@Body LoginRequest: LoginRequest) : Response<LoginResponse>
//
//    @POST("api/user/forgot-password/")
//    suspend fun forgetPassword(
//        @Body request: ForgotPasswordRequest
//    ): Response<ForgotPasswordResponse>
//
//    @POST("api/user/reset-password/")
//    suspend fun resetPassword(
//        @Body request: resetPasswordRequest
//    ): Response<ForgotPasswordResponse>


    @POST("api/user/logout/")
    suspend fun logout(@Header("Authorization") token: String) : Response<LogoutResponse>



}