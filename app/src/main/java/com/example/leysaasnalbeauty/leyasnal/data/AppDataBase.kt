package com.example.leysaasnalbeauty.leyasnal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.leysaasnalbeauty.leyasnal.data.annotations.AnnotationsDao
import com.example.leysaasnalbeauty.leyasnal.data.annotations.AnnotationsEntity
import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentDao
import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentEntity
import com.example.leysaasnalbeauty.leyasnal.data.balance.earnings.EarningDao
import com.example.leysaasnalbeauty.leyasnal.data.balance.earnings.EarningEntity
import com.example.leysaasnalbeauty.leyasnal.data.balance.expenses.ExpenseDao
import com.example.leysaasnalbeauty.leyasnal.data.balance.expenses.ExpenseEntity
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientDao
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientEntity
import com.example.leysaasnalbeauty.leyasnal.data.converters.Converters
import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyDao
import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyEntity

@Database(entities = [ClientEntity::class, EarningEntity::class, ExpenseEntity::class, AnnotationsEntity::class, AppointmentEntity::class, LoyaltyEntity::class] , version = 8)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase(){
    abstract val clientDao: ClientDao
    abstract val earningDao: EarningDao
    abstract val expenseDao: ExpenseDao
    abstract val annotationsDao: AnnotationsDao
    abstract val appointmentDao: AppointmentDao
    abstract val loyaltyDao: LoyaltyDao
}