package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EntryDataClass

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EntryFlowRow(
    showEarningDialog:() -> Unit,
    showExpenseDialog:() -> Unit,
    showNewClientDialog:() -> Unit
){

    val entryList: List<EntryDataClass> = listOf(
        EntryDataClass(
            R.drawable.ic_money, stringResource(R.string.add_earning),
            onClick = { showEarningDialog() }
        ),
        EntryDataClass(
            R.drawable.ic_buys, stringResource(R.string.add_expense),
            onClick = { showExpenseDialog() }
        ),
        EntryDataClass(
            R.drawable.ic_woman, stringResource(R.string.new_client),
            onClick = { showNewClientDialog() }
        ),
        EntryDataClass(
            R.drawable.ic_fidelity, stringResource(R.string.new_fidelity_client),
            onClick = { }
        ),
        EntryDataClass(
            R.drawable.ic_gift_card, stringResource(R.string.create_gift_card),
            onClick = { }
        ),
        EntryDataClass(
            R.drawable.ic_message, stringResource(R.string.notify_client),
            onClick = { }
        ),
    )

    FlowRow(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = Int.MAX_VALUE,

        ) {
        entryList.forEach { entry ->
            SquareCardComponent(entry.icon, entry.title) { entry.onClick() }
        }
    }
}