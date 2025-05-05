package com.example.leysaasnalbeauty.leyasnal.ui.helpers

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast

fun getWhatsAppName(context: Context, packageName: String): Boolean {
    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(
                packageName,
                PackageManager.PackageInfoFlags.of(0)
            )
        } else {
            @Suppress("DEPRECATION")
            context.packageManager.getPackageInfo(packageName, 0)
        }
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun getWhatsAppSenderAuto(context: Context): WhatsAppSender {
    return when {
        getWhatsAppName(context, "com.whatsapp.w4b") -> WhatsAppBusinessSenderImplementation()
        getWhatsAppName(context, "com.whatsapp") -> WhatsAppSenderImplementation()
        else -> {
            Toast.makeText(context, "No se encontró WhatsApp", Toast.LENGTH_LONG).show()
            EmptyWhatsAppSender()
        }
    }
}