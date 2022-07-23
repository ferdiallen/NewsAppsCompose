package com.example.testcompose.presentation.main

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.testcompose.core.navigation.NavigationSealedClass
import com.example.testcompose.utils.ImageRequestLoader
import com.example.testcompose.utils.fonts

private val tagListName = listOf("All", "Games", "Sports", "Technology")

@Composable
fun MainScreen(
    controller: NavController,
    vm: MainScreenViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    paddingContent: PaddingValues
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val focusRequest = LocalFocusManager.current
    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color.White, Color.Gray.copy(alpha = 0.6F)), startY = 800F
                )
            )
            .padding(paddingContent)
            .clickable(indication = null, interactionSource = interactionSource, onClick = {
                focusRequest.clearFocus()
            })
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        HeaderRow()
        Spacer(modifier = Modifier.height(24.dp))
        MiddleScreen(vm, controller)
    }

}

@Composable
private fun MiddleScreen(vm: MainScreenViewModel, controller: NavController) {
    Column(
        Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = vm.searchNews,
            onValueChange = vm::onNewsSearchChange,
            modifier = Modifier.fillMaxWidth(0.9F),
            shape = RoundedCornerShape(32.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray.copy(alpha = 0.3F),
                unfocusedIndicatorColor = Color.LightGray.copy(0.5F),
                focusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "Search an article...",
                    fontFamily = fonts,
                    fontWeight = FontWeight.Light
                )
            }, singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
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
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                LazyRow(
                    Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(start = 20.dp, end = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(10) {
                        NewsRowItem(
                            image = "https://assets.weforum.org/article/image/large_jOJFpEYTByvUuTqpYN0TACiFchfnKOBhvcYv_W2nK_s.jpg",
                            tag = "Technology",
                            title = "Worlds most futuristic companies yet",
                            author = "Peter G.",
                            createdAt = "10-07-2022",
                            userImage = "https://pbs.twimg.com/profile_images/1381981120/petergriffinbh9_400x400.jpg"
                        ) {
                            controller.navigate(NavigationSealedClass.ReadMenu.route)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recommendation",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(
                            1F
                        )
                    )
                    Text(
                        text = "See More",
                        color = Color.Blue.copy(alpha = 0.6F),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(10) {
                RecommendationList(
                    image = "https://asset.kompas.com/crops/N" +
                            "9ypwWuI3HHx9vpt0uXM8trm42k=/0x0:594x396/750x500/data/photo/" +
                            "2021/09/07/6136d27bef422.jpg",
                    tag = "Technology",
                    title = "Ps5 Massive production in progress oakwokwokwaokwdaowokdoawd",
                    createdAt = "7-5-2021"
                ) {
                    controller.navigate(NavigationSealedClass.ReadMenu.route)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun HeaderRow() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://cms-assets.tutsplus.com/uploads/users/1499/posts/29820/preview_image/kotlin.jpg")
                .crossfade(500).build(),
            contentDescription = "",
            modifier = Modifier
                .size(55.dp)
                .clip(
                    CircleShape
                ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(2F)) {
            Text(
                text = "Welcome Back !", color = Color.LightGray, fontWeight = FontWeight.SemiBold,
                fontFamily = fonts
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Mikazuki !",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                fontFamily = fonts
            )
        }
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
                .clickable { },
            contentAlignment = Alignment.Center
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
private fun TagList(
    textTag: String,
    backgroundColor: Color? = Color.LightGray.copy(alpha = 0.5F),
    imageAddress: String
) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(32.dp))
        .background(color = backgroundColor as Color)
        .clickable {

        }) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageAddress, contentDescription = "", modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = textTag,
                color = if (backgroundColor != Color.LightGray.copy(alpha = 0.5F))
                    Color.White else Color.Gray,
                fontFamily = fonts, fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
private fun NewsRowItem(
    image: String, tag: String, title: String, author: String, createdAt: String, userImage: String,
    onClick: () -> Unit
) {
    var cardSize by remember {
        mutableStateOf(150.dp)
    }
    val animateSize = animateDpAsState(cardSize, animationSpec = tween(300))
    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.clickable {
        onClick.invoke()
    }) {
        val context = LocalContext.current
        Column(
            Modifier
                .width(260.dp)
                .height(animateSize.value)
        ) {
            Box {
                AsyncImage(
                    model = ImageRequestLoader.requestImage(image, context),
                    contentDescription = "Image News",
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Box(
                    contentAlignment = Alignment.TopEnd,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, end = 10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .clip(CircleShape)
                    ) {
                        Row(modifier = Modifier.padding(start = 14.dp, end = 14.dp)) {
                            Text(
                                text = "Hot",
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                if (cardSize != 300.dp)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Black.copy(0.2F),
                                        Color.Black
                                    )
                                )
                            )
                            .align(Alignment.BottomStart),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            Modifier
                                .fillMaxWidth(0.8F)
                                .padding(start = 12.dp, end = 4.dp, bottom = 8.dp),
                            textAlign = TextAlign.Start,
                            fontSize = 17.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Light, color = Color.White,
                            fontFamily = fonts
                        )
                        IconButton(onClick = {
                            cardSize = if (cardSize != 300.dp) 300.dp else 150.dp
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIos,
                                contentDescription = "show more",
                                modifier = Modifier
                                    .size(17.dp)
                                    .padding(bottom = 2.dp)
                                    .rotate(-90F), tint = Color.White
                            )
                        }
                    }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = tag,
                modifier = Modifier.padding(start = 12.dp),
                color = Color.LightGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fonts
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                Modifier.padding(start = 12.dp, end = 8.dp),
                textAlign = TextAlign.Start,
                fontSize = 17.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts
            )
            Spacer(modifier = Modifier.height(14.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequestLoader.requestImage(userImage, context),
                    contentDescription = "user posted image",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(
                            CircleShape
                        )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = author, fontWeight = FontWeight.Bold,
                    fontFamily = fonts
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = createdAt, fontWeight = FontWeight.SemiBold, color = Color.LightGray,
                    fontFamily = fonts
                )
                IconButton(onClick = {
                    cardSize = if (cardSize != 300.dp) 300.dp else 150.dp
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIos,
                        contentDescription = "show more",
                        modifier = Modifier
                            .size(17.dp)
                            .padding()
                            .rotate(90F), tint = Color.Black
                    )
                }
            }
        }
    }

}

@Composable
private fun RecommendationList(
    image: String, tag: String, title: String, createdAt: String, onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9F)
            .clickable {
                onClick.invoke()
            }, shape = RoundedCornerShape(8.dp)
    ) {
        Row {
            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .size(85.dp)
                    .padding(3.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.padding(top = 8.dp)) {
                Row(modifier = Modifier.padding(end = 12.dp)) {
                    Text(
                        text = tag, color = Color.LightGray, modifier = Modifier.weight(1F),
                        fontFamily = fonts, fontWeight = FontWeight.Light
                    )
                    Text(
                        text = createdAt, color = Color.LightGray,
                        fontFamily = fonts, fontWeight = FontWeight.Light
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    title,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(end = 12.dp),
                    fontFamily = fonts
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}