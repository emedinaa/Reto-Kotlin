package com.emedinaa.retokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ui()
    }

    fun ui(){
        buttonShow.setOnClickListener {
            val text= editTextName.text
            Toast.makeText(this,text,Toast.LENGTH_LONG).show()
        }
    }
}
