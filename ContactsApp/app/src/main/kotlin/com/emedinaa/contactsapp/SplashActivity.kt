package com.emedinaa.contactsapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.emedinaa.contactsapp.ui.LogInActivity
import com.emedinaa.contactsapp.ui.MainActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    private val TIME:Long=1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ui()
        Timer().schedule(timerTask { gotoLogIn() },TIME)
    }

    fun ui(){
    }

    fun gotoLogIn(){
        val intent= Intent(this,LogInActivity::class.java)
        startActivity(intent)
    }
}
