package com.emedinaa.contactsapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.emedinaa.contactsapp.R
import com.emedinaa.contactsapp.data.model.BaseReponse
import com.emedinaa.contactsapp.data.model.ContactRaw
import com.emedinaa.contactsapp.data.storage.rest.ApiClient
import com.emedinaa.contactsapp.model.User
import kotlinx.android.synthetic.main.activity_invitation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvitationActivity : BaseActivity() {

    private var user:User?=null
    private var invited:User?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invitation)
        user= sharedPreferences!!.user()
        extras()
        ui()
        populateInvitationInfo()
    }

    private fun extras(){
        invited= intent.extras.getSerializable("INVITED") as User
    }

    private fun populateInvitationInfo(){

        textViewUserName.text= invited!!.name + "?"
    }

    private  fun exit(){
        finish()
    }

    private val callbackMyContact: Callback<BaseReponse> = object: Callback<BaseReponse> {
        override fun onResponse(call: Call<BaseReponse>?, response: Response<BaseReponse>?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onResponse $response")
            log({"onResponse $response"})

            if(response!!.isSuccessful){
                //toast("Se registro el contacto satisfactoriamente $response")
                registerContact()
            }else{
                toast("Ocurrió un error registrando esta invitación $response" )
            }

        }

        override fun onFailure(call: Call<BaseReponse>?, t: Throwable?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onFailure $t")
            log({"onFailure $t"})

            toast("Ocurrió un error registrando este dispositivo $t")
        }
    }

    private val callback: Callback<BaseReponse> = object: Callback<BaseReponse> {
        override fun onResponse(call: Call<BaseReponse>?, response: Response<BaseReponse>?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onResponse $response")
            log({"onResponse $response"})

            if(response!!.isSuccessful){
                toast("Se registro el contacto satisfactoriamente $response")
                exit()
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

    private fun registerContact(){
        val userId:String= invited!!.objectId!!

        val contactId:String= user!!.objectId!!
        val email:String= user!!.email!!
        val fullName:String= user!!.name!!
        val profession:String?= user!!.profession

        val contact:ContactRaw = ContactRaw(userId,contactId,email,fullName,profession)

        val call:Call<BaseReponse> = ApiClient.getMyApiClient().registerContact(contact)
        call.enqueue(callback)

    }

    private fun registerMyContact(){

        val userId:String= user!!.objectId!!

        val contactId:String= invited!!.objectId!!
        val email:String= invited!!.email!!
        val fullName:String= invited!!.name!!
        var profession:String= ""
        if(invited!!.profession!=null){
            profession= invited!!.profession!!
        }
        val contact:ContactRaw = ContactRaw(userId,contactId,email,fullName,profession)

        val call:Call<BaseReponse> = ApiClient.getMyApiClient().registerContact(contact)
        call.enqueue(callbackMyContact)
    }

    private fun acceptContact(){
        //registerContact()
        registerMyContact()
    }

    private fun ui(){
        buttonCancelar.setOnClickListener { exit() }
        buttonAceptar.setOnClickListener { acceptContact() }
    }
}
