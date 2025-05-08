package com.example.leysaasnalbeauty.leyasnal.domain.loyalty

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import javax.inject.Inject

class DeleteLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    suspend operator fun invoke(loyaltyId: Int) = loyaltyRepository.deleteLoyalty(loyaltyId)
}