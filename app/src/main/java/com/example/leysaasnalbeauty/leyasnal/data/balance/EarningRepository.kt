package com.example.leysaasnalbeauty.leyasnal.data.balance

import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toEarningEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EarningRepository @Inject constructor(private val earningDao: EarningDao) {

    fun getAllEarnings(): Flow<List<EarningDataClass>> = earningDao.getAllEarnings().map {
        it.map { earningEntity -> earningEntity.toEarningDataClass() }
    }

    fun deleteAllEarningsData() = earningDao.deleteAllEarningsData()

    suspend fun addEarning(earning: EarningDataClass) = earningDao.addEarning(earning.toEarningEntity())

    suspend fun deleteEarning(earning:EarningDataClass) = earningDao.deleteEarning(earning.toEarningEntity())

    suspend fun updateEarning(earning:EarningDataClass) = earningDao.updateEarning(earning.toEarningEntity())

}