package com.example.leysaasnalbeauty.leyasnal.domain.expenses

import com.example.leysaasnalbeauty.leyasnal.data.balance.expenses.ExpenseRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ExpenseDataClass
import javax.inject.Inject
import kotlin.math.exp

class UpdateExpenseUseCase @Inject constructor(private val expenseRepository: ExpenseRepository) {
    suspend operator fun invoke(expense: ExpenseDataClass) = expenseRepository.updateExpense(expense)
}