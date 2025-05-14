package com.example.leysaasnalbeauty.leyasnal.domain.loyalty.client_points

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyClientPointsDataClass
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoyaltyClientPointsByIdUseCase @Inject constructor(private val loyaltyRepository: LoyaltyRepository) {
    operator fun invoke(clientId:Int): Flow<LoyaltyClientPointsDataClass> = loyaltyRepository.getLoyaltyClientPointsById(clientId)
}