package com.example.leysaasnalbeauty.leyasnal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.leysaasnalbeauty.leyasnal.data.balance.earnings.EarningDao
import com.example.leysaasnalbeauty.leyasnal.data.balance.earnings.EarningEntity
import com.example.leysaasnalbeauty.leyasnal.data.balance.expenses.ExpenseDao
import com.example.leysaasnalbeauty.leyasnal.data.balance.expenses.ExpenseEntity
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientDao
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientEntity

@Database(entities = [ClientEntity::class, EarningEntity::class, ExpenseEntity::class] , version = 3)
abstract class AppDataBase : RoomDatabase(){
    abstract val clientDao: ClientDao
    abstract val earningDao: EarningDao
    abstract val expenseDao: ExpenseDao

}