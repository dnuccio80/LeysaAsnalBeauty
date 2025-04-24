package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonItem(text: String, selected: String, onClick: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
        onClick(text)
    }, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        RadioButton(
            selected = selected == text,
            onClick = {
                onClick(text)
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.White,
                unselectedColor = Color.White
            )
        )
        BodyText(text)
    }
}