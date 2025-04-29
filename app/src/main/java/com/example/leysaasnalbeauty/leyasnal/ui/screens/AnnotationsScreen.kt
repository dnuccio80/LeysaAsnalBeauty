package com.example.leysaasnalbeauty.leyasnal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import com.example.leysaasnalbeauty.ui.theme.PostIt1
import com.example.leysaasnalbeauty.ui.theme.PostIt2
import com.example.leysaasnalbeauty.ui.theme.PostIt3
import com.example.leysaasnalbeauty.ui.theme.PostIt4

@Composable
fun AnnotationsScreen(innerPadding: PaddingValues, viewModel: AppViewModel) {

    val cursiveFont = Font(
        R.font.cursive
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                FirstTitleText(stringResource(R.string.annotations))
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.White)
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    items(30) { item ->
                        Box(
                            modifier = Modifier
                                .size(200.dp)
                                .weight(1f)
                                .background(pickPostItColor())
                                .clickable {

                                }
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Description $item!", style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily(cursiveFont),
                                fontWeight = FontWeight.ExtraBold
                            ))
                        }
                    }
                }
            )
        }
        FloatingActionButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            elevation = FloatingActionButtonDefaults.elevation(16.dp),
            containerColor = DarkAccentColor,
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add icon")
        }
    }
}

fun pickPostItColor() : Color {
    val randomNumber = (1..4).random()

    return when(randomNumber) {
        1 ->  PostIt1
        2 ->  PostIt2
        3 ->  PostIt3
        4 ->  PostIt4
        else ->  PostIt1
    }
}