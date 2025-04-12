package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.ui.theme.AccentColor
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import com.example.leysaasnalbeauty.ui.theme.MainColor

@Composable
fun SquareCardComponent(icon:Int, text:String) {
    Card(
        modifier = Modifier
            .size(120.dp).border(2.5.dp, color = DarkAccentColor, shape = RoundedCornerShape(8.dp))
        ,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = AccentColor
        )
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(painter = painterResource(icon), contentDescription = "Card icon", modifier = Modifier.size(48.dp))
            BodyText(text, textAlignment = TextAlign.Center)
        }
    }
}