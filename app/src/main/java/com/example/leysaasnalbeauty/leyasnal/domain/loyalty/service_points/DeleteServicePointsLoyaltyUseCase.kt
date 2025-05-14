package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.service_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import javax.inject.Inject

class DeleteServicePointsLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    suspend operator fun invoke(loyaltyId:Int) = loyaltyRepository.deleteServicePointsLoyalty(loyaltyId)
}