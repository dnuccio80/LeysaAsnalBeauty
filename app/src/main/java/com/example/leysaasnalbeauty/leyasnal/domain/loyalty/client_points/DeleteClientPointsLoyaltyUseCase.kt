package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.client_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import javax.inject.Inject

class DeleteClientPointsLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    suspend operator fun invoke(clientId: Int) = loyaltyRepository.deleteClientPointLoyalty(clientId)
}