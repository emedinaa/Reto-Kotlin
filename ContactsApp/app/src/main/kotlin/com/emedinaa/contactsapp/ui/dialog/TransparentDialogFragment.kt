package com.emedinaa.contactsapp.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.TextView
import com.emedinaa.contactsapp.R
import com.emedinaa.contactsapp.model.User

/**
 * Created by eduardomedina on 24/01/17.
 */
class TransparentDialogFragment : DialogFragment() {
    private var mListener: CustomDialogListener? = null
    private var tviCancel: View? = null
    private var tviOk: View? = null
    private var textViewUser:TextView?=null

    private var user:User?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user= arguments.getSerializable("USER") as User
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is CustomDialogListener) {
            mListener = context as CustomDialogListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement CustomDialogListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        // Get the layout inflater
        val inflater = activity.layoutInflater

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        val customView = inflater.inflate(R.layout.layout_dialog, null)
        tviCancel = customView.findViewById(R.id.tviCancel)
        tviOk = customView.findViewById(R.id.tviOk)
        textViewUser = customView.findViewById(R.id.textViewUser) as TextView
        builder.setView(customView)
        textViewUser!!.text= user!!.name

        // Add action buttons
        tviCancel!!.setOnClickListener {
            dismiss()

            if (mListener != null) {
                val message:String = ""
                mListener!!.onDialogNegative(message)
            }
        }

        tviOk!!.setOnClickListener {
            // sign in the user ...
            if (mListener != null) {

                dismiss()
                val message =""
                Log.v(TAG, message)

                mListener!!.onDialogPositive(message)

            }
        }

        return builder.create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
    }

    companion object {

        private val TAG = "CustomDialogF"
    }
}// Required empty public constructor
