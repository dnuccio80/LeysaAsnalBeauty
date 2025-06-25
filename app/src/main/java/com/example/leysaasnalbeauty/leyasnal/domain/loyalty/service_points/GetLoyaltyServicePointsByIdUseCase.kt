package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.service_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import javax.inject.Inject

class GetLoyaltyServicePointsByIdUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    operator fun invoke(loyaltyId:Int) = loyaltyRepository.getLoyaltyServicePointsById(loyaltyId)
}