package com.example.contactmanagerapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactmanagerapp.room.UsersRepository

class UserViewModelFactory (private val repository: UsersRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(UsersViewModel::class.java))
        {
            return  UsersViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknow ViewModel")
    }
}