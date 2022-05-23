package com.smit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.smit.model.ApiResult
import com.smit.model.IGUsers
import com.smit.ui.theme.AndroidfirebaseTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidfirebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                      //ProcessMeter()
                     //GridTest()
                    //AnimateApp()
                    ApiTesting()

                }
            }
        }
    }
}


@Composable
fun ApiTesting(){
    val client = HttpClient (CIO){
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    var users = remember {
        mutableListOf<IGUsers>()
    }
    runBlocking {
        val response:ApiResult = client.get("https://instagram47.p.rapidapi.com/search?") {
            header(
                key = "X-RapidAPI-Host",
                value = "instagram47.p.rapidapi.com"
            )
            header(
                key = "X-RapidAPI-Key",
                value = "b24b748452mshea7538628fc5d80p111264jsn14da7a60bb71"
            )
            parameter(key = "search", "smit.0110")
        }.body()
        users = response.body?.users ?: mutableListOf()
    }

    LazyColumn (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        contentPadding = PaddingValues(top = 50.dp, bottom = 10.dp),
        modifier = Modifier.background(Color.Cyan)){

        items(users) {

            // val constraintsScope = rememberCoroutineScope()
            Text(text = it.user?.username ?: "no name")
            Image(painter = rememberAsyncImagePainter(it.user?.profilePicUrl ?: ""), contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop)


        }
    }

}

@Composable
fun AnimateApp(){
    val color = remember{
        Animatable(Color.Black)
    }
    val textColor = remember {
        Animatable(Color.White)
    }
    LaunchedEffect(key1 = color ){
         launch {
             color.animateTo(Color.White, animationSpec = infiniteRepeatable(animation = tween(6000, easing = FastOutSlowInEasing)))
            // textColor.animateTo(Color.Black, animationSpec = tween(6000, easing = FastOutSlowInEasing) )
         }
        launch {
            textColor.animateTo(Color.Black, animationSpec = infiniteRepeatable(animation = tween(6000, easing = FastOutSlowInEasing)))
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color.value)) {
        Text(text = "Hey how are ypu.", style = TextStyle(fontSize = 30.sp, color = textColor.value))
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridTest(){
    LazyVerticalGrid(cells = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(all = 20.dp),
        modifier = Modifier
            .background(Color.Black)){
            items(30){
                Box(modifier = Modifier
                    .padding(4.dp)
                    .size(50.dp)
                    .background(Color.Blue))
            }
    }
}

@Composable
fun ProcessMeter() {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(600.dp)
            .background(brush = Brush.verticalGradient(listOf(Color.Blue, Color.Cyan)))
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .drawBehind {
                    drawArc(
                        color = Color.White,
                        sweepAngle = 360f,
                        startAngle = -90f,
                        useCenter = false,
                        //    size = size/1.25f,
                        style = Stroke(width = 60f, cap = StrokeCap.Round),
                    )
                    drawArc(
                        brush = Brush.verticalGradient(listOf(Color.Black, Color.Red)),
                        sweepAngle = 280f,
                        startAngle = -90f,
                        useCenter = false,
                        //    size = size/1.25f,
                        alpha = 0.7f,
                        style = Stroke(width = 60f, cap = StrokeCap.Round),
                    )

                }
                .size(100.dp)) {
            Text(
                text = "800",
                style = TextStyle(Color.White, fontSize = 20.sp, fontFamily = FontFamily.Monospace)
            )
        }
        Column(modifier = Modifier
            .drawBehind {
                drawArc(
                    brush = Brush.verticalGradient(listOf(Color.Black, Color.Red)),
                    sweepAngle = 280f,
                    startAngle = -90f,
                    useCenter = false,
                    //    size = size/1.25f,
                    style = Stroke(width = 60f, cap = StrokeCap.Round),
                )
            }
            .size(100.dp)) {}
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidfirebaseTheme {

    }
}