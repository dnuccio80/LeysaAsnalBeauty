package com.example.leysaasnalbeauty.leyasnal.data

import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Clients : Routes("clients")
    data object ClientDetails : Routes("clientDetails/{clientId}") {
        fun createRoute(clientId: Int) = "clientDetails/$clientId"
    }

    data object Annotations : Routes("annotations")
    data object AnnotationDetails : Routes("annotationDetails/{annotationId}") {
        fun createRoute(annotationId: Int) = "annotationDetails/$annotationId"
    }
    data object AddAnnotation : Routes("addAnnotation")
    data object GiftCardMaker : Routes("giftCardMaker")
    data object AddClient : Routes("addClient")
    data object NotifyClient : Routes("notifyClient")
    data object ScheduleAppointment : Routes("scheduleAppointment")
    data object SelectDateTimeAppointment : Routes("selectDateTimeAppointment/{clientId}") {
        fun createRoute(clientId: Int) = "selectDateTimeAppointment/$clientId"
    }
    data object ModifyAppointment : Routes("modifyAppointment/{appointmentId}") {
        fun createRoute(appointmentId: Int) = "modifyAppointment/$appointmentId"
    }
    data object AppointmentList : Routes("appointmentList")
}