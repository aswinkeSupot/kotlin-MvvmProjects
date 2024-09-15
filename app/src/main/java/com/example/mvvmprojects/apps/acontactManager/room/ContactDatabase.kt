package com.example.mvvmprojects.apps.acontactManager.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @Database - is use to declare a class as a room database and configure its properties.
 * **/

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract val contactDAO: ContactDAO

    /**
     * Singleton Design Pattern:
     * Only one instance of the database exists, avoiding unnecessary overhead
     * associated with repeated database creation
     *
     * companion object : define a static singleton instance of this DB Class
     *
     * @Volatile : prevents any possible race conditions in multithreading.
     * **/

    companion object{
        @Volatile
        private var INSTANCE: ContactDatabase?= null

        fun getInstance(context: Context): ContactDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null) {

                    // Creating the database object
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contacts_db"
                    ).build()

                }

                INSTANCE = instance
                return instance
            }
        }
    }
}