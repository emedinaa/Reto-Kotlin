package com.emedinaa.contactsapp.presenters

/**
 * Created by emedinaa on 06/04/17.
 */
interface LogInContract {

    interface LogInView{

        fun showLoading()

        fun hideLoading()

        fun validateForm():Boolean
    }
}