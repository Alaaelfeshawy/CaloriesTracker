package com.example.caloriestracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.caloriestracker.navigation.navigateTo
import com.example.caloriestracker.ui.theme.CaloriesTrackerTheme
import com.example.core.navigation.Route
import com.example.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloriesTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Route.WELCOME ){
                        composable(Route.WELCOME){
                            WelcomeScreen{
                                navController.navigateTo(it)
                            }
                        }
                        composable(Route.AGE){
                        }
                        composable(Route.ACTIVITY){
                        }
                        composable(Route.GENDER){
                        }
                        composable(Route.GOAL){
                        }
                        composable(Route.WEIGHT){
                        }
                        composable(Route.NUTRIENT_GOAL){
                        }
                        composable(Route.SEARCH){
                        }
                        composable(Route.TRACKER_OVERVIEW){
                        }
                        composable(Route.HEIGHT){
                        }
                    }
                }
            }
        }
    }
}
