package com.emedinaa.contactsapp.data.model

import com.emedinaa.contactsapp.model.Contact
import com.emedinaa.contactsapp.model.User

/**
 * Created by emedinaa on 07/04/17.
 */

data class DeviceFCmResponse(val registrationId:String)

data class SigUpResponse(val name:String,
                         val email:String, val objectId:String)

data class ContactsResponse(
        val offset:Int,val data:List<Contact>)

data class LogInResponse(val name:String ,
                         val email:String,
                         val objectId:String,
                         val profession:String,
                         val registrationId:String,
                         val deviceId:String)

data class UserQueryResponse(val offset:Int,
                             val data:List<User>)

data class UserResponse(val name:String ,
                         val email:String,
                         val objectId:String,
                         val profession:String,
                         val registrationId:String,
                         val deviceId:String)