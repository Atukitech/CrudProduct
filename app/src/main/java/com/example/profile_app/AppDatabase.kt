package com.example.app_store

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.profile_app.Note
import com.example.profile_app.NoteDao

@Database(entities= [Note::class], version =1)
abstract class AppDatabase:RoomDatabase() {
    abstract  fun noteDao(): NoteDao
    companion object{
        private  var INSTANCE : AppDatabase?=null

        fun getDatabase(context: Context): AppDatabase{

            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "note_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }

}
