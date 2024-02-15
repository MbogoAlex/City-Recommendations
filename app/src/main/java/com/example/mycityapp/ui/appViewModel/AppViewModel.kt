package com.example.mycityapp.ui.appViewModel

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.Datasource
import com.example.mycityapp.model.JointType
import com.example.mycityapp.ui.state.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(value = AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun initializeApp() {
        val jointType = JointType.COFFEE_SHOP
        val recommendations = Datasource.recommendations.groupBy {
            it.jointType
        }
        _uiState.value = AppUiState(
            recommendations = recommendations,
            jointType = jointType,
            showSelection = false
        )
    }

    init {
        initializeApp()
    }

    fun showRecommendationDetails(recommendationId : Long) {
        _uiState.update {
            it.copy(
                currentSelection = Datasource.recommendations.first { recommendation ->
                    recommendation.id == recommendationId
                },
                showSelection = true
            )
        }
    }

    fun showHomeScreen() {
        _uiState.update {
            it.copy(
                showSelection = false
            )
        }
    }

    fun changeRecommendationsToDisplay(jointType: JointType) {
        _uiState.update {
            it.copy(
                jointType = jointType,
            )
        }
    }
}