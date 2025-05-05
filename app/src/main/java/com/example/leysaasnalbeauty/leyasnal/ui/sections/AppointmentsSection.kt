package com.example.leysaasnalbeauty.leyasnal.ui.sections

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentWithClient
import com.example.leysaasnalbeauty.leyasnal.ui.components.AppointmentItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonIconItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.TransactionDetailsItem
import com.example.leysaasnalbeauty.ui.theme.AccentColor
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor

@Composable
fun AppointmentsSection(
    title: String,
    appointments: List<AppointmentWithClient>,
    onAppointmentClicked: (Int) -> Unit
) {

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val cardHeight by animateDpAsState(
        targetValue = if (isExpanded) 370.dp else 250.dp,
        animationSpec = TweenSpec(durationMillis = 200),
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(horizontal = 8.dp)
            .border(4.dp, DarkAccentColor, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = AccentColor
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SecondTitleText(title)
                    Image(
                        painterResource(R.drawable.ic_appointment),
                        modifier = Modifier.size(28.dp),
                        contentDescription = ""
                    )
                }
                ButtonIconItem(if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown) {
                    isExpanded = !isExpanded
                }
            }
            Column(Modifier.fillMaxWidth()) {
                HorizontalDivider(thickness = 1.dp, color = DarkAccentColor)
                HorizontalDivider(thickness = 1.dp, color = Color.Black)
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                appointments.forEach {
                    AppointmentItem(
                        appointment = it,
                        showDate = false,
                        onClick = { onAppointmentClicked(it.appointment.id) }
                    )
                }
            }
        }
    }
}