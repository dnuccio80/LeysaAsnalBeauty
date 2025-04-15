package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor

@Composable
fun AcceptDeclineButtons(onAccept: () -> Unit, onDecline: () -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.SpaceEvenly) {
        ButtonTextItem(stringResource(R.string.cancel)) { onDecline() }
        ButtonTextItem(stringResource(R.string.accept)) { onAccept() }
    }
}

