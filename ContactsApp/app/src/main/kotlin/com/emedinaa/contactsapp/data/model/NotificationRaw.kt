package com.emedinaa.contactsapp.data.model

/**
 * Created by emedinaa on 07/04/17.
 */
class NotificationRaw(val message:String,
           val publisherId:String,
           val pushPolicy:String, val pushBroadcast:String,
           val pushSinglecast:Array<String>,
           val publishAt:String,
           var headers:HashMap<String,String>){


    fun builderHeader(mTicker:String,mMessage:String,mTitle:String){
        this.headers= HashMap()
        this.headers.put("android-ticker-text", mTicker)
        this.headers.put("android-content-text",mMessage);
        this.headers.put("android-content-title",mTitle);
    }
}