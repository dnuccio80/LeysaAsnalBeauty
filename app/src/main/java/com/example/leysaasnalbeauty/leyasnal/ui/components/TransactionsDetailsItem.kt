package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass

@Composable
fun TransactionDetailsItem(earning: EarningDataClass, viewModel: AppViewModel, onClick:(EarningDataClass) -> Unit) {

    val formatedAmount = viewModel.formatPrice(earning.amount)

    Row(Modifier.fillMaxWidth().clickable {
        onClick(earning)
    }) {
        Box(modifier = Modifier.weight(1f)) {
            BodyText(earning.description)
        }
        BodyText(formatedAmount)
    }
}