package com.example.contactmanagerapp.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactmanagerapp.room.Users
import com.example.contactmanagerapp.room.UsersRepository
import kotlinx.coroutines.launch

class UsersViewModel (private val repository: UsersRepository):ViewModel(),Observable {

    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userUpdateToDelete:Users
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
    fun saveOrUpdate(){

        if(isUpdateOrDelete){
            //Make update
            userUpdateToDelete.name = inputName.value!!
            userUpdateToDelete.email = inputEmail.value!!
            update(userUpdateToDelete)

        }else{
            //Insert Functionality
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(Users(0,name,email))
            inputName.value = null
            inputEmail.value = null
        }

    }
    fun clearAllOrDelete()
    {
        if(isUpdateOrDelete){
            delete(userUpdateToDelete)

        }else{
            clearAll()
        }

    }
    fun insert(users:Users) = viewModelScope.launch {
        repository.insert(users)
    }
    fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun initUdateAndDelete(users: Users){

        inputName.value = users.name
        inputEmail.value = users.email
        isUpdateOrDelete = true
        userUpdateToDelete = users
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    fun update(users: Users) = viewModelScope.launch {
        repository.update(users)
        //Resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"

    }
    fun delete(users: Users) = viewModelScope.launch {
        repository.delete(users)
        //Resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}