package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonsGroup(list: List<String>, selected: String, onSelectedChanged: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        list.forEach { listItem ->
            RadioButtonItem(text = listItem, selected) { onSelectedChanged(listItem) }
        }
    }
}