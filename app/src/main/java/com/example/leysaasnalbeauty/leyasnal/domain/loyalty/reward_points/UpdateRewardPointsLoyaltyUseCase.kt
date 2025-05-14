package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.reward_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyRewardPointsDataClass
import javax.inject.Inject

class UpdateRewardPointsLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    suspend operator fun invoke(loyalty: LoyaltyRewardPointsDataClass) = loyaltyRepository.updateRewardPointsLoyalty(loyalty)
}