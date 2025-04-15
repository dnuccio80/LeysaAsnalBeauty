package com.example.leysaasnalbeauty.leyasnal.ui.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R

@Composable
fun AppTopBar(){
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo_with_background),
            contentDescription = "logo",
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight(),
            contentScale = ContentScale.Fit
        )
    }
}