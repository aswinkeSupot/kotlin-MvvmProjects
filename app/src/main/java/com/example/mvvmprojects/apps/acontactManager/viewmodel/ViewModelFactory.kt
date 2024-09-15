package com.example.mvvmprojects.apps.acontactManager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmprojects.apps.acontactManager.repository.ContactRepository

/**
 * If our viewModel has a constructor with parameters we can't use the
 * default constructor that the viewModel framework provides.
 *
 * ViewModelFactory: pass the required parameters to ViewModel
 * **/

class ViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactViewModle::class.java)){
            return ContactViewModle(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}