package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.leysaasnalbeauty.ui.theme.TextColor

@Composable
fun SecondTitleText(text: String, color: Color = TextColor) {
    Text(text = text, color = color, style = MaterialTheme.typography.titleMedium)
}