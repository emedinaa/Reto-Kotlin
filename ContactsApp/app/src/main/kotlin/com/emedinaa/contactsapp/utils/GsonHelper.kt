package com.emedinaa.contactsapp.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject



/**
 * Created by emedinaa on 07/04/17.
 */
class GsonHelper {

    fun objectToJSON(obj: Any): JSONObject? {
        val gsonb:GsonBuilder = GsonBuilder()
        val gson:Gson = gsonb.create()
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(gson.toJson(obj))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return jsonObject
    }

    fun <T> jsonToObject(json: String, cls: Class<T>): T {

        val gsonb:GsonBuilder = GsonBuilder()
        val gson:Gson = gsonb.create()
        return gson.fromJson(json, cls)
    }
}