package com.example.mycityapp.ui.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycityapp.R
import com.example.mycityapp.data.Datasource
import com.example.mycityapp.model.CityRecommendation

@Composable
fun RecommendationDetails(
    recommendation: CityRecommendation,
    onBackButtonClicked: () -> Unit,
    showBackButton: Boolean,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackButtonClicked()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        top = 20.dp,
                        bottom = 20.dp
                    )
            ) {
                if(showBackButton) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to previous screen",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onBackButtonClicked()
                            }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Text(
                    text = stringResource(id = R.string.recommendation),
                    fontSize = 20.sp
                )
            }
            Box {
                Image(
                    painter = painterResource(id = recommendation.image),
                    contentDescription = stringResource(id = recommendation.name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Text(
                    text = stringResource(id = recommendation.name),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(
                            start = 10.dp,
                            bottom = 10.dp
                        )
                )
            }

            Text(
                text = stringResource(id = recommendation.description),
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.End)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecommendationDetailsPreview(
    recommendation: CityRecommendation = Datasource.recommendations[0],
    modifier: Modifier = Modifier
) {
    RecommendationDetails(
        onBackButtonClicked = {},
        showBackButton = true,
        recommendation = recommendation,
    )
}