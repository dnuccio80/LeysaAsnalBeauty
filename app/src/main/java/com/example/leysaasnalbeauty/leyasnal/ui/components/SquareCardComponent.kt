package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.ui.theme.DarkGrayColor

@Composable
fun SquareCardComponent(icon: Int, text: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clickable {
                onClick()
            },
//            .border(2.5.dp, color = DarkAccentColor, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkGrayColor
        )
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = "Card icon",
                modifier = Modifier.size(48.dp)
            )
            BodyText(text, textAlignment = TextAlign.Center)
        }
    }
}