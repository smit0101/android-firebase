package com.smit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smit.ui.theme.AndroidfirebaseTheme

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
                    ProcessMeter()
                }
            }
        }
    }
}

@Composable
fun ProcessMeter() {
    Column( verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(600.dp)
            .background(brush = Brush.verticalGradient(listOf(Color.Blue, Color.Cyan)))) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
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
                .size(100.dp)){
            Text(text = "800", style = TextStyle(Color.White, fontSize = 20.sp, fontFamily = FontFamily.Monospace))
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
            .size(100.dp)){}
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidfirebaseTheme {

    }
}