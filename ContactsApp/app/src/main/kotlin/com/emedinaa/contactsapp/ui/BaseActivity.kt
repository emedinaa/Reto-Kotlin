package com.emedinaa.contactsapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.emedinaa.contactsapp.BuildConfig
import com.emedinaa.contactsapp.data.storage.preferences.DefaultSharedPreferencesHelper
import com.emedinaa.contactsapp.data.storage.preferences.SharedPreferencesHelper
import com.emedinaa.contactsapp.utils.GsonHelper

/**
 * Created by emedinaa on 07/04/17.
 */
open class BaseActivity: AppCompatActivity() {

    protected var sharedPreferences:SharedPreferencesHelper?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appSharedPreferences:SharedPreferences= getSharedPreferences("com.emedinaa.contactapp", Context.MODE_PRIVATE)
        sharedPreferences= DefaultSharedPreferencesHelper(GsonHelper(),appSharedPreferences)
    }

    fun log(lambda: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.d("TAG", lambda())
        }
    }

    fun nextActivity(intent:Intent, destroy:Boolean=false){
        startActivity(intent)
        if(destroy) finish()
    }

    fun toast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}