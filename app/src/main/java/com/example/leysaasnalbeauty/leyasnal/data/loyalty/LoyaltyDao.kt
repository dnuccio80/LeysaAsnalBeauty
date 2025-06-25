package com.example.leysaasnalbeauty.leyasnal.data.loyalty

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface LoyaltyDao {

    // Client-Points Relationship

    @Query("SELECT * FROM LoyaltyClientPointsEntity ORDER BY points DESC")
    fun getAllLoyalty():Flow<List<LoyaltyWithClient>>

    @Insert
    suspend fun addLoyaltyClientPoints(loyalty: LoyaltyClientPointsEntity)

    @Query("DELETE FROM LoyaltyClientPointsEntity WHERE clientId = :clientId")
    suspend fun deleteLoyaltyClientPoints(clientId: Int)

    @Update
    suspend fun updateLoyaltyClientPoints(loyalty: LoyaltyClientPointsEntity)

    @Query ("SELECT * FROM LoyaltyClientPointsEntity where clientId = :clientId LIMIT 1")
    fun getLoyaltyClientPointsById(clientId: Int): Flow<LoyaltyClientPointsEntity?>

    @Upsert
    suspend fun upsertLoyaltyClientPoints(loyalty: LoyaltyClientPointsEntity)

    // Service-Points Relationship

    @Query("SELECT * FROM LoyaltyServicePointsEntity ORDER BY points DESC")
    fun getAllServicePoints():Flow<List<LoyaltyServicePointsEntity>>

    @Insert
    suspend fun addLoyaltyServicePoints(loyalty: LoyaltyServicePointsEntity)

    @Query("SELECT * FROM LoyaltyServicePointsEntity WHERE id = :loyaltyId LIMIT 1")
    fun getLoyaltyServicePointsById(loyaltyId: Int): Flow<LoyaltyServicePointsEntity>

    @Update
    suspend fun updateLoyaltyServicePoints(loyalty: LoyaltyServicePointsEntity)

    @Query("DELETE FROM LoyaltyServicePointsEntity WHERE id = :loyaltyId")
    suspend fun deleteLoyaltyServicePoints(loyaltyId:Int)

    // Reward-Points Relationship

    @Query("SELECT * FROM LoyaltyRewardPointsEntity ORDER BY points ASC")
    fun getAllRewardPoints():Flow<List<LoyaltyRewardPointsEntity>>

    @Insert
    suspend fun addLoyaltyRewardPoints(loyalty:LoyaltyRewardPointsEntity)

    @Update
    suspend fun updateLoyaltyRewardPoints(loyalty: LoyaltyRewardPointsEntity)

    @Query ("DELETE FROM LoyaltyRewardPointsEntity where id = :loyaltyId")
    suspend fun deleteLoyaltyRewardPointsEntity(loyaltyId:Int)



}