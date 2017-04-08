package com.emedinaa.contactsapp.data.storage.rest

import com.emedinaa.contactsapp.data.model.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.emedinaa.contactsapp.data.model.NotificationRaw
import retrofit2.http.POST
import retrofit2.http.GET
import com.emedinaa.contactsapp.data.model.DeviceRaw







/**
 * Created by emedinaa on 07/04/17.
 */
class ApiClient {


    companion object {
        private var servicesApiInterface: ServicesApiInterface? = null
        private val API_BASE_URL = "http://api.backendless.com"

        private fun interceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return httpLoggingInterceptor
        }

        private fun requestInterceptor(): Interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain?): Response {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                val original: Request = chain!!.request()

                val request: Request = original.newBuilder().addHeader("Content-Type", "application/json")
                        .addHeader("application-id", "1B6F1268-7CB4-08F8-FFCF-02F87DD13300")
                        .addHeader("secret-key", "6E3ACAEB-54FD-81CE-FF26-A586656F3200")
                        .addHeader("application-type", "REST")
                        .method(original.method(), original.body())
                        .build()

                return chain.proceed(request)
            }
        }

            fun getMyApiClient(): ServicesApiInterface {
                var builder: Retrofit.Builder = Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
                httpClient.addInterceptor(interceptor())
                httpClient.addInterceptor(requestInterceptor())

                var retrofit: Retrofit = builder.client(httpClient.build()).build()
                servicesApiInterface = retrofit.create(
                        ServicesApiInterface::class.java)

                return servicesApiInterface as ServicesApiInterface
            }
    }

    interface ServicesApiInterface{
        /*@Headers(*arrayOf("Content-Type: application/json",
                "application-id: 1B6F1268-7CB4-08F8-FFCF-02F87DD13300",
                "secret-key: 6E3ACAEB-54FD-81CE-FF26-A586656F3200",
                "application-type: REST"))*/

        @POST("/v1/users/login")
        fun logIn(@Body raw:LogInRaw):Call<LogInResponse>

        ///<version name>/users/register
        @POST("/v1/users/register")
        fun register(@Body raw:UserRaw):Call<BaseReponse>

        ///<version name>/users/register
        @POST("/v1/data/Contacts")
        fun registerContact(@Body raw:ContactRaw):Call<BaseReponse>
        //https://api.backendless.com/v1/data/Person?where=age%3D20

        @GET("/v1/data/Contacts")
        fun contacts(@Query("where") query:String):Call<ContactsResponse>

        @GET("/v1/data/Contacts/{objectId}")
        fun contact(@Path("objectId") objectId:String):Call<BaseReponse>

        @GET("/v1/users/{objectId}")
        fun userInfo(@Path("objectId") objectId:String):Call<UserResponse>

        @GET("/v1/data/Users")
        fun queryUsers(@Query("where") query:String):Call<BaseReponse>

        @GET("/v1/data/Users")
        fun queryUsers(@Query("where") query:String,@Query("props") props:String):Call<UserQueryResponse>

        //notifications
        @POST("/v1/messaging/Default")
        fun sendNotification(@Body notificationRaw: NotificationRaw): Call<BaseReponse>

        @GET("/v1/messaging/registrations")
        fun devices(): Call<BaseReponse>

        @POST("/v1/messaging/registrations")
        fun registerDevice(@Body deviceRaw: DeviceRaw): Call<DeviceFCmResponse>

    }


}