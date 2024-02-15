package com.example.mycityapp.ui.state

import com.example.mycityapp.data.Datasource
import com.example.mycityapp.model.CityRecommendation
import com.example.mycityapp.model.JointType

data class AppUiState(
    val recommendations: Map<JointType, List<CityRecommendation>> = emptyMap(),
    val jointType: JointType = JointType.RESTAURANT,
    val currentSelection: CityRecommendation = Datasource.recommendations[0],
    val showSelection: Boolean = false
) {
    val recommendationsToDisplay: List<CityRecommendation> by lazy { recommendations[jointType]!! }
}
