package com.example.mvvmprojects.apps.acontactManager.viewmodel

import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmprojects.apps.acontactManager.repository.ContactRepository
import com.example.mvvmprojects.apps.acontactManager.room.Contact
import kotlinx.coroutines.launch

// View Modle : store and manage UI-related Data seperating the UI-related login from UI Controller(Activity/Fragment)

class ContactViewModle(private val repository: ContactRepository)
    : ViewModel(), Observable {

    val contacts = repository.contacts
    private var isUpdateOrDelete = false
    private lateinit var contactToUpdateOrDelete: Contact

    // Data Binding with Live Data
    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun insert(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)
    }

    fun delete(contact: Contact) = viewModelScope.launch {
        repository.delete(contact)

        // Resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun update(contact: Contact) = viewModelScope.launch {
        repository.update(contact)

        // Resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun saveOrUpdate(view: View) {
        if (isUpdateOrDelete) {
            // Make and update
            contactToUpdateOrDelete.name = inputName.value!!
            contactToUpdateOrDelete.email = inputEmail.value!!
            update(contactToUpdateOrDelete)
        }else {
            // Inserting a new contact
            val name = inputName.value!!
            val email = inputEmail.value!!

            insert(Contact(0,name,email))

            // Reset the name and email
            inputName.value = null
            inputEmail.value = null
        }
    }

    fun clearAllorDelete(){
        if(isUpdateOrDelete) {
            delete(contactToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    fun initUpdateAndDelete(contact: Contact){
        inputName.value = contact.name
        inputEmail.value = contact.email

        isUpdateOrDelete = true
        contactToUpdateOrDelete = contact
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}