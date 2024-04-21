package com.example.caloriestracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.caloriestracker.navigation.navigateTo
import com.example.caloriestracker.ui.theme.CaloriesTrackerTheme
import com.example.core.domain.DefaultPreferences
import com.example.core.domain.prefrences.Preferences
import com.example.core.navigation.Route
import com.example.onboarding_presentation.activity.ActivityScreen
import com.example.onboarding_presentation.age.AgeScreen
import com.example.onboarding_presentation.gender.GenderScreen
import com.example.onboarding_presentation.goal.GoalScreen
import com.example.onboarding_presentation.height.HeightScreen
import com.example.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.example.onboarding_presentation.weight.WeightScreen
import com.example.onboarding_presentation.welcome.WelcomeScreen
import com.example.tracker_presentation.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences :  Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloriesTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    Scaffold(
                        scaffoldState = scaffoldState
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize()
                        ) {
                            val navController = rememberNavController()
                            AppNavHost(navController = navController, scaffoldState =scaffoldState , preferences = preferences )
                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun AppNavHost(navController : NavHostController , scaffoldState: ScaffoldState , preferences: Preferences){
    val startDestination = if (!preferences.getShouldLoadOnboarding()){
        Route.TRACKER_OVERVIEW
    } else Route.WELCOME
    NavHost(navController = navController, startDestination = startDestination ){
        composable(Route.WELCOME){
            WelcomeScreen{
                navController.navigateTo(it)
            }
        }
        composable(Route.AGE){
            AgeScreen(
                scaffoldState = scaffoldState,
                navigate = navController::navigateTo)
        }
        composable(Route.ACTIVITY){
            ActivityScreen(navigate = navController::navigateTo)
        }
        composable(Route.GENDER){
            GenderScreen(navigate = navController::navigateTo)
        }
        composable(Route.GOAL){
            GoalScreen(navigate = navController::navigateTo)
        }
        composable(Route.WEIGHT){
            WeightScreen(
                scaffoldState = scaffoldState,
                navigate = navController::navigateTo)
        }
        composable(Route.NUTRIENT_GOAL){
            NutrientGoalScreen(
                scaffoldState = scaffoldState,
                navigate = navController::navigateTo)
        }
        composable(Route.SEARCH){
        }
        composable(Route.TRACKER_OVERVIEW){
            TrackerOverviewScreen()
        }
        composable(Route.HEIGHT){
            HeightScreen(
                scaffoldState = scaffoldState,
                navigate = navController::navigateTo)
        }
    }

}