package com.example.leysaasnalbeauty.leyasnal.data.loyalty

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoyaltyRepository @Inject constructor(private val loyaltyDao: LoyaltyDao) {

    fun getAllLoyalties(): Flow<List<LoyaltyWithClient>> = loyaltyDao.getAllLoyalty()

    suspend fun addLoyalty(loyalty: LoyaltyEntity) = loyaltyDao.addLoyalty(loyalty)

    suspend fun deleteLoyalty(loyaltyId:Int) = loyaltyDao.deleteLoyalty(loyaltyId)

    suspend fun updateLoyalty(loyalty: LoyaltyEntity) = loyaltyDao.updateLoyalty(loyalty)

}