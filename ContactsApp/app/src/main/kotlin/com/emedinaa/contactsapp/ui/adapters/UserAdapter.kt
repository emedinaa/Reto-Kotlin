package com.emedinaa.contactsapp.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.emedinaa.contactsapp.R
import com.emedinaa.contactsapp.model.Contact
import com.emedinaa.contactsapp.model.User
import kotlinx.android.synthetic.main.row_contact.view.*

/**
 * Created by emedinaa on 06/04/17.
 */
class UserAdapter (val users:List<User>,val context:Context): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.textViewName
        val textViewProfession: TextView = view.textViewProfession
        val imageViewContact: ImageView = view.imageViewContact
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.row_user, parent, false)) //To change body of created functions use File | Settings | File Templates.
    }

    /*
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val name:String = pokemonList[position].name
        holder!!.tviName.text = name //To change body of created functions use File | Settings | File Templates.
    }*/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val contactFullName: String = users[position].name!!
        val profession: String? = users[position].profession

        holder.textViewName.text = contactFullName //To change body of created functions use File | Settings | File Templates.
        holder.textViewProfession.text = profession
        //holder.iviPhoto.setImageBitmap(getBitmapFromAssets(photo))
    }

    override fun getItemCount(): Int {
        return users.size //To change body of created functions use File | Settings | File Templates.
    }

}