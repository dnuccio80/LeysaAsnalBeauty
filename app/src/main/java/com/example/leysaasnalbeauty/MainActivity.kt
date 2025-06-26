package com.example.leysaasnalbeauty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.leysaasnalbeauty.leyasnal.data.Routes
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.screens.AddAnnotationScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.AddClientScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.AddLoyaltyPointsScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.AddRewardLoyaltyScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.AddRewardPointsToServiceScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.AnnotationsDetailsScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.AnnotationsScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.AppointmentListScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.ChangeLoyaltyPointsScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.ChangePointsClientListScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.ClientDetailsScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.ClientsScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.EditRewardPointsToServiceScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.FidelityClientSystemScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.GiftCardScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.HomeScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.LoyaltyClientListScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.ModifyAppointmentScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.NotifyClientScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.ScheduleAppointmentScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.SelectDateTImeForAppointmentScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.TestScreen
import com.example.leysaasnalbeauty.leyasnal.ui.sections.AppTopBar
import com.example.leysaasnalbeauty.leyasnal.ui.sections.BottomBar
import com.example.leysaasnalbeauty.ui.theme.LeysaAsnalBeautyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LeysaAsnalBeautyTheme {

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { AppTopBar() },
                    bottomBar = { BottomBar(navController) }
                ) { innerPadding ->
                    NavHost(navController, startDestination = Routes.Home.route) {
                        composable(Routes.Home.route) {
                            HomeScreen(
                                innerPadding,
                                viewModel,
                                navController
                            )
                        }
                        composable(Routes.Clients.route) {
                            ClientsScreen(innerPadding, viewModel, onClientClicked = { clientId ->
                                navController.navigate(Routes.ClientDetails.createRoute(clientId))
                            })
                        }
                        composable(
                            Routes.ClientDetails.route,
                            arguments = listOf(navArgument("clientId") {
                                type = NavType.IntType
                            })
                        )
                        { backStackEntry ->
                            ClientDetailsScreen(
                                innerPadding = innerPadding,
                                viewModel = viewModel,
                                clientId = backStackEntry.arguments?.getInt("clientId") ?: 0,
                                onBackButtonClick = {
                                    navController.popBackStack()
                                },
                                onDeleteClient = { clientId ->
                                    viewModel.deleteClient(clientId)
                                }
                            )
                        }
                        composable(Routes.GiftCardMaker.route) {
                            GiftCardScreen(innerPadding, onCancel = {
                                navController.popBackStack()
                            })
                        }
                        composable(Routes.AddClient.route) {
                            AddClientScreen(
                                innerPadding, onAddClient = { client ->
                                    viewModel.addNewClient(client)
                                    navController.popBackStack()
                                },
                                onCancel = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(Routes.NotifyClient.route) {
                            NotifyClientScreen(
                                innerPadding = innerPadding,
                                viewModel = viewModel,
//                                onCancel = {
//                                    viewModel.onQueryChanged("")
//                                    navController.popBackStack()
//                                }
                            )
                        }
                        composable(Routes.Annotations.route) {
                            AnnotationsScreen(
                                innerPadding = innerPadding,
                                viewModel = viewModel,
                                navController
                            )
                        }
                        composable(
                            Routes.AnnotationDetails.route,
                            arguments = listOf(navArgument("annotationId") {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            AnnotationsDetailsScreen(
                                innerPadding = innerPadding,
                                viewModel = viewModel,
                                annotationId = backStackEntry.arguments?.getInt("annotationId")
                                    ?: 0,
                                onBackButtonClicked = {
                                    navController.popBackStack()
                                },
                                onConfirmButtonClicked = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(Routes.AddAnnotation.route) {
                            AddAnnotationScreen(
                                innerPadding = innerPadding,
                                onConfirmButtonClicked = { title, description ->
                                    viewModel.addAnnotation(
                                        title = title,
                                        description = description
                                    )
                                    navController.popBackStack()
                                },
                                onBackButtonClicked = { navController.popBackStack() }
                            )
                        }
                        composable(Routes.ScheduleAppointment.route) {
                            ScheduleAppointmentScreen(
                                innerPadding,
                                viewModel = viewModel,
                                onClientSelected = { clientId ->
                                    navController.navigate(
                                        Routes.SelectDateTimeAppointment.createRoute(
                                            clientId
                                        )
                                    )
                                }
                            )
                        }
                        composable(
                            Routes.SelectDateTimeAppointment.route,
                            arguments = listOf(navArgument("clientId") {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            SelectDateTImeForAppointmentScreen(
                                innerPadding,
                                viewModel,
                                clientId = backStackEntry.arguments?.getInt("clientId") ?: 0,
                                onCancelButtonClicked = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(Routes.AppointmentList.route) {
                            AppointmentListScreen(innerPadding, viewModel, onAppointmentClicked = {
                                navController.navigate(Routes.ModifyAppointment.createRoute(it))
                            })
                        }
                        composable(
                            Routes.ModifyAppointment.route,
                            arguments = listOf(navArgument("appointmentId") {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            ModifyAppointmentScreen(
                                innerPadding = innerPadding,
                                viewModel = viewModel,
                                appointmentId = backStackEntry.arguments?.getInt("appointmentId")
                                    ?: 0,
                                onCancelButtonClicked = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(Routes.FidelitySystem.route) {
                            FidelityClientSystemScreen(
                                innerPadding = innerPadding,
                                viewModel = viewModel,
                                onBackClick = {
                                    navController.navigate(Routes.Home.route)
                                },
                                onAddPointsButtonClicked = {
                                    navController.navigate(Routes.LoyaltyClientList.route)
                                },
                                onChangePointsButtonClicked = {
                                    navController.navigate(Routes.ChangePointsClientList.route)
                                },
                                onAddRewardPointsToService = {
                                    navController.navigate(Routes.AddRewardPointsToService.route)
                                },
                                onServiceClicked = {
                                    navController.navigate(
                                        Routes.EditRewardPointsToService.createRoute(
                                            it
                                        )
                                    )
                                },
                                onAddRewardLoyaltyClicked = {
                                    navController.navigate(Routes.AddRewardLoyalty.route)
                                }
                            )
                        }
                        composable(Routes.LoyaltyClientList.route) {
                            LoyaltyClientListScreen(
                                viewModel,
                                innerPadding,
                                onClientClicked = { clientId ->
                                    navController.navigate(
                                        Routes.AddLoyaltyPoints.createRoute(
                                            clientId
                                        )
                                    )
                                },
                            )
                        }
                        composable(
                            Routes.AddLoyaltyPoints.route,
                            arguments = listOf(navArgument("clientId") {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            AddLoyaltyPointsScreen(
                                viewModel,
                                innerPadding,
                                backStackEntry.arguments?.getInt("clientId") ?: 0,
                                onBackButtonClicked = {
                                    navController.popBackStack()
                                },
                                onConfirm = {
                                    navController.navigate(Routes.FidelitySystem.route)
                                }
                            )
                        }
                        composable(Routes.ChangePointsClientList.route) {
                            ChangePointsClientListScreen(
                                innerPadding,
                                viewModel,
                                onClientClicked = {
                                    navController.navigate(
                                        Routes.ChangeLoyaltyPointsScreen.createRoute(
                                            it
                                        )
                                    )
                                })
                        }
                        composable(
                            Routes.ChangeLoyaltyPointsScreen.route, arguments = listOf(
                                navArgument("clientId") {
                                    type = NavType.IntType
                                }
                            )) { backStackEntry ->
                            ChangeLoyaltyPointsScreen(
                                viewModel = viewModel,
                                innerPadding = innerPadding,
                                clientId = backStackEntry.arguments?.getInt("clientId") ?: 0
                            ) {
                                navController.popBackStack()
                            }
                        }
                        composable(Routes.AddRewardPointsToService.route) {
                            AddRewardPointsToServiceScreen(
                                innerPadding = innerPadding,
                                viewModel = viewModel,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(
                            Routes.EditRewardPointsToService.route, arguments = listOf(
                            navArgument("loyaltyId") {
                                type = NavType.IntType
                            }
                        )) { backStackEntry ->
                            EditRewardPointsToServiceScreen(
                                innerPadding = innerPadding,
                                viewModel = viewModel,
                                onBackClick = { navController.popBackStack() },
                                loyaltyId = backStackEntry.arguments?.getInt("loyaltyId") ?: 0
                            )
                        }
                        composable(Routes.AddRewardLoyalty.route) {
                            AddRewardLoyaltyScreen(
                                innerPadding = innerPadding,
                                viewModel = viewModel,
                                onAcceptClick = {
                                    navController.popBackStack()
                                },
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}


