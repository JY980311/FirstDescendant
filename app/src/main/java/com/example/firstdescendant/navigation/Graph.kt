package com.example.firstdescendant.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firstdescendant.screen.user_info.UserBasicInfoScreen
import com.example.firstdescendant.screen.user_info.UserDescendantInfoScreen
import com.example.firstdescendant.screen.user_info.UserExternalInfoScreen
import com.example.firstdescendant.screen.user_info.UserMainScreen
import com.example.firstdescendant.screen.user_info.UserReactorInfoScreen
import com.example.firstdescendant.screen.user_info.UserWeaponInfoScreen
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    val viewModel = TestScreenViewModel()

    NavHost(
        navController = navController,
        startDestination = MAINSCREEN_ROUTE
    ) {
        composable(MAINSCREEN_ROUTE){
            UserMainScreen(
                navHostController = navController,
                viewModel = viewModel
            )
        }
        composable(BASICINFOSCREEN_ROUTE){
            UserBasicInfoScreen(viewModel = viewModel)
        }
        composable(DESCENDANTINFOSCREEN_ROUTE){
            UserDescendantInfoScreen(viewModel = viewModel)
        }
        composable(WEAPONINFOSCREEN_ROUTE){
            UserWeaponInfoScreen(viewModel = viewModel)
        }
        composable(REACTORINFOSCREEN_ROUTE){
            UserReactorInfoScreen(viewModel = viewModel)
        }
        composable(EXTERNALINFOSCREEN_ROUTE){
            UserExternalInfoScreen(viewModel = viewModel)
        }
    }
}