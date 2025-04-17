package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass
import com.example.leysaasnalbeauty.ui.theme.AccentColor

@Composable
fun BalanceDetail(earnings: List<EarningDataClass>) {

    val balance = earnings.sumOf { it.amount }

    Column {
        SecondTitleText(stringResource(R.string.balance), color = AccentColor)
        FirstTitleText("$ $balance")
    }
}