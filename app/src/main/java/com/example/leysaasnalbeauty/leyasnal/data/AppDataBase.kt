package com.example.leysaasnalbeauty.leyasnal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.leysaasnalbeauty.leyasnal.data.balance.EarningDao
import com.example.leysaasnalbeauty.leyasnal.data.balance.EarningEntity
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientDao
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientEntity

@Database(entities = [ClientEntity::class, EarningEntity::class] , version = 2)
abstract class AppDataBase : RoomDatabase(){
    abstract val clientDao: ClientDao
    abstract val earningDao: EarningDao
}