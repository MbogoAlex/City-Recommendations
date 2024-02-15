package com.example.mycityapp.ui.utils

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.painter.Painter
import com.example.mycityapp.model.JointType

data class NavigationContent(
    @StringRes val contentDescription: Int,
    val icon: Painter,
    val jointType: JointType,
)
