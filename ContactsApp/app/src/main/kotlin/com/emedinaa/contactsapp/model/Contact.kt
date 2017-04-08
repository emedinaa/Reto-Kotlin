package com.emedinaa.contactsapp.model

import java.io.Serializable

/**
 * Created by emedinaa on 06/04/17.
 */
data class Contact(val objectId:String,
       val userId:String,val contactId:String,
       val contactFullName:String, val contactEmail:String,
       val name:String="",val profession:String=""):Serializable