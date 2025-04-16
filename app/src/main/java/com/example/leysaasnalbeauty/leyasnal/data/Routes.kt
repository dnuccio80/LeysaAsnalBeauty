package com.example.leysaasnalbeauty.leyasnal.data

sealed class Routes(val route: String) {
    data object Home: Routes("home")
    data object Clients: Routes("clients")
    data object Annotations: Routes("annotations")
}