package com.example.leysaasnalbeauty.leyasnal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientDao
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientEntity

@Database(entities = [ClientEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract val clientDao: ClientDao
}