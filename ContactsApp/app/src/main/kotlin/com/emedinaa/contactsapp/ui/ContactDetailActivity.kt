package com.emedinaa.contactsapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.emedinaa.contactsapp.BuildConfig
import com.emedinaa.contactsapp.R
import com.emedinaa.contactsapp.data.model.BaseReponse
import com.emedinaa.contactsapp.data.model.ContactsResponse
import com.emedinaa.contactsapp.data.model.UserResponse
import com.emedinaa.contactsapp.data.storage.rest.ApiClient
import com.emedinaa.contactsapp.model.Contact
import com.emedinaa.contactsapp.model.User
import kotlinx.android.synthetic.main.activity_contact_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactDetailActivity : BaseActivity() {

    private var contact:Contact?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        extras()

        getContactInfo()
    }

    fun extras(){
        contact= intent.extras.getSerializable("CONTACT") as Contact
    }

    fun getContactInfo(){
        if(contact!=null){
            val call:Call<UserResponse> = ApiClient.getMyApiClient().userInfo(contact!!.contactId)
            call.enqueue(callback)
        }
    }
    fun populate(user:User){
        textViewUserName.text= user.name
        textViewEmail.text= user.email
        textViewPhone.text= ""
        textViewProfession.text= user.profession
    }

    private val callback: Callback<UserResponse> = object: Callback<UserResponse> {
        override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onResponse $response.body()")
            log({"onResponse $response.body()"})
            if(response!!.isSuccessful){
                val userResponse= response.body()
                val user:User= User(userResponse.objectId,
                        userResponse.name, userResponse.profession,
                        userResponse.email,"","")
                populate(user)
            }else{

            }
            //renderContacts(response!!.body().data)
        }

        override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onFailure $t")

            log({"onFailure $t"})
        }
    }

}
