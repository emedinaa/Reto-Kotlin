package com.emedinaa.contactsapp.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.emedinaa.contactsapp.BuildConfig
import com.emedinaa.contactsapp.R
import com.emedinaa.contactsapp.model.Invitation
import com.emedinaa.contactsapp.model.User
import com.emedinaa.contactsapp.ui.InvitationActivity

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by eduardomedina on 28/02/17.
 */
class FcmService : FirebaseMessagingService() {

    private var invited:User?=null

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        //buildNotification(remoteMessage.getNotification().getBody());
        var message:String?=null
        var title:String?= "Nueva notificacion"

        if (remoteMessage!!.data != null) {
            message = remoteMessage!!.data["message"]
            title = remoteMessage!!.data["android-content-title"]
        }
        val obj:Any=remoteMessage.data;

        log({"remoteMessage $remoteMessage"})
        log({"obj $obj"})
        log({"message $message"})
        log({"title $title"})

        val userRequestName:String?= remoteMessage!!.data["userRequestName"]
        val userRequestId:String?= remoteMessage!!.data["userRequestId"]
        val userRequestEmail:String?= remoteMessage!!.data["userRequestEmail"]
        val userRequestProfession:String?= remoteMessage!!.data["userRequestProfession"]

        invited= User(userRequestId,userRequestName,userRequestProfession,userRequestEmail,"","")

        buildNotification(title, message)
        buildBroadCast(message)
    }

    private fun buildBroadCast(message:String?) {

        val broadCastIntent = Intent("update-message")
        broadCastIntent.putExtra("message", message)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadCastIntent)
    }

    private fun buildNotification(title:String?,messageBody:String?) {
        //val bundle:Bundle= Bundle()
        //bundle.putString("MESSAGE",messageBody)
        //bundle.putSerializable("INVITED",invited)

        val intent = Intent(this, InvitationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("INVITED",invited)

        val resultIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val mNotificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, mNotificationBuilder.build())
    }


    fun log(lambda: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.d("TAG", lambda())
        }
    }

    companion object {
        private val TAG = "FcmService"
    }
}
