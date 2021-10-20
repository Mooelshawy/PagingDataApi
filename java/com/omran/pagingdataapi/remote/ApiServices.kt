package com.omran.pagingdataapi.remote

import com.omran.pagingdataapi.model.PassengerRespond
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
private const val BASE_URL = "https://api.instantwebtools.net/v1/"
private val client: OkHttpClient = OkHttpClient.Builder().build()
interface ApiServices {


    @GET("passenger")
    suspend fun getPassengersData(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): Response<PassengerRespond>


}

class Builder {

    companion object {
        fun invoke() :Retrofit{
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}