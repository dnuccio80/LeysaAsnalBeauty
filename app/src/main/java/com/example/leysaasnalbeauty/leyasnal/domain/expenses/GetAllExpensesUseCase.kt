package com.example.leysaasnalbeauty.leyasnal.domain.expenses

import com.example.leysaasnalbeauty.leyasnal.data.balance.expenses.ExpenseRepository
import javax.inject.Inject

class GetAllExpensesUseCase @Inject constructor(private val expenseRepository: ExpenseRepository) {
    operator fun invoke() = expenseRepository.getAllExpenses()
}