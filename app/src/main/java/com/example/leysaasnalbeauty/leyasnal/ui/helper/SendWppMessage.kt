package com.example.leysaasnalbeauty.leyasnal.ui.helper

import android.content.Context

fun sendWppMessage(context: Context, phoneNumber: String, message: String) {
    val wppSender = getWhatsAppSenderAuto(
        context = context
    )

    wppSender.sendMessage(
        context,
        "549$phoneNumber",
        message = message
    )
}
