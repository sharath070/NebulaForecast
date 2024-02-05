package com.sharath070.nebulaforecast.presentation.screens.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sharath070.nebulaforecast.R
import com.sharath070.nebulaforecast.presentation.widgets.ActionButtons
import com.sharath070.nebulaforecast.presentation.widgets.DrawerItem
import com.sharath070.nebulaforecast.ui.theme.appBlue
import com.sharath070.nebulaforecast.ui.theme.btnBlue
import com.sharath070.nebulaforecast.ui.theme.cardBg
import com.sharath070.nebulaforecast.ui.theme.poppins
import com.sharath070.nebulaforecast.ui.theme.poppinsMed
import com.sharath070.nebulaforecast.ui.theme.primaryBg
import com.sharath070.nebulaforecast.ui.theme.textGray
import com.sharath070.nebulaforecast.ui.theme.textWhite
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showSystemUi = true)
@Composable
fun MainScreen() {

    /*    val viewModel: MainScreenViewModel = hiltViewModel()
    //
    //    val currentLocation = viewModel.locationState.collectAsState().value
    //    val locality = viewModel.locality.collectAsState().value
    //    val weather = viewModel.weather.collectAsState().value*/

    val drawerComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.sun))
    val drawerProgress by animateLottieCompositionAsState(
        composition = drawerComposition,
        iterations = LottieConstants.IterateForever
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val mainComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.clear_day))
    val mainProgress by animateLottieCompositionAsState(
        composition = drawerComposition,
        iterations = LottieConstants.IterateForever
    )


    Surface(Modifier.fillMaxSize()) {
        ModalNavigationDrawer(
            drawerContent = {
                BoxWithConstraints {
                    ModalDrawerSheet(Modifier.width(maxWidth * 0.9f)) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(cardBg)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 30.dp)
                                    .padding(top = 20.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                LottieAnimation(
                                    composition = drawerComposition,
                                    progress = { drawerProgress },
                                    modifier = Modifier.size(100.dp)
                                )
                                Column {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Udupi",
                                            color = Color.White,
                                            fontFamily = poppinsMed,
                                            fontSize = 22.sp
                                        )
                                        Icon(
                                            imageVector = Icons.Outlined.LocationOn,
                                            contentDescription = "Location Icon",
                                            tint = appBlue
                                        )
                                    }
                                    Text(
                                        text = "31 / 24",
                                        color = textWhite,
                                        fontFamily = poppins,
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(vertical = 3.dp)
                                    )
                                    Text(
                                        text = "Sun 03, Oct 2021",
                                        color = textWhite,
                                        fontFamily = poppins,
                                        fontSize = 16.sp
                                    )
                                }
                            }

                            Divider(
                                modifier = Modifier
                                    .height(1.dp)
                                    .background(textGray)
                            )
                            Text(
                                text = "Location Management",
                                color = Color.White,
                                fontFamily = poppinsMed,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .padding(top = 25.dp, bottom = 15.dp)
                            )
                            DrawerItem(icon = Icons.Default.Search, text = "Search Location")
                            DrawerItem(icon = Icons.Default.Bookmarks, text = "Saved Location")

                            Divider(
                                modifier = Modifier
                                    .padding(bottom = 25.dp, top = 20.dp)
                                    .height(1.dp)
                                    .background(textGray)
                            )
                            DrawerItem(icon = Icons.Outlined.Share, text = "Share your weather")
                            DrawerItem(icon = Icons.Outlined.Settings, text = "Settings")
                            DrawerItem(icon = Icons.Outlined.Feedback, text = "Feedbacks")
                            DrawerItem(icon = Icons.Outlined.Info, text = "About Us")
                        }
                    }
                }
            },
            drawerState = drawerState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(primaryBg)
                    .padding(top = 15.dp)
                    .padding(horizontal = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ActionButtons(icon = Icons.Rounded.Menu, description = "Menu Button") {
                        scope.launch { drawerState.open() }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Udupi",
                            fontSize = 30.sp,
                            color = textWhite,
                            fontFamily = poppinsMed
                        )
                        Text(
                            text = "Sun 03 Oct",
                            fontSize = 18.sp,
                            color = textWhite,
                            fontFamily = poppins,
                            //modifier = Modifier.offset(y = (-5).dp)
                        )
                    }
                    ActionButtons(icon = Icons.Rounded.Refresh, description = "Refresh weather") {

                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LottieAnimation(
                            composition = mainComposition,
                            progress = { mainProgress },
                            modifier = Modifier.size(140.dp)
                        )
                        Text(
                            text = "CLEAR",
                            fontSize = 30.sp,
                            color = textWhite,
                            fontFamily = poppins
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Today",
                            fontSize = 30.sp,
                            color = textWhite,
                            fontFamily = poppins,
                            modifier = Modifier.offset(x = (-7).dp)
                        )
                        GradientStyleCurrentTemp()
                        Text(
                            text = "Max : 20°",
                            fontSize = 18.sp,
                            color = textWhite,
                            fontFamily = poppins,
                            modifier = Modifier.offset(y = (-25).dp)
                        )
                        Text(
                            text = "Min : 20°",
                            fontSize = 18.sp,
                            color = textWhite,
                            fontFamily = poppins,
                            modifier = Modifier.offset(y = (-25).dp)
                        )
                    }
                }

                BoxWithConstraints(Modifier.fillMaxWidth()) {
                    val axisLength = maxWidth - 30.dp
                    LineChartCompose(maxWidth)
                }

            }
        }
    }
}

@Composable
fun LineChartCompose(axisLength: Dp) {


}

@Composable
private fun GradientStyleCurrentTemp() {
    Row {
        Text(
            text = "29",
            fontSize = 80.sp,
            fontFamily = poppinsMed,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    val brush = Brush.verticalGradient(listOf(btnBlue, appBlue))
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush, blendMode = BlendMode.SrcAtop)
                    }
                }
        )
        Text(
            text = "°",
            fontSize = 50.sp,
            fontFamily = poppinsMed,
            color = textWhite,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    val brush = Brush.verticalGradient(
                        listOf(btnBlue, appBlue)
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush, blendMode = BlendMode.SrcAtop)
                    }
                }
        )
    }
}

