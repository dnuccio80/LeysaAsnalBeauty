package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.service_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyServicePointsEntity
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyServicePointsDataClass
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllServicePointsLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    operator fun invoke(): Flow<List<LoyaltyServicePointsDataClass>> = loyaltyRepository.getAllServicePointsLoyalty()
}