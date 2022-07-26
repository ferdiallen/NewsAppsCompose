package com.example.testcompose.presentation.boarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.testcompose.core.navigation.NavigationSealedClass
import com.example.testcompose.utils.fonts

@Composable
fun BoardingScreen(controller: NavController) {
    Column(Modifier.fillMaxSize()) {
        HeaderScreen()
        Spacer(modifier = Modifier.height(17.dp))
        Divider()
        Spacer(modifier = Modifier.height(20.dp))
        MiddleScreen(controller)
    }
}

@Composable
private fun HeaderScreen() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Some title", fontSize = 25.sp, fontWeight = FontWeight.Light, fontFamily = fonts,
            modifier = Modifier.weight(1F)
        )
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
                .clickable { },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Skip",
                Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                fontFamily = fonts, fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
private fun MiddleScreen(controller: NavController) {
    var selectedItem: Int? by remember {
        mutableStateOf(null)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = "Choose a topic to  start reading",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold, fontFamily = fonts
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp), userScrollEnabled = false
        ) {
            items(9) {
                ItemsTagCategories(
                    name = "World",
                    com.example.testcompose.R.drawable.world, it == selectedItem, id = it
                ) { out ->
                    selectedItem = out
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 22.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    controller.navigate(NavigationSealedClass.MainMenu.route) {
                        popUpTo(controller.currentDestination?.route.toString()) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.29F),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                shape = RoundedCornerShape(24.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 3.dp,
                    pressedElevation = 12.dp
                )
            ) {
                Text(
                    text = "Continue",
                    color = Color.White,
                    fontFamily = fonts,
                    fontWeight = FontWeight.Light, fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun <T> ItemsTagCategories(
    name: String,
    image: T,
    selected: Boolean = false,
    id: Int,
    clicked: (Int) -> Unit
) {
    Card(
        shape = CircleShape,
        modifier = Modifier
            .size(105.dp, height = 120.dp)
            .clip(CircleShape)
            .border(
                if (selected) 2.dp else 1.dp,
                color = if (selected) Color.Blue else Color.Gray,
                CircleShape
            )
            .clickable {
                clicked.invoke(id)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (selected) Color.Blue.copy(0.10F) else Color.Transparent)
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            AsyncImage(model = image, contentDescription = "", modifier = Modifier.size(50.dp))
            Text(text = name, fontFamily = fonts, fontWeight = FontWeight.Light)
        }
    }
}