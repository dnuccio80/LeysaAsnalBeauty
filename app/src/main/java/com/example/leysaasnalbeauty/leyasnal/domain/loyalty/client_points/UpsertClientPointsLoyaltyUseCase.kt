package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.client_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyClientPointsDataClass
import javax.inject.Inject

class UpsertClientPointsLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    suspend operator fun invoke(loyalty:LoyaltyClientPointsDataClass) = loyaltyRepository.upsertClientPointsLoyalty(loyalty)
}