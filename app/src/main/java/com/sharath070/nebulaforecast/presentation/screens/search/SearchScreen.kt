package com.sharath070.nebulaforecast.presentation.screens.search

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SearchScreen(
    navController: NavController
) {
    val viewModel: SearchScreenViewModel = hiltViewModel()
    val searchText = viewModel.searchText.collectAsState()
    val addressList = viewModel.addressList.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = searchText.value,
            onValueChange = viewModel::onSearchTextChange,
            placeholder = { Text(text = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ){
            items(addressList.value.orEmpty()) {
                Text(
                    text = "${it.city}, ${it.country}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp)
                        .border(width = 1.dp, color = Color.LightGray)
                        .padding(10.dp)
                )
            }
        }
    }
}