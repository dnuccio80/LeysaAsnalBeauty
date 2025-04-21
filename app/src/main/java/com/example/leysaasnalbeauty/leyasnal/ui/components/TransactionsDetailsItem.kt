package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.TransactionsDataClass

@Composable
fun TransactionDetailsItem(transactions: TransactionsDataClass, viewModel: AppViewModel, onClick:(TransactionsDataClass) -> Unit) {

    val formatedAmount = viewModel.formatPrice(transactions.amount)

    Row(Modifier.fillMaxWidth().clickable {
        onClick(transactions)
    }) {
        Box(modifier = Modifier.weight(1f)) {
            BodyText(transactions.description, maxLines = 1, modifier = Modifier.padding(end = 8.dp), overflow = TextOverflow.Ellipsis)
        }
        BodyText(formatedAmount)
    }
}