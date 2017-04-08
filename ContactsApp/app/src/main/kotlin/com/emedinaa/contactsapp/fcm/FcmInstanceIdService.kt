package com.emedinaa.contactsapp.fcm

import android.util.Log
import com.emedinaa.contactsapp.BuildConfig
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by eduardomedina on 28/02/17.
 */
class FcmInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()

        val token:String? = FirebaseInstanceId.getInstance().token
        sendRegisterToken(token)
    }

    private fun sendRegisterToken(token: String?) {

    }

    companion object {
        private val TAG = "FcmInstanceIdService"
    }

}
