package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.service_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyServicePointsDataClass
import javax.inject.Inject

class AddServicePointsLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    suspend operator fun invoke(loyalty:LoyaltyServicePointsDataClass) = loyaltyRepository.addServicePointsLoyalty(loyalty)
}