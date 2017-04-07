package com.emedinaa.contactsapp.data.storage.rest

import com.emedinaa.contactsapp.data.model.LogInRaw
import com.emedinaa.contactsapp.data.model.LogInResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by emedinaa on 07/04/17.
 */
class ApiClient {
    companion object{
        private var servicesApiInterface:ServicesApiInterface?=null

        fun getMyApiClient():ServicesApiInterface{
            var builder:Retrofit.Builder= Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
            var httpClient:OkHttpClient.Builder = OkHttpClient.Builder()

            var retrofit:Retrofit=builder.client(httpClient.build()).build()
            servicesApiInterface= retrofit.create(
                    ServicesApiInterface::class.java)

            return servicesApiInterface as ServicesApiInterface
        }
    }

    interface ServicesApiInterface{
        @Headers("Content-Type: application/json")
        @POST("/api/login")
        fun logIn(@Body raw:LogInRaw):Call<LogInResponse>
    }
}