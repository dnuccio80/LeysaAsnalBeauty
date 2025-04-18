package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ExpenseDataClass
import com.example.leysaasnalbeauty.ui.theme.AccentColor
import com.example.leysaasnalbeauty.ui.theme.NegativeColor
import com.example.leysaasnalbeauty.ui.theme.NeutralColor
import com.example.leysaasnalbeauty.ui.theme.PositiveColor

@Composable
fun BalanceDetail(
    earnings: List<EarningDataClass>,
    expenses: List<ExpenseDataClass>,
    viewModel: AppViewModel
) {

    val balance = earnings.sumOf { it.amount } - expenses.sumOf { it.amount }

    val formatedBalance = viewModel.formatPrice(balance)

    val color: Color = when {
        balance == 0 -> NeutralColor
        balance > 0 -> PositiveColor
        else -> NegativeColor
    }

    Column {
        SecondTitleText(stringResource(R.string.balance), color = AccentColor)
        FirstTitleText(formatedBalance, color = color)
    }
}