package com.example.leysaasnalbeauty.leyasnal.ui.helper

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import java.net.URLEncoder
import androidx.core.net.toUri

interface WhatsAppSender {
    fun sendMessage(context: Context, phoneNumber: String, message: String)
}

class WhatsAppSenderImplementation : WhatsAppSender {
    override fun sendMessage(context: Context, phoneNumber: String, message: String) {
        try {
            val uri =
                "https://wa.me/$phoneNumber?text=${URLEncoder.encode(message, "UTF-8")}".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.whatsapp")
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "No se pudo abrir WhatsApp", Toast.LENGTH_SHORT).show()
        }
    }
}

class WhatsAppBusinessSenderImplementation : WhatsAppSender {
    override fun sendMessage(context: Context, phoneNumber: String, message: String) {
        try {
            val uri =
                "https://wa.me/$phoneNumber?text=${URLEncoder.encode(message, "UTF-8")}".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.whatsapp.w4b")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "No se pudo abrir WhatsApp", Toast.LENGTH_SHORT).show()
        }
    }
}

class EmptyWhatsAppSender : WhatsAppSender {
    override fun sendMessage(context: Context, phoneNumber: String, message: String) {
    }
}