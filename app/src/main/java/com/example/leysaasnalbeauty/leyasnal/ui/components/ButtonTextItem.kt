package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor

@Composable
fun ButtonTextItem(text: String,buttonColor:Color, enabled:Boolean = true, horizontalPadding: Dp = 16.dp, verticalPadding:Dp = 4.dp, onClick: () -> Unit ) {
    Button(
        onClick = { onClick() },
        enabled = enabled,
        contentPadding = PaddingValues(vertical = verticalPadding, horizontal = horizontalPadding),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            disabledContainerColor = Color.DarkGray
        )
    ) {
        BodyText(text)
    }
}