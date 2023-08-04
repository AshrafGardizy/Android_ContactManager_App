package com.example.contactmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanagerapp.databinding.ActivityMainBinding
import com.example.contactmanagerapp.room.Users
import com.example.contactmanagerapp.room.UsersDatabase
import com.example.contactmanagerapp.room.UsersRepository
import com.example.contactmanagerapp.viewModel.UserViewModelFactory
import com.example.contactmanagerapp.viewModel.UsersViewModel
import com.example.contactmanagerapp.viewUI.MyRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var usersViewModel: UsersViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Room Database
        var dao = UsersDatabase.getInstance(applicationContext).usersDAO
        val repository = UsersRepository(dao)
        val factory = UserViewModelFactory(repository)
        usersViewModel = ViewModelProvider(this,factory).get(UsersViewModel::class.java)
        binding.userViewModel = usersViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        displayUsersLists()
    }

    private fun displayUsersLists() {
       usersViewModel.users.observe(this, Observer {
           binding.recyclerView.adapter = MyRecyclerViewAdapter(it,{selectedItem:Users->ListItemClicked(selectedItem)})
       })
    }

    private fun ListItemClicked(selectedItem: Users) {
        Toast.makeText(this,"Selected name is:${selectedItem.name}",Toast.LENGTH_SHORT).show()
        usersViewModel.initUdateAndDelete(selectedItem)
    }
}