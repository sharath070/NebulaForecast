package com.sharath070.nebulaforecast.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharath070.nebulaforecast.ui.theme.cardBg
import com.sharath070.nebulaforecast.ui.theme.poppins
import com.sharath070.nebulaforecast.ui.theme.textWhite

@Composable
fun DrawerItem(icon: ImageVector, text: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 15.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = textWhite,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            color = textWhite,
            fontFamily = poppins,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ActionButtons(
    icon: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(cardBg),
        elevation = CardDefaults.cardElevation(7.dp),
        modifier = Modifier.padding(horizontal = 10.dp)
//            .offset(y = (-10).dp)
            .clickable { onClick.invoke() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier
                .padding(4.dp)
                .size(33.dp),
            tint = textWhite
        )
    }
}