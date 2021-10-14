package com.example.headup2

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/celebrities/")
    fun getCelebrityDetails(): Call<List<CelData.Datum>>

    @Headers("Content-Type: application/json")
    @POST("/celebrities/")
    fun addCelebrityDetails(@Body userdata: CelData.Datum)
            : Call<CelData.Datum>

    @Headers("Content-Type: application/json")
    @PUT("/celebrities/{id}")
    fun upDateCelebrityDetails(
        @Path("id") id: Int,
        @Body userdata: CelData.Datum,
    ): Call<CelData.Datum>

    @Headers("Content-Type: application/json")
    @DELETE("/celebrities/{id}")
    fun delCelebrityDetails(@Path("id") id: Int)
            : Call<Void>

}