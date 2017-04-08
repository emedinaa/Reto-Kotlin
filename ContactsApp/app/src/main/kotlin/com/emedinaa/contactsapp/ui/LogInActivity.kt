package com.emedinaa.contactsapp.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.emedinaa.contactsapp.BuildConfig
import com.emedinaa.contactsapp.R
import com.emedinaa.contactsapp.data.model.LogInRaw
import com.emedinaa.contactsapp.data.model.LogInResponse
import com.emedinaa.contactsapp.data.storage.rest.ApiClient
import com.emedinaa.contactsapp.model.User
import kotlinx.android.synthetic.main.activity_log_in.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : BaseActivity() {

    private var name:String=""
    private var password:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        ui()
    }

    fun ui(){
        buttonLogIn.setOnClickListener {
            name= editTextEmail.text.toString().trim()
            password= editTextPassword.text.toString().trim()
            logIn()
        }
        textViewSignUp.setOnClickListener {
            signUp()
        }
    }

    fun signUp(){
        val intent= Intent(this,SignUpActivity::class.java)
        startActivity(intent)
    }

    fun logIn(){
        //val loginRaw:LogInRaw= LogInRaw("abc@gmail.com","123456")
        val loginRaw:LogInRaw= LogInRaw(name,password)
        val call:Call<LogInResponse> = ApiClient.getMyApiClient().logIn(loginRaw)
        call.enqueue(callback)
    }

    fun gotoMain(){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    private fun saveSession(user:User) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        sharedPreferences!!.saveUser(user)
    }


    private val callback:Callback<LogInResponse> = object:Callback<LogInResponse>{
        override fun onResponse(call: Call<LogInResponse>?, response: Response<LogInResponse>?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onResponse $response")
            log({"onResponse $response"})

            if(response!!.isSuccessful){
                val loginResponse:LogInResponse= response!!.body()
                val user:User= User(loginResponse.objectId,
                        loginResponse.name,loginResponse.profession,loginResponse.email,
                        loginResponse.registrationId,loginResponse.deviceId)

                saveSession(user)
                gotoMain()
            }else{
                if(response.errorBody()!=null){
                    val responseBody:ResponseBody= response!!.errorBody()
                    val jsonObject:JSONObject= JSONObject(String(responseBody.bytes()))

                    toast("Ocurrió un error $jsonObject")
                }else{
                    toast("Ocurrió un error $response")
                }
            }
        }

        override fun onFailure(call: Call<LogInResponse>?, t: Throwable?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onFailure $t")
            log({"onFailure $t"})

            toast("Ocurrió un error $t")
        }
    }


}
