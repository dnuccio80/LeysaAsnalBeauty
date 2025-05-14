package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.reward_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import javax.inject.Inject

class GetAllRewardPointsLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    operator fun invoke() = loyaltyRepository.getAllRewardPointsLoyalty()
}