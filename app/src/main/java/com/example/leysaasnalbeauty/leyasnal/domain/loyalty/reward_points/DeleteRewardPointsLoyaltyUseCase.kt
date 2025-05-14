package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.reward_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import javax.inject.Inject

class DeleteRewardPointsLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    suspend operator fun invoke(loyaltyId:Int) = loyaltyRepository.deleteRewardPointsLoyalty(loyaltyId)
}