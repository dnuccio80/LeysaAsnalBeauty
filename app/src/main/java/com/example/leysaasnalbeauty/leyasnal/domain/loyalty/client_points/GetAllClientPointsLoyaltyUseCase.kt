package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.client_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import javax.inject.Inject

class GetAllClientPointsLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
     operator fun invoke() = loyaltyRepository.getAllClientPointsLoyalty()
}