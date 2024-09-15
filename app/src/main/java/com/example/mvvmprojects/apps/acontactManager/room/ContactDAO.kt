package com.example.mvvmprojects.apps.acontactManager.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


/**
 * DAO : Data Access Object, defines the methods to interact with DB
 *
 * suspend : is use for creating coroutines.
 *
 * @Insert : annotation is used by Room DB Library to identify that the associated
 * method is used for inserting data into the database.
 *
 * @Update : This annotation is use to identify that the associated method is
 * used for updating data
 *
 * @Delete : This annotation is use to identify that the associated method is
 * used for delete item
 *
 * @Query : Is use for custom Query, we can use placeholders like id to pass
 * parameters to our query
 * **/

@Dao
interface ContactDAO {

    @Insert
    suspend fun insertContact(contact: Contact): Long

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    // Delete all data from the table "contact_table" with a custom Query
    @Query("DELETE FROM contacts_table")
    suspend fun deleteAll()

    // Get all data from the contact_table
    @Query("SELECT * FROM contacts_table")
    fun getAllContactsInDB(): LiveData<List<Contact>>

}

