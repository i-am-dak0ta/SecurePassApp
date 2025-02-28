package com.dak0ta.sp.data.api

import retrofit2.http.GET

interface NetworkApi {

    @GET("/v1/auth")
    suspend fun authorize()
}
