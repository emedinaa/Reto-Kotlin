package com.emedinaa.contactsapp.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.emedinaa.contactsapp.BuildConfig
import com.emedinaa.contactsapp.R
import com.emedinaa.contactsapp.data.model.BaseReponse
import com.emedinaa.contactsapp.data.model.ContactsResponse
import com.emedinaa.contactsapp.data.model.UserRaw
import com.emedinaa.contactsapp.data.storage.rest.ApiClient
import com.emedinaa.contactsapp.model.Contact
import com.emedinaa.contactsapp.ui.adapters.ContactAdapter
import com.emedinaa.contactsapp.ui.listener.RecyclerClickListener
import com.emedinaa.contactsapp.ui.listener.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    //private var contacts:List<Contact>?= null
    private var contactAdapter:ContactAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ui()
        //getContacts()
    }

    override fun onResume() {
        super.onResume()
        getContacts()
    }

    fun getContacts(){
        /*
        String cityBloodTypeSearch = "city = "+"\'"+city+"\'"+" AND donor_blood_group = "+"\'"+donorBloodGroup+"\'";
         */
        val userId:String= sharedPreferences!!.user().objectId!!

        //val query:String="userId="+"\'"+"3A098CD0-8744-C0D9-FF26-155F1B723300"+"\'"
        val query:String="userId="+"\'"+userId+"\'"
        val call:Call<ContactsResponse> = ApiClient.getMyApiClient().contacts(query)
        call.enqueue(callback)
    }

    fun gotoContactDetail(contact:Contact){
        val bundle:Bundle = Bundle()
        bundle.putSerializable("CONTACT",contact)

        val intent= Intent(this,ContactDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun gotoSearch(){
        val intent= Intent(this,SearchContactsActivity::class.java)
        startActivity(intent)
    }

    fun ui()
    {
        val mLayoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerViewContacts.layoutManager= mLayoutManager

        recyclerViewContacts.addOnItemTouchListener(RecyclerTouchListener(this,recyclerViewContacts,
                object:RecyclerClickListener{
                    override fun onClick(view: View, position: Int) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        if(contactAdapter!=null){
                            val contact:Contact= contactAdapter!!.contacts.get(position)
                            gotoContactDetail(contact)
                        }
                    }

                    override fun onLongClick(view: View, position: Int) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                }))
        /*
        rvPlace.addOnItemTouchListener(new RecyclerTouchListener(this, rvPlace, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(places!=null) {
                    Place place= places.get(position);
                    placePresenter.selectedPlace(place);
                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }
}));
         */

        floatingABSearch.setOnClickListener { gotoSearch() }
    }

    fun renderContacts(mContacts:List<Contact>){
        contactAdapter= ContactAdapter(mContacts,this)
        recyclerViewContacts.setAdapter(contactAdapter)
    }

    private val callback: Callback<ContactsResponse> = object: Callback<ContactsResponse> {
        override fun onResponse(call: Call<ContactsResponse>?, response: Response<ContactsResponse>?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onResponse $response.body()")

            log({"onResponse $response.body()"})
            renderContacts(response!!.body().data)
        }

        override fun onFailure(call: Call<ContactsResponse>?, t: Throwable?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            println("onFailure $t")

            log({"onFailure $t"})
        }
    }

}
