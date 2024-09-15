package com.example.mvvmprojects.apps.acontactManager.repository

import com.example.mvvmprojects.apps.acontactManager.room.Contact
import com.example.mvvmprojects.apps.acontactManager.room.ContactDAO

// Repository : act as a bridge between the ViewModel and the Data Source.
class ContactRepository(private val contactDAO: ContactDAO) {

    val contacts = contactDAO.getAllContactsInDB()

    suspend fun insert(contact: Contact): Long {
        return contactDAO.insertContact(contact)
    }

    suspend fun update(contact: Contact){
        return contactDAO.updateContact(contact)
    }

    suspend fun delete(contact: Contact){
        return contactDAO.deleteContact(contact)
    }

    suspend fun deleteAll(){
        return contactDAO.deleteAll()
    }
}