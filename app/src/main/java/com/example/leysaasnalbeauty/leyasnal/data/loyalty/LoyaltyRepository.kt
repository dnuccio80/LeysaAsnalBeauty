package com.example.leysaasnalbeauty.leyasnal.data.loyalty

import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyClientPointsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyRewardPointsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyServicePointsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toLoyaltyClientPointsEntity
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toLoyaltyRewardPointsEntity
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toLoyaltyServicePointsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoyaltyRepository @Inject constructor(private val loyaltyDao: LoyaltyDao) {

    // Client-Points Relationship

    fun getAllClientPointsLoyalty(): Flow<List<LoyaltyWithClient>> = loyaltyDao.getAllLoyalty()

    suspend fun addClientPointLoyalty(loyalty: LoyaltyClientPointsEntity) =
        loyaltyDao.addLoyaltyClientPoints(loyalty)

    suspend fun deleteClientPointLoyalty(clientId: Int) =
        loyaltyDao.deleteLoyaltyClientPoints(clientId)

    suspend fun updateClientPointLoyalty(loyalty: LoyaltyClientPointsEntity) =
        loyaltyDao.updateLoyaltyClientPoints(loyalty)

    suspend fun upsertClientPointsLoyalty(loyalty:LoyaltyClientPointsDataClass) =
        loyaltyDao.upsertLoyaltyClientPoints(loyalty.toLoyaltyClientPointsEntity())

    fun getLoyaltyClientPointsById(clientId:Int):Flow<LoyaltyClientPointsDataClass> = loyaltyDao.getLoyaltyClientPointsById(clientId).map {
        item -> item?.toLoyaltyDataClass()?:LoyaltyClientPointsDataClass(clientId,0)
    }

    // Service-Points Relationship

    fun getAllServicePointsLoyalty(): Flow<List<LoyaltyServicePointsDataClass>> =
        loyaltyDao.getAllServicePoints().map {
            it.map { item ->
                item.toLoyaltyServicePointsDataClass()
            }
        }

    suspend fun addServicePointsLoyalty(loyalty: LoyaltyServicePointsDataClass) =
        loyaltyDao.addLoyaltyServicePoints(loyalty.toLoyaltyServicePointsEntity())

    suspend fun deleteServicePointsLoyalty(loyaltyId: Int) =
        loyaltyDao.deleteLoyaltyServicePoints(loyaltyId)

    fun getLoyaltyServicePointsById(loyaltyId:Int):Flow<LoyaltyServicePointsDataClass> = loyaltyDao.getLoyaltyServicePointsById(loyaltyId).map {
        item -> item.toLoyaltyServicePointsDataClass()
    }

    suspend fun updateServicePointsLoyalty(loyalty: LoyaltyServicePointsDataClass) =
        loyaltyDao.updateLoyaltyServicePoints(loyalty.toLoyaltyServicePointsEntity())

    // Reward-Points Relationship

    fun getAllRewardPointsLoyalty(): Flow<List<LoyaltyRewardPointsDataClass>> =
        loyaltyDao.getAllRewardPoints().map {
            it.map { item ->
                item.toLoyaltyRewardPointsDataClass()
            }
        }

    suspend fun addRewardPointsLoyalty(loyalty: LoyaltyRewardPointsDataClass) =
        loyaltyDao.addLoyaltyRewardPoints(loyalty.toLoyaltyRewardPointsEntity())

    suspend fun deleteRewardPointsLoyalty(loyaltyId: Int) =
        loyaltyDao.deleteLoyaltyRewardPointsEntity(loyaltyId)

    suspend fun updateRewardPointsLoyalty(loyalty: LoyaltyRewardPointsDataClass) =
        loyaltyDao.updateLoyaltyRewardPoints(loyalty.toLoyaltyRewardPointsEntity())


}