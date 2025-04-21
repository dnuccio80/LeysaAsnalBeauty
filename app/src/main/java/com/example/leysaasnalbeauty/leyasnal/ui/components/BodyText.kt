package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.leysaasnalbeauty.ui.theme.TextColor

@Composable
fun BodyText(text: String, textAlignment: TextAlign = TextAlign.Start, color:Color = TextColor) {
   Text(text, color = color, style = MaterialTheme.typography.bodyLarge, textAlign = textAlignment)
}