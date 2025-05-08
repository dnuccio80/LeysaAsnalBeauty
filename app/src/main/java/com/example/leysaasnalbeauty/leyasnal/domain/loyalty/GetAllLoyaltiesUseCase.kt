package com.example.leysaasnalbeauty.leyasnal.domain.loyalty

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import javax.inject.Inject

class GetAllLoyaltiesUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
     operator fun invoke() = loyaltyRepository.getAllLoyalties()
}