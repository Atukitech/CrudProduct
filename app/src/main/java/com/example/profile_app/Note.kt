package com.example.profile_app




import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "notes")
data class Note (
    @PrimaryKey(autoGenerate = true)

    val id:Int = 0,
    val name : String,
    val quantity : String,
    val category : String,
    val price: String,
    val available: String,
)