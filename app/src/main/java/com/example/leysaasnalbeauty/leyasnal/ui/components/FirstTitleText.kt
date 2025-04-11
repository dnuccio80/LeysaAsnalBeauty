package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun FirstTitleText(text:String, color: Color = Color.White) {
    Text(text = text, color = color, style = MaterialTheme.typography.titleLarge)
}