package com.example.leysaasnalbeauty.leyasnal.domain.earnings

import com.example.leysaasnalbeauty.leyasnal.data.balance.earnings.EarningRepository
import javax.inject.Inject

class DeleteAllEarningsUseCase @Inject constructor(private val earningRepository: EarningRepository) {
    operator fun invoke() = earningRepository.deleteAllEarningsData()
}