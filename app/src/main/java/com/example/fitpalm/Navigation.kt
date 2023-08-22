package com.example.fitpalm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitpalm.Screens.BMI
import com.example.fitpalm.Screens.Calories
import com.example.fitpalm.Screens.Scheduale


@Composable
fun NavigationGraph(navController: NavHostController) {


    NavHost(navController = navController, startDestination = "BMI"){

        composable("BMI"){
            BMI()
        }

        composable("Calories"){
            Calories()
        }

        composable("Scheduale"){
            Scheduale()
        }


    }



}