package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BackButtonItem(onBackButtonClicked: () -> Unit){
    Icon(
        Icons.AutoMirrored.Default.KeyboardArrowLeft,
        contentDescription = "Back Arrow",
        tint = Color.White,
        modifier = Modifier
            .size(32.dp)
            .clickable {
                onBackButtonClicked()
            }
    )
}