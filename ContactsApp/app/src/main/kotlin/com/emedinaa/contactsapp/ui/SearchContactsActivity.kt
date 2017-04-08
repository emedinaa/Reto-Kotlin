package com.emedinaa.contactsapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import com.emedinaa.contactsapp.BuildConfig
import com.emedinaa.contactsapp.R
import com.emedinaa.contactsapp.data.model.BaseReponse
import com.emedinaa.contactsapp.data.model.ContactsResponse
import com.emedinaa.contactsapp.data.model.UserQueryResponse
import com.emedinaa.contactsapp.data.storage.rest.ApiClient
import com.emedinaa.contactsapp.model.Contact
import com.emedinaa.contactsapp.model.User
import com.emedinaa.contactsapp.ui.adapters.ContactAdapter
import com.emedinaa.contactsapp.ui.adapters.UserAdapter
import com.emedinaa.contactsapp.ui.dialog.CustomDialogListener
import com.emedinaa.contactsapp.ui.listener.RecyclerClickListener
import com.emedinaa.contactsapp.ui.listener.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search_contacts.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.emedinaa.contactsapp.ui.dialog.TransparentDialogFragment
import com.emedinaa.contactsapp.data.model.NotificationRaw




class SearchContactsActivity : BaseActivity() ,CustomDialogListener{

    private var userAdapter:UserAdapter?=null
    private var invited:User?=null
    private var user:User?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_contacts)
        ui()
        user= sharedPreferences!!.user()
    }

    private fun showInvitationDialog(){

        val bundle:Bundle= Bundle()
        bundle.putSerializable("USER",invited)

        val dialog = TransparentDialogFragment()
        dialog.arguments=bundle

        dialog.show(supportFragmentManager, "TransparentDialogFragment")
    }

    private fun renderUsers(users:List<User>){
        userAdapter= UserAdapter(users,this)
        recyclerViewSearchContacts.setAdapter(userAdapter)

        recyclerViewSearchContacts.addOnItemTouchListener(RecyclerTouchListener(this,recyclerViewSearchContacts,
                object: RecyclerClickListener {
                    override fun onClick(view: View, position: Int) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        if(userAdapter!=null){
                            invited = userAdapter!!.users.get(position)
                            showInvitationDialog()
                        }
                    }

                    override fun onLongClick(view: View, position: Int) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                }))
    }

    private val callback: Callback<UserQueryResponse> = object: Callback<UserQueryResponse> {
        override fun onResponse(call: Call<UserQueryResponse>?, response: Response<UserQueryResponse>?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onResponse $response.body()")

            log({"onResponse $response.body()"})
            renderUsers(response!!.body().data)

        }

        override fun onFailure(call: Call<UserQueryResponse>?, t: Throwable?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onFailure $t")

            log({"onFailure $t"})
        }
    }

    fun searchUser(userName:String){

        //"name LIKE 'J%'";
        //val query:String="name LIKE "+"\'"+userName+"%\'"
        val query:String="name LIKE "+"\'"+userName+"%\'"
        val props:String="objectId,name,email,registrationId,deviceId"
        //val call: Call<BaseReponse> = ApiClient.getMyApiClient().queryUsers(query)
        val call: Call<UserQueryResponse> = ApiClient.getMyApiClient().queryUsers(query,props)
        call.enqueue(callback)
    }


    fun ui(){
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerViewSearchContacts.layoutManager= mLayoutManager

        searchViewContacts.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                if (query.toString().length >=1){
                    searchUser(query.toString())
                    searchViewContacts.clearFocus()
                }else {}
                return true
            }
        })
    }

    private val notificationCallback: Callback<BaseReponse> = object: Callback<BaseReponse> {
        override fun onResponse(call: Call<BaseReponse>?, response: Response<BaseReponse>?) {

            println("onResponse $response.body()")

            log({"notificationCallback onResponse $response.body()"})
        }

        override fun onFailure(call: Call<BaseReponse>?, t: Throwable?) {
            println("onFailure $t")
            log({"notificationCallback onFailure $t"})
        }
    }

    fun sendInvitation(){

        val userRequestName:String= user!!.name!!
        val userRequestObjectId:String= user!!.objectId!!
        val userRequestEmail:String= user!!.email!!
        val registrationId:String= invited!!.registrationId!!
        val deviceId:String= invited!!.deviceId!!

        val message = "Invitación de $userRequestName"
        val title = "Esta es un invitación de $userRequestName"
        val headers:HashMap<String,String> = HashMap()
        headers.put("android-ticker-text", message)
        headers.put("android-content-text",message)
        headers.put("android-content-title",title)
        headers.put("userRequestName",userRequestName)
        headers.put("userRequestId",userRequestObjectId)
        headers.put("userRequestEmail",userRequestEmail)

        val pushSinglecast:Array<String> = arrayOf(deviceId)
        //notificationRaw.setPushSinglecast(arrayOf("0a4d4b1e-a041-4ff9-a536-fd995fc22ac0"))

        val notificationRaw:NotificationRaw= NotificationRaw(message,
                "","","", pushSinglecast,"", headers)

        val call: Call<BaseReponse> = ApiClient.getMyApiClient().sendNotification(notificationRaw)
        call.enqueue(notificationCallback)
    }

    override fun onAction(obj: Any) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDialogPositive(obj: Any) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        sendInvitation()
    }

    override fun onDialogNegative(obj: Any) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


