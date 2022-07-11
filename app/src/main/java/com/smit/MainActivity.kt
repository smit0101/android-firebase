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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.smit.model.ApiResult
import com.smit.model.IGUsers
import com.smit.ui.theme.AndroidfirebaseTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var navController: NavHostController
        setContent {
            AndroidfirebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    navController = rememberNavController()
//                    NavigationGraph(navController = navController)
                    //                   ApiTesting()
                     //AddWebSite()
                    //AnimateApp()
                    ProcessMeter()


                }
            }
        }
    }
}

@Composable
fun AddWebSite(){
    val webView = rememberWebViewState(url = "https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS43LjAiLCJwbGF0Zm9ybSI6ImphdmEiLCJhcmdzIjoiIiwibm9uZU1hcmtlcnMiOnRydWUsInRoZW1lIjoiaWRlYSIsImNvZGUiOiJpbXBvcnQga290bGlueC5jb3JvdXRpbmVzLipcbmltcG9ydCBrb3RsaW4uY29yb3V0aW5lcy4qXG5pbXBvcnQga290bGlueC5jb3JvdXRpbmVzLmZsb3cuKlxuaW1wb3J0IGtvdGxpbi5yZWZsZWN0LipcbmltcG9ydCBrb3RsaW4uaW8uKlxuaW1wb3J0IGphdmEubmlvLmZpbGUuKlxuaW1wb3J0IGphdmEuaW8uKlxuIFxuXG5cbmZ1biBtYWluKCk9cnVuQmxvY2tpbmd7XG4gICAgZmxvdzxJbnQ+e1xuICAgICAgICB2YXIgc3RhcnQgPSAwXG4gICAgICAgIHdoaWxlKHN0YXJ0IDw9IDEwMDApe1xuICAgICAgICAgICAgZW1pdChzdGFydClcbiAgICAgICAgICAgIGVtaXQoc3RhcnQgKyAxKVxuICAgICAgICAgICAgc3RhcnQrK1xuICAgICAgICB9XG4gICAgICAgIFxuICAgIH0uY29sbGVjdCg6OnByaW50bG4pXG59In0=")
    WebView(
        state = webView,
        onCreated = { it.settings.javaScriptEnabled = true }
    )
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HomeScreen.route) {
        composable(route = HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = ScreenA.route,
            arguments = listOf(navArgument(name = "id") {
                type = NavType.IntType
            })
        ) {
            ScreenA(it.arguments?.getInt("id") ?: -1)
        }
    }

}


sealed interface Screen {
    val route: String
}

object HomeScreen : Screen {
    override val route: String
        get() = "homeScreen"
}

object ScreenA : Screen {
    override val route: String
        get() = "screenA/{id}"
}


@Composable
fun HomeScreen(navController: NavHostController) {
    Text(
        text = "Home",
        style = TextStyle(color = Color.Black, fontSize = 40.sp),
        modifier = Modifier.clickable {
            navController.navigate("screenA/" + 9)
        })

}

@Composable
fun ScreenA(value: Int) {
    Text(text = value.toString(), style = TextStyle(color = Color.Black, fontSize = 40.sp))
}


@Composable
fun ApiTesting() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
//
//    var users = remember {
//        mutableListOf<IGUsers>()
//    }


    var users by remember {
        mutableStateOf(mutableListOf<IGUsers>())
    }
    var searchText by remember {
        mutableStateOf("")
    }
    Column() {
        BasicTextField(
            value = searchText, onValueChange = { searchText = it }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Yellow)
        )
        Button(onClick = {
            runBlocking {
                //  delay(4000)
                async {
                    val response: ApiResult =
                        client.get("https://instagram47.p.rapidapi.com/search?") {
                            header(
                                key = "X-RapidAPI-Host",
                                value = "instagram47.p.rapidapi.com"
                            )
                            header(
                                key = "X-RapidAPI-Key",
                                value = "b24b748452mshea7538628fc5d80p111264jsn14da7a60bb71"
                            )
                            parameter(key = "search", searchText)
                        }.body()
                    users = response.body?.users ?: mutableListOf()

                }
            }
        }) {
            Text(text = "Search")

        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
            contentPadding = PaddingValues(top = 50.dp, bottom = 10.dp),
            modifier = Modifier.background(Color.Black)
        ) {

            items(users) {
                // val constraintsScope = rememberCoroutineScope()

                Text(
                    text = it.user?.username ?: "no name",
                    style = TextStyle(
                        Color.White,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace
                    )
                )
                Image(
                    painter = rememberAsyncImagePainter(it.user?.profilePicUrl ?: ""),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }


    }
}


@Composable
fun AnimateApp() {
    val color = remember {
        Animatable(Color.Black)
    }
    val textColor = remember {
        Animatable(Color.White)
    }
    LaunchedEffect(key1 = color,key2=textColor) {
        launch {
            color.animateTo(
                Color.White,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        6000,
                        easing = FastOutSlowInEasing
                    )
                )
            )
            // textColor.animateTo(Color.Black, animationSpec = tween(6000, easing = FastOutSlowInEasing) )
        }
        launch {
            textColor.animateTo(
                Color.Black,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        6000,
                        easing = FastOutSlowInEasing
                    )
                )
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color.value)
    ) {
        Text(
            text = "Hey how are ypu.",
            style = TextStyle(fontSize = 30.sp, color = textColor.value)
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridTest() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(all = 20.dp),
        modifier = Modifier
            .background(Color.Black)
    ) {
        items(30) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(50.dp)
                    .background(Color.Blue)
            )
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
//            Text(
//                text = "800",
//                style = TextStyle(Color.White, fontSize = 20.sp, fontFamily = FontFamily.Monospace)
//            )
            Column(modifier = Modifier
                .drawBehind {
                    drawArc(
                        brush = Brush.verticalGradient(listOf(Color.Black, Color.Red)),
                        sweepAngle = 280f,
                        startAngle = -90f,
                        useCenter = false,
                        //    size = size/1.25f,
                        style = Stroke(width = 30f, cap = StrokeCap.Round),
                    )
                }
                .size(50.dp)) {}
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