package com.sharath070.nebulaforecast.presentation.screens.testScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Preview(showSystemUi = true)
@Composable
fun TestScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp)
    ) {
        val chartEntryModel = entryModelOf(7.7,7.1,6.5,6.1,5.6,5.5,6.0,6.0,6.4,6.9,7.5,8.2,8.3,8.2,8.0,7.8,7.7,7.7,7.7,7.6,7.5,7.4,7.3,7.6)
        Chart(
            chart = lineChart(),
            model = chartEntryModel,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
        )
    }
}
val list = listOf(7.7,7.1,6.5,6.1,5.6,5.5,6.0,6.0,6.4,6.9,7.5,8.2,8.3,8.2,8.0,7.8,7.7,7.7,7.7,7.6,7.5,7.4,7.3,7.6)