package com.emedinaa.contactsapp.model

import java.io.Serializable

/**
 * Created by emedinaa on 06/04/17.
 */
data class User(val objectId:String?,val name:String?, val profession:String?="",val email:String?,
                val registrationId:String?,val deviceId:String?):Serializable