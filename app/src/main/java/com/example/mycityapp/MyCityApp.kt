package com.example.mycityapp

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityapp.ui.appViewModel.AppViewModel
import com.example.mycityapp.ui.utils.WindowType
import com.example.mycityapp.ui.views.HomeScreen

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: AppViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val windowType: WindowType = when(windowSize) {
        WindowWidthSizeClass.Compact -> {
            WindowType.COMPACT
        }
        WindowWidthSizeClass.Medium -> {
            WindowType.MEDIUM
        }
        WindowWidthSizeClass.Expanded -> {
            WindowType.EXPANDED
        }
        else -> WindowType.COMPACT
    }



    HomeScreen(
        windowType = windowType,
        appUiState = uiState,
        onItemClicked = { viewModel.showRecommendationDetails(it) },
        onBackButtonClicked = { viewModel.showHomeScreen() },
        onTabClicked = { viewModel.changeRecommendationsToDisplay(it) }
    )

}