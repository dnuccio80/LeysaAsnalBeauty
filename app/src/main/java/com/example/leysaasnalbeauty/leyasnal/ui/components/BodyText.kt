package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun BodyText(text: String, textAlignment: TextAlign = TextAlign.Start) {
   Text(text, color = Color.White, style = MaterialTheme.typography.bodyLarge, textAlign = textAlignment)
}