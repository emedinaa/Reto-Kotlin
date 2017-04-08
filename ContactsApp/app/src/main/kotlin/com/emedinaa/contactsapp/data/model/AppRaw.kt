package com.emedinaa.contactsapp.data.model

/**
 * Created by emedinaa on 07/04/17.
 */

data class LogInRaw(val login:String?, val password:String?)

data class ContactRaw(
        val userId:String,
        val contactId:String,
        val contactEmail:String,
        val contactFullName:String,
        val profession:String?)
