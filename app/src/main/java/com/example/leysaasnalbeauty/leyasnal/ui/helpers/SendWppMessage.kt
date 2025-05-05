package com.example.leysaasnalbeauty.leyasnal.ui.helpers

import android.content.Context
import java.io.File

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

fun sendPdfFile(context:Context, pdfFile: File) {
    val wppSender = getWhatsAppSenderAuto(
        context = context
    )

    wppSender.sendPdf(context, pdfFile)
}
