package com.example.leysaasnalbeauty.leyasnal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.MainTextField
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.ui.theme.AccentColor

@Composable
fun GiftCardScreen(innerPadding: PaddingValues) {

    var giftName by rememberSaveable { mutableStateOf("") }
    var toName by rememberSaveable { mutableStateOf("") }
    var fromName by rememberSaveable { mutableStateOf("") }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            FirstTitleText("Crear Gift Card")
            HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.Gray)
            ThirdTitleText("Completa los datos para crear tu gift card")
            MainTextField(
                value = giftName,
                isNumeric = false,
                isPhone = false,
                onValueChange = { giftName = it },
                label = "Tipo de regalo",
                icon = R.drawable.ic_gift
            )
            MainTextField(
                value = toName,
                isNumeric = false,
                isPhone = false,
                onValueChange = { toName = it },
                label = "A quien va dirigido el regalo",
                icon = R.drawable.ic_name
            )
            MainTextField(
                value = fromName,
                isNumeric = false,
                isPhone = false,
                onValueChange = { fromName = it },
                label = "De parte de quien?",
                icon = R.drawable.ic_flower
            )
            Spacer(Modifier.size(32.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                ButtonTextItem(
                    text = "Generar Gift Card",
                    buttonColor = AccentColor
                ) { }
            }
        }
    }
}

