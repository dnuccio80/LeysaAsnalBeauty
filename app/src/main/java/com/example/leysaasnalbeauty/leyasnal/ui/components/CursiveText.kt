package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leysaasnalbeauty.R

@Composable
fun CursiveText(text: String, xOffset: Dp, yOffset: Dp) {

    val cursiveFont = FontFamily(
        Font(R.font.cursive)
    )

    Text(
        text,
        fontFamily = cursiveFont,
        color = Color.Black,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .absoluteOffset(xOffset, yOffset),
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.SemiBold
    )
}