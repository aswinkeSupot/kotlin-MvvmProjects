package com.example.mvvmprojects.apps.acontactManager.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ROOM DB Details
 * Entity (TableName) : contacts_table
 * 1. contact_id
 * 2. contact_name
 * 3. contact_email
 * **/

// Each instance of this class represents a row in the table
@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contact_id")
    val id: Int,
    @ColumnInfo(name = "contact_name")
    var name: String,
    @ColumnInfo(name = "contact_email")
    var email: String
)
