package com.emedinaa.contactsapp.data.storage.preferences

import android.R.id.edit
import android.content.SharedPreferences
import com.emedinaa.contactsapp.model.User
import com.emedinaa.contactsapp.utils.GsonHelper


/**
 * Created by emedinaa on 07/04/17.
 */

class DefaultSharedPreferencesHelper(val gsonHelper: GsonHelper, val sharedPreferences: SharedPreferences) : SharedPreferencesHelper {

    private val KEY_USER ="contactapp.session.user"
    private val KEY_DEVICE ="contactapp.session.device"

    override fun saveDevice(device: String) {
        val editor = editor()
        editor.putString(KEY_DEVICE, device)
        editor.apply()
    }

    override fun device(): String {
        val device = sharedPreferences.getString(KEY_DEVICE, "")
        return device
    }

    override fun saveUser(user: User) {
        val editor = editor()
        editor.putString(KEY_USER, gsonHelper.objectToJSON(user).toString())
        editor.apply()
    }

    override fun user(): User {
        val userStr = sharedPreferences.getString(KEY_USER, "")
        val user = gsonHelper.jsonToObject(userStr, User::class.java)
        println("sp user " + user)
        return user
    }

    override fun clear() {
        val editor = editor()
        editor.clear()
        editor.apply()
    }

    private fun editor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }
}