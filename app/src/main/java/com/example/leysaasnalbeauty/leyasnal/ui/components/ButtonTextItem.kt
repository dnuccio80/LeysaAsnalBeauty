package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor

@Composable
fun ButtonTextItem(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkAccentColor
        )
    ) {
        BodyText(text)
    }
}