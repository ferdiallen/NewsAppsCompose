package com.allen.readscreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Share
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.allen.core.util.ImageRequestLoader

@Composable
fun ReadScreen(controller: NavController) {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(12.dp))
        HeaderScreen(controller = controller)
        Spacer(modifier = Modifier.height(18.dp))
        MiddleScreen(
            tag = "Tech",
            title = "Ps5 Massive production in progress",
            author = "Peter G.",
            "10-02-2019",
            authorImage = "https://pbs.twimg.com/profile_images/1381981120/petergriffinbh9_400x400.jpg",
            fullStory = "Lorem Ipsum is simply dummy text " +
                    "of the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text " +
                    "ever since the 1500s, when an unknown printer " +
                    "took a galley of type and scrambled it to make a " +
                    "type specimen book. It has survived not only five " +
                    "centuries, but also the leap into electronic typesetting, " +
                    "remaining essentially unchanged. It was popularised in the " +
                    "1960s with the release of Letraset sheets containing Lorem Ipsum" +
                    " passages, and more recently with desktop publishing software like" +
                    " Aldus PageMaker including versions of Lorem Ipsum." +
                    "It is a long established fact that a reader will be distracted by " +
                    "the readable content of a page when looking at its layout. The point of using" +
                    " Lorem Ipsum is that it has a more-or-less normal distribution of letters, as" +
                    " opposed to using 'Content here, content here', making it look like readable " +
                    "English. Many desktop publishing packages and web page editors now use Lorem I" +
                    "psum as their default model text, and a search for 'lorem ipsum' will uncover " +
                    "many web sites still in their infancy. Various versions have evolved over the " +
                    "years, sometimes by accident, sometimes on purpose (injected humour and the" +
                    " like).",
            newsImage = "https://cms.fin.co.id/uploads/large/8b172f097c147ecd5da3ab8214d3c2f9.jpg"
        )
    }
}


@Composable
private fun HeaderScreen(controller: NavController) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp)
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

@Composable
private fun MiddleScreen(
    tag: String,
    title: String,
    author: String,
    createdAt: String,
    authorImage: String,
    newsImage: String?,
    fullStory: String,
    context: Context = LocalContext.current
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = tag,
            fontSize = 22.sp,
            color = Color.Gray,

            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = title, fontSize = 28.sp, fontWeight = FontWeight.SemiBold, )
        Spacer(modifier = Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequestLoader.requestImage(authorImage, context),
                contentDescription = "user posted image",
                modifier = Modifier
                    .size(30.dp)
                    .clip(
                        CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = author, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(5.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = createdAt, fontWeight = FontWeight.SemiBold, color = Color.LightGray)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                AsyncImage(
                    model = newsImage,
                    contentDescription = "news image",
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = fullStory,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}