package com.example.mycityapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CityRecommendation(
    val id: Long,
    @StringRes val name: Int = 0,
    @StringRes val description: Int = 0,
    @DrawableRes val image: Int = 0,
    val jointType: JointType = JointType.RESTAURANT
)
