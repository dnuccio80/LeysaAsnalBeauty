package com.example.leysaasnalbeauty.leyasnal.domain.earnings

import com.example.leysaasnalbeauty.leyasnal.data.balance.earnings.EarningRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass
import javax.inject.Inject

class UpdateEarningUseCase @Inject constructor(private val earningRepository: EarningRepository) {
    suspend operator fun invoke(earning:EarningDataClass) = earningRepository.updateEarning(earning)
}