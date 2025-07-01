package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.data.Routes
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText

sealed class BottomNavigationBarItem {
    abstract val route: String
    abstract val icon: @Composable () -> Unit
    abstract val label: @Composable () -> Unit

    data class Home(
        override val route: String = Routes.Home.route,
        override val icon: @Composable () -> Unit = { Icon(Icons.Filled.Home, contentDescription = "") },
        override val label: @Composable () -> Unit = { BodyText(stringResource(R.string.home)) }
    ):BottomNavigationBarItem()

    data class Clients(
        override val route: String = Routes.Clients.route,
        override val icon: @Composable () -> Unit = { Icon(Icons.Filled.Favorite, contentDescription = "") },
        override val label: @Composable () -> Unit = { BodyText(stringResource(R.string.clients)) }
    ):BottomNavigationBarItem()

    data class Appointments(
        override val route: String = Routes.AppointmentList.route,
        override val icon: @Composable () -> Unit = { Icon(painterResource(R.drawable.ic_date), contentDescription = "") },
        override val label: @Composable () -> Unit = { BodyText(stringResource(R.string.appointments)) }
    ):BottomNavigationBarItem()

    data class Annotations(
        override val route: String = Routes.Annotations.route,
        override val icon: @Composable () -> Unit = { Icon(painterResource(R.drawable.ic_annotations), contentDescription = "") },
        override val label: @Composable () -> Unit = { BodyText(stringResource(R.string.notes)) }
    ):BottomNavigationBarItem()

}