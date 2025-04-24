package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.leysaasnalbeauty.ui.theme.TextColor

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    textAlignment: TextAlign = TextAlign.Start,
    color: Color = TextColor,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    Text(
        text,
        color = color,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = textAlignment,
        maxLines = maxLines,
        overflow = overflow
    )
}