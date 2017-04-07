package com.emedinaa.contactsapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.emedinaa.contactsapp.R
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    private var name:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        ui()
    }

    fun ui(){
        buttonLogIn.setOnClickListener {
            name= editTextEmail.text.toString().trim()
        }
    }
}
