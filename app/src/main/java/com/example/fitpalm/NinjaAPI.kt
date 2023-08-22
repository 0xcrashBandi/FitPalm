package com.example.fitpalm

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

object ApiHeaders {
    const val API_KEY = "o70plLGqWu9lqaPCZFMQcw==lwtxeT86FR8z3lXE"
}

val client = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", ApiHeaders.API_KEY)
            .build()
        chain.proceed(request)
    }
    .build()


interface NinjaAPI {

    @GET("v1/nutrition?")
    fun getNutrition(@Query("query") foodName: String): Call<List<nutritionData>>
}