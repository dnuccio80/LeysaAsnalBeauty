package com.example.leysaasnalbeauty.leyasnal.ui.sections

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonIconItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.helpers.sendWppMessage
import com.example.leysaasnalbeauty.ui.theme.DarkGrayColor
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import java.time.LocalDate

@Composable
fun BirthdaySection(viewModel: AppViewModel) {

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val clients by viewModel.clients.collectAsState()
    val today = LocalDate.now()

    val birthdayList = clients.filter {
        it.birthday.month == today.month && it.birthday.dayOfMonth == today.dayOfMonth
    }

    val cardHeight by animateDpAsState(
        targetValue = if (isExpanded) 370.dp else 250.dp,
        animationSpec = TweenSpec(durationMillis = 200),
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(horizontal = 8.dp),
//            .border(4.dp, DarkAccentColor, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = DarkGrayColor
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)){
                    SecondTitleText(stringResource(R.string.today_birthday))
                    Image(painterResource(R.drawable.image_birthday), modifier = Modifier.size(28.dp), contentDescription = "")
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
                if (birthdayList.isEmpty()) {
                    ThirdTitleText(stringResource(R.string.no_birthday_today))
                } else {
                    birthdayList.forEach {
                        BirthdayItem(it)
                    }
                }
            }
        }
    }
}

@Composable
fun BirthdayItem(client: ClientDataClass) {

    val context = LocalContext.current

    val firstName = client.name.split(" ").first()

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        BodyText(client.name)
        ButtonTextItem(
            text = stringResource(R.string.say_hello),
            buttonColor = DarkAccentColor
        ) {
            sendWppMessage(
                context,
                client.phone,
                message = "${context.getString(R.string.hello)} $firstName \uD83E\uDD17\n${context.getString(R.string.birthday_message)}"
            )
        }
    }
}