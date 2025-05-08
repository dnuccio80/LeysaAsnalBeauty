package com.example.leysaasnalbeauty.leyasnal.data.loyalty

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LoyaltyDao {

    @Query("SELECT * FROM LoyaltyEntity ORDER BY points ASC")
    fun getAllLoyalty():Flow<List<LoyaltyWithClient>>

    @Insert
    suspend fun addLoyalty(loyalty: LoyaltyEntity)

    @Query("DELETE FROM LoyaltyEntity WHERE id = :loyaltyId")
    suspend fun deleteLoyalty(loyaltyId: Int)

    @Update
    suspend fun updateLoyalty(loyalty: LoyaltyEntity)

}