package com.example.contactmanagerapp.room

class UsersRepository (private val dao:UsersDAO) {

    val users = dao.getAllUsersFromDB()
    suspend fun insert(users:Users):Long{
        return dao.insertUsers(users)
    }
    suspend fun delete(users:Users) {
        return dao.deleteUsers(users)
    }
    suspend fun update(users:Users) {
        return dao.updateUsers(users)
    }
    suspend fun deleteAll() {
        return dao.deleteAll()
    }

}