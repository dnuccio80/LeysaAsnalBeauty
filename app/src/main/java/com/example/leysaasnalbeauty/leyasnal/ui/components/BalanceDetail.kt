package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.leysaasnalbeauty.ui.theme.AccentColor

@Composable
fun BalanceDetail() {
    Column {
        SecondTitleText("Balance", color = AccentColor)
        FirstTitleText("$150.000")
    }
}