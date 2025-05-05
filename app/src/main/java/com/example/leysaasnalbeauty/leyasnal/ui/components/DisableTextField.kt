package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor

@Composable
fun DisableTextField(value: String) {
    TextField(
        value = value,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            disabledTextColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledIndicatorColor = DarkAccentColor
        ),
        enabled = false
    )
}