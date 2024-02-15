package com.example.mycityapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityapp.R
import com.example.mycityapp.model.CityRecommendation
import com.example.mycityapp.model.JointType
import com.example.mycityapp.ui.appViewModel.AppViewModel
import com.example.mycityapp.ui.state.AppUiState
import com.example.mycityapp.ui.utils.NavigationContent
import com.example.mycityapp.ui.utils.WindowType

@Composable
fun HomeScreen(
    windowType: WindowType,
    appUiState: AppUiState,
    onItemClicked: (recommendationId: Long) -> Unit,
    onBackButtonClicked: () -> Unit,
    onTabClicked: (joinType: JointType) -> Unit,
    modifier: Modifier = Modifier,
) {

    val navigationContentList = listOf<NavigationContent>(
        NavigationContent(
            R.string.coffee_shops,
            icon = painterResource(id = R.drawable.coffee),
            JointType.COFFEE_SHOP
        ),
        NavigationContent(
            R.string.restaurants,
            icon = painterResource(id = R.drawable.baseline_restaurant_24),
            JointType.RESTAURANT
        ),
        NavigationContent(
            R.string.kid_friendly_places,
            icon = painterResource(id = R.drawable.kid_friendly),
            JointType.KID_FRIENDLY_PLACE
        ),
        NavigationContent(
            R.string.parks,
            icon = painterResource(id = R.drawable.park),
            JointType.PARK
        ),
        NavigationContent(
            R.string.shopping_centers,
            icon = painterResource(id = R.drawable.shopping),
            JointType.SHOPPING_CENTER
        ),
    )

    if(windowType == WindowType.COMPACT) {
        if(appUiState.showSelection) {
            RecommendationDetails(
                recommendation = appUiState.currentSelection,
                onBackButtonClicked = { onBackButtonClicked() },
                showBackButton = true
            )
        } else {
            Column {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = appUiState.jointType.toString(),
                        fontSize = 26.sp
                    )
                }
                ShowRecommendations(
                    onItemClicked = onItemClicked,
                    appUiState = appUiState,
                    modifier = Modifier
                        .weight(1f)
                )
                BottomNavigationBar(
                    appUiState = appUiState,
                    navigationContentList = navigationContentList,
                    onTabClicked = onTabClicked,

                    )
            }
        }
    } else if (windowType == WindowType.MEDIUM) {
        Row {
            AppNavigationRail(
                appUiState = appUiState,
                navigationContentList = navigationContentList,
                onTabClicked = onTabClicked
            )
            ShowRecommendations(
                onItemClicked = onItemClicked,
                appUiState = appUiState,
                modifier = Modifier
                    .weight(1f)
            )
            RecommendationDetails(
                recommendation = appUiState.currentSelection,
                onBackButtonClicked = { onBackButtonClicked() },
                showBackButton = false,
                modifier = Modifier
                    .weight(1f)
            )
        }
    } else if (windowType == WindowType.EXPANDED) {

            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet {
                        NavigationDrawerContent(
                            appUiState = appUiState,
                            navigationContentList = navigationContentList,
                            onTabClicked = onTabClicked,
                            modifier = Modifier
                                .padding(
                                    top = 10.dp
                                )
                        )
                    }
                })
            {
                Row {
                    ShowRecommendations(
                        onItemClicked = onItemClicked,
                        appUiState = appUiState,
                        modifier = Modifier
                            .weight(1f)
                    )
                    RecommendationDetails(
                        recommendation = appUiState.currentSelection,
                        onBackButtonClicked = { onBackButtonClicked() },
                        showBackButton = false,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }

    }


}

@Composable
fun ShowRecommendations(
    appUiState: AppUiState,
    onItemClicked: (recommendationId: Long) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()

    ) {
        items(appUiState.recommendationsToDisplay) { recommendation ->
            RecommendationItem(
                recommendation = recommendation,
                onItemClicked = onItemClicked,
                modifier = Modifier
                    .padding(
                        start = 15.dp,
                        top = 10.dp,
                        end = 15.dp
                    )
            )
        }
    }
}

@Composable
fun RecommendationItem(
    onItemClicked: (recommendationId: Long) -> Unit,
    recommendation: CityRecommendation,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable {
                onItemClicked(recommendation.id)
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),

            modifier = Modifier
//                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = recommendation.image),
                contentDescription = stringResource(id = recommendation.name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)

            )
            Column(
                modifier = Modifier
                    .padding(
                        end = 20.dp
                    )

            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = recommendation.name),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(id = recommendation.description),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    appUiState: AppUiState,
    navigationContentList: List<NavigationContent>,
    onTabClicked: (joinType: JointType) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        for(navigationItem in navigationContentList) {
            NavigationBarItem(
                selected = appUiState.jointType == navigationItem.jointType,
                onClick = { onTabClicked(navigationItem.jointType) },
                icon = { 
                    Icon(
                        painter = navigationItem.icon, 
                        contentDescription = stringResource(id = navigationItem.contentDescription)
                    )
                }
            )
        }
    }
}

@Composable
fun AppNavigationRail(
    appUiState: AppUiState,
    navigationContentList: List<NavigationContent>,
    onTabClicked: (joinType: JointType) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail {
        for(navigationItem in navigationContentList) {
            NavigationRailItem(
                selected = appUiState.jointType == navigationItem.jointType,
                onClick = { onTabClicked(navigationItem.jointType) },
                icon = {
                    Icon(
                        painter = navigationItem.icon,
                        contentDescription = stringResource(id = navigationItem.contentDescription)
                    )
                }
            )
        }
    }
}

@Composable
fun NavigationDrawerContent(
    appUiState: AppUiState,
    navigationContentList: List<NavigationContent>,
    onTabClicked: (joinType: JointType) -> Unit,
    modifier: Modifier = Modifier
) {
    for(navItem in navigationContentList) {
        NavigationDrawerItem(
            label = {
                    Text(text = stringResource(id = navItem.contentDescription))
            },
            selected = appUiState.jointType == navItem.jointType,
            onClick = { onTabClicked(navItem.jointType) },
            icon = {
                Icon(
                    painter = navItem.icon,
                    contentDescription = stringResource(id = navItem.contentDescription)
                )
            },
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyCityAppCompactReview(
    modifier: Modifier = Modifier
) {
    val viewModel: AppViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    HomeScreen(
        windowType = WindowType.COMPACT,
        appUiState = uiState,
        onItemClicked = {},
        onBackButtonClicked = { /*TODO*/ },
        onTabClicked = {}
    )
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun MyCityAppMediumReview(
    modifier: Modifier = Modifier
) {
    val viewModel: AppViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    HomeScreen(
        windowType = WindowType.MEDIUM,
        appUiState = uiState,
        onItemClicked = {},
        onBackButtonClicked = { /*TODO*/ },
        onTabClicked = {}
    )
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun MyCityAppExpandedReview(
    modifier: Modifier = Modifier
) {
    val viewModel: AppViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    HomeScreen(
        windowType = WindowType.EXPANDED,
        appUiState = uiState,
        onItemClicked = {},
        onBackButtonClicked = { /*TODO*/ },
        onTabClicked = {}
    )
}