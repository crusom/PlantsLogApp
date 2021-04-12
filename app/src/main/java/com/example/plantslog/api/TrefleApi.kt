package com.example.plantslog.api

import com.example.plantslog.BuildConfig
import com.example.plantslog.data.PlantData
import retrofit2.http.GET
import retrofit2.http.Query


interface TrefleApi {

    companion object {
        const val BASE_URL = "https://trefle.io/api/v1/"
        const val CLIENT_ID = BuildConfig.TREFLE_ACCESS_KEY
    }

    @GET("plants")
    suspend fun getPlants(
        @Query("token") token: String = CLIENT_ID,
        @Query("page") page: Int
    ): PlantData

    @GET("plants/search")
    suspend fun searchPlants(
        @Query("token") token: String = CLIENT_ID,
        @Query("q") query: String,
        @Query("page") page: Int
    ): PlantData
}
