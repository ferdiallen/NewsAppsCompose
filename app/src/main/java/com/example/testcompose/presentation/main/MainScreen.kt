package com.example.testcompose.presentation.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private val tagListName = listOf("All", "Games", "Sports", "Technology")

@Composable
fun MainScreen() {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(12.dp))
        HeaderRow()
        Spacer(modifier = Modifier.height(24.dp))
        MiddleScreen()
    }
}

@Composable
fun MiddleScreen() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(0.9F),
            shape = RoundedCornerShape(32.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray.copy(alpha = 0.3F),
                unfocusedIndicatorColor = Color.LightGray.copy(0.5F),
                focusedIndicatorColor = Color.Transparent
            ), placeholder = {
                Text(text = "Search an article...")
            }
        )
        Spacer(modifier = Modifier.height(14.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 18.dp, end = 12.dp)
        ) {
            items(tagListName) { out ->
                TagList(
                    textTag = out,
                    imageAddress = "https://cdn.kibrispdr.org/data/799/playstation-5-logo-png-50.png"
                )
            }
        }

    }
}

@Composable
fun HeaderRow() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://pbs.twimg.com/media/FNflH0HXIAIuvg5.jpg",
            contentDescription = "", modifier = Modifier
                .size(55.dp)
                .clip(
                    CircleShape
                ), contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(2F)) {
            Text(text = "Welcome Back !", color = Color.Gray)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Mikazuki !",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
                .clickable { }, contentAlignment = Alignment.Center
        ) {
            BadgedBox(badge = {
                Badge {
                    Text(text = "20")
                }
            }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notification area"
                )
            }
        }
    }
}

@Composable
fun TagList(
    textTag: String,
    backgroundColor: Color? = Color.White,
    imageAddress: String
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(32.dp))
            .background(backgroundColor ?: Color.White)
            .clickable {
                Toast
                    .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                    .show()
            }) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageAddress,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = textTag,
                color = if (backgroundColor != Color.White) Color.White else Color.Gray
            )
        }
    }
}