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
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.screens.ClientDetailsScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.ClientsScreen
import com.example.leysaasnalbeauty.leyasnal.ui.screens.HomeScreen
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
                        composable(Routes.Home.route) { HomeScreen(innerPadding, viewModel) }
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
                    }
                }
            }
        }
    }
}


