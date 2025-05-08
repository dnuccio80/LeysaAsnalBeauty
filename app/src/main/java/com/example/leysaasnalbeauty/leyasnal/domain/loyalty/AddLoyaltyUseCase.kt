package com.example.leysaasnalbeauty.leyasnal.domain.loyalty

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toLoyaltyEntity
import javax.inject.Inject

class AddLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository){
    suspend operator fun invoke(loyalty: LoyaltyDataClass) = loyaltyRepository.addLoyalty(loyalty.toLoyaltyEntity())
}