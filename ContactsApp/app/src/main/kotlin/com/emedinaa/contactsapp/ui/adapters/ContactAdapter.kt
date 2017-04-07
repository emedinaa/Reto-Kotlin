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
import kotlinx.android.synthetic.main.row_contact.view.*

/**
 * Created by emedinaa on 06/04/17.
 */
class ContactAdapter (val contacts:List<Contact>,val context:Context): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.textViewName
        val textViewProfession: TextView = view.textViewName
        val imageViewContact: ImageView = view.imageViewContact
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.row_contact, parent, false)) //To change body of created functions use File | Settings | File Templates.
    }

    /*
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val name:String = pokemonList[position].name
        holder!!.tviName.text = name //To change body of created functions use File | Settings | File Templates.
    }*/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name: String = contacts[position].name
        val profession: String = contacts[position].profession

        holder.textViewName.text = name //To change body of created functions use File | Settings | File Templates.
        holder.textViewProfession.text = profession
        //holder.iviPhoto.setImageBitmap(getBitmapFromAssets(photo))
    }

    override fun getItemCount(): Int {
        return contacts.size //To change body of created functions use File | Settings | File Templates.
    }

}