package com.example.mvvmprojects.apps.acontactManager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmprojects.R
import com.example.mvvmprojects.apps.acontactManager.repository.ContactRepository
import com.example.mvvmprojects.apps.acontactManager.room.Contact
import com.example.mvvmprojects.apps.acontactManager.room.ContactDatabase
import com.example.mvvmprojects.apps.acontactManager.view.ContactRecyclerAdapter
import com.example.mvvmprojects.apps.acontactManager.viewmodel.ContactViewModle
import com.example.mvvmprojects.apps.acontactManager.viewmodel.ViewModelFactory
import com.example.mvvmprojects.databinding.ActivityContactManagerHomeBinding

class ContactManagerHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactManagerHomeBinding
    private lateinit var contactViewModle: ContactViewModle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_contact_manager_home)

        // Room Database
        val dao = ContactDatabase.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(dao)
        val factory = ViewModelFactory(repository)

        // View Model
        contactViewModle = ViewModelProvider(this,factory).get(ContactViewModle::class.java)

        binding.contactViewModel = contactViewModle

        // use this: LiveData and Data Binding integration
        binding.lifecycleOwner = this

        intiRecyclerView()

    }

    private fun intiRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayContactList()
    }

    private fun DisplayContactList() {
        contactViewModle.contacts.observe(this, Observer {
            binding.recyclerView.adapter = ContactRecyclerAdapter(
                it, {selectedItem: Contact -> listItemClicked(selectedItem)}
            )
        })
    }

    private fun listItemClicked(selectedItem: Contact){
        Toast.makeText(this,"Selected one is ${selectedItem.name}",Toast.LENGTH_LONG).show()

        contactViewModle.initUpdateAndDelete(selectedItem)
    }
}

/**
 * 1. Add dependency for room database in app level build.gradle.kts
 * Reference URL - https://developer.android.com/jetpack/androidx/releases/room
 *dependencies {
 *      // Room Database
 *     val room_version = "2.6.1"
 *     implementation("androidx.room:room-runtime:$room_version")
 *}
 *
 * * Also add below KSP in dependency  (KSP is more faster than KAPT)
 * in app level build.gradle.kts
 * dependencies {
 *      // To use Kotlin Symbol Processing (KSP)
 *     ksp("androidx.room:room-compiler:$room_version")
 * }
 *
 * * Reference URL for migrate from KAPT to KSP
 * Reference URL - https://developer.android.com/build/migrate-to-ksp
 * for migrate form KAPT to KSP add the below plugins in app level build.gradle.kts
 * plugins {
 *     id("com.google.devtools.ksp") version "1.9.0-1.0.13"
 * }
 *
 * * Also add below plugins in project level build.gradle.kts
 * plugins {
 *       id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
 *  }
 *
 *  * Add Coroutines support for Room
 *  Reference URL - https://developer.android.com/jetpack/androidx/releases/room
 *  Add dependency for room database in app level build.gradle.kts
 *  dependencies {
 *      implementation("androidx.room:room-ktx:$room_version")
 *  }
 *
 *  * Add Coroutines dependency
 *  Reference URL - https://developer.android.com/kotlin/coroutines
 *  dependencies {
 *      implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
 *  }
 *
 *  2. Entity data class.
 *  Create kotlin data class Name - "Contact"
 *
 *  3. DAO (Data Access Object)
 *  Create a kotlin interface name - "ContactDAO"
 *
 *  4. DataBase Part (Create Data base)
 *  Create a kotlin class name - "ContactDatabase"
 *
 *  5. Repository : is a class that is mainly used to manage multiple sources of data.
 *  Create a kotlin class name - "ContactRepository"
 *
 *  6. ViewModel :
 *  Create a kotlin class name - "ContactViewModle"
 *  * Add lifecycle depencency in app level build.gradle.kts
 *  Reference URL = https://developer.android.com/jetpack/androidx/releases/lifecycle
 *  dependencies {
 *          // ViewModel
 *         implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
 *  }
 *  * Add LiveData depencency in app level build.gradle.kts
 *  dependencies {
 *      // LiveData
 *      implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
 *  }
 *  * Enable Databinding in app level build.gradle.kts
 *  android {
 *      buildFeatures{
 *         dataBinding = true
 *     }
 * }
 * Note:- We cant use ksp with databinding. databinding is not supported with "ksp" and only supported with "kapt"
 * for adding the kapt plugin Referal URl = https://kotlinlang.org/docs/kapt.html#use-in-gradle
 * * Add below plugins in project level build.gradle.kts and remove the commented one
 * plugins {
 *      // id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
 *     kotlin("kapt") version "2.0.0"
 * }
 *
 * * Add below plugins in app level build.gradle.kts and remove the commented one
 * plugins {
 *     //id("com.google.devtools.ksp") version "1.9.0-1.0.13"
 *     id("kotlin-kapt")
 * }
 *
 * * Add below plugins in app level build.gradle.kts and remove the commented one
 * dependencies {
 *     kapt("androidx.room:room-compiler:$room_version")
 *     //ksp("androidx.room:room-compiler:$room_version")
 * }
 *
 * 7. Create Adapter
 *
 * 7. View Model Factory Class
 * Create a kotlin class name - "ViewModelFactory"
 *
 * **/