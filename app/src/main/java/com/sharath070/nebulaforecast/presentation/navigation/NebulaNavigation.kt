package com.sharath070.nebulaforecast.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sharath070.nebulaforecast.MainActivity
import com.sharath070.nebulaforecast.presentation.screens.main.MainScreen
import com.sharath070.nebulaforecast.presentation.screens.SplashScreen
import com.sharath070.nebulaforecast.presentation.screens.search.SearchScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NebulaNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SplashScreen.name) {

        composable(route = Screens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(route = Screens.SplashScreen.name) {
            MainScreen()
        }
        
        composable(route = Screens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
    }
}