package com.example.testcompose.presentation.read

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ReadScreen(controller: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 12.dp, end = 12.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
                    .clickable {
                        controller.popBackStack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.padding(start = 8.dp),
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = "Back action to menu area"
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
                    .clickable {
                        controller.popBackStack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Link,
                    contentDescription = "Back action to menu area"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
                    .clickable {
                        controller.popBackStack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Back action to menu area"
                )
            }
        }
    }
}