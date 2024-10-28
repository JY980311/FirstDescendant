package com.jjy9811.thefirstdescendantlink.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jjy9811.thefirstdescendantlink.screen.user_info.UserDescendantInfoScreen
import com.jjy9811.thefirstdescendantlink.screen.user_info.UserExternalInfoScreen
import com.jjy9811.thefirstdescendantlink.screen.user_info.UserMainScreen
import com.jjy9811.thefirstdescendantlink.screen.user_info.UserReactorInfoScreen
import com.jjy9811.thefirstdescendantlink.screen.user_info.UserWeaponInfoScreen
import com.jjy9811.thefirstdescendantlink.screen.viewmodel.UserScreenViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    val viewModel = UserScreenViewModel(context = LocalContext.current)

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