package com.example.quire.dataBase

import com.example.quire.dataBase.note.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserRepository (private val appDatabase : AppDatabase, private val
coroutineScope : CoroutineScope ) {

    fun addUser(user: User){
        appDatabase.userDao().insertUser(user)
    }
    fun addNote(note: Note){
        appDatabase.userDao().addNoteToUser(note)
    }
    fun removeNote(index: Int){
        appDatabase.userDao().removeNoteAtIndex(index)
    }
    fun getUserInfo(): List<User> {
        return appDatabase.userDao().getAllUsers()
    }


    fun performDatabaseOperation (dispatcher: CoroutineDispatcher,
                                  databaseOperation : suspend () -> Unit) {
        coroutineScope .launch(dispatcher) {
            databaseOperation ()
        }
    }
}