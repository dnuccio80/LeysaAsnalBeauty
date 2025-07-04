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
    data object FidelitySystem : Routes("fidelitySystem")
    data object LoyaltyClientList : Routes("loyaltyClientList")
    data object AddLoyaltyPoints : Routes("addLoyaltyPoints/{clientId}") {
        fun createRoute(clientId:Int) = "addLoyaltyPoints/$clientId"
    }
    data object ChangePointsClientList : Routes("changePointsClientList")
    data object ChangeLoyaltyPointsScreen : Routes("changeLoyaltyPoints/{clientId}") {
        fun createRoute(clientId: Int) = "changeLoyaltyPoints/$clientId"
    }
    data object AddRewardPointsToService : Routes("addRewardPointsToService")
    data object EditRewardPointsToService : Routes("editRewardPointsToService/{loyaltyId}") {
        fun createRoute(loyaltyId: Int) = "editRewardPointsToService/$loyaltyId"
    }
    data object AddRewardLoyalty : Routes("addRewardLoyalty")
    data object EditRewardLoyalty : Routes("editRewardLoyalty/{loyaltyId}") {
        fun createRoute(loyaltyId: Int) = "editRewardLoyalty/$loyaltyId"
    }


}
