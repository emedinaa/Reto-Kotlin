package com.emedinaa.contactsapp.data.storage.preferences

import com.emedinaa.contactsapp.model.User



/**
 * Created by emedinaa on 07/04/17.
 */
interface SharedPreferencesHelper {

    fun saveDevice(device: String)
    fun device(): String

    fun saveUser(user: User)
    fun user(): User

    fun clear()
}

