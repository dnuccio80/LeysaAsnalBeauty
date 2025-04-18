package com.example.leysaasnalbeauty.leyasnal.data.balance.earnings

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EarningDao {

    @Query("SELECT * FROM EarningEntity")
    fun getAllEarnings(): Flow<List<EarningEntity>>

    @Query("DELETE FROM EarningEntity")
    fun deleteAllEarningsData()

    @Insert
    suspend fun addEarning(earning: EarningEntity)

    @Delete
    suspend fun deleteEarning(earning: EarningEntity)

    @Update
    suspend fun updateEarning(earning: EarningEntity)
}