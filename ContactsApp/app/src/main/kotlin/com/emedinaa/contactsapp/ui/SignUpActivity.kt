package com.emedinaa.contactsapp.ui

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.emedinaa.contactsapp.BuildConfig
import com.emedinaa.contactsapp.R
import com.emedinaa.contactsapp.data.model.*
import com.emedinaa.contactsapp.data.storage.rest.ApiClient
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.UUID.randomUUID



class SignUpActivity : BaseActivity() {

    private var email:String=""
    private var password:String=""
    private var fullName:String=""
    private var registrationId:String=""
    private var deviceId:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        ui()
    }

    private fun registerDevice() {
        val token:String? = FirebaseInstanceId.getInstance().token
        deviceId= buildDeviceID()
        val versionSDK:String = Build.VERSION.SDK_INT.toString()

        val deviceRaw:DeviceRaw= DeviceRaw(token, deviceId,"ANDROID",versionSDK)
        val call:Call<DeviceFCmResponse> = ApiClient.getMyApiClient().registerDevice(deviceRaw)
        call.enqueue(callbackFCM)
    }

    fun signUp(){
        fullName= editTextFullName.text.toString()
        email= editTextEmail.text.toString()
        password= editTextPassword.text.toString()

        val userRaw:UserRaw= UserRaw(email,password,fullName,registrationId,deviceId)
        val call:Call<BaseReponse> = ApiClient.getMyApiClient().register(userRaw)
        call.enqueue(callback)

    }

    private fun buildDeviceID(): String {
        return UUID.randomUUID().toString()
    }

    private fun gotoLogIn(){
        finish()
    }

    private val callback: Callback<BaseReponse> = object: Callback<BaseReponse> {
        override fun onResponse(call: Call<BaseReponse>?, response: Response<BaseReponse>?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onResponse $response")
            log({"onResponse $response"})

            if(response!!.isSuccessful){
                toast("Se registro satisfactoriamente $response")
                gotoLogIn()
            }else{
                toast("Ocurrió un error registrando este usuario $response" )
            }

        }

        override fun onFailure(call: Call<BaseReponse>?, t: Throwable?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onFailure $t")

            log({"onFailure $t"})

            toast("Ocurrió un error registrando este dispositivo $t")
        }
    }


    private val callbackFCM: Callback<DeviceFCmResponse> = object: Callback<DeviceFCmResponse> {
        override fun onResponse(call: Call<DeviceFCmResponse>?, response: Response<DeviceFCmResponse>?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            log({"fcm onResponse $response.body()"})

            //val l = if (b != null) b.length else -1
            if(response!=null){
                if(response.isSuccessful){
                    registrationId= response.body().registrationId
                    signUp()
                }
            }

        }

        override fun onFailure(call: Call<DeviceFCmResponse>?, t: Throwable?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("fcm onFailure $t")

            log({"fcm onFailure $t"})

            toast("Ocurrió un error registrando este usuario $t")
        }
    }

    fun ui(){
        buttonSignUp.setOnClickListener {
            registerDevice()
            //signUp()
        }
    }

}
