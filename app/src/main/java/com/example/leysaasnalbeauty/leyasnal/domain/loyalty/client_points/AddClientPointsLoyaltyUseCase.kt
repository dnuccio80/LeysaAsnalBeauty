package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.client_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyClientPointsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toLoyaltyClientPointsEntity
import javax.inject.Inject

class AddClientPointsLoyaltyUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository){
    suspend operator fun invoke(loyalty: LoyaltyClientPointsDataClass) = loyaltyRepository.addClientPointLoyalty(loyalty.toLoyaltyClientPointsEntity())
}