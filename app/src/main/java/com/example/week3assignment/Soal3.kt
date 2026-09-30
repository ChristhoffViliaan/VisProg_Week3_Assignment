package com.example.week3assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week3assignment.ui.theme.Week3AssignmentTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

class Soal3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3AssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    View3(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun View3(modifier: Modifier = Modifier) {
    var currentPage by rememberSaveable() { mutableStateOf(Pages3.HOME) }
    var textcolour by rememberSaveable() { mutableStateOf(Colours.BLACK) }
    var inkcolour by rememberSaveable() { mutableStateOf(Colours.BLACK) }
    var gamemode by rememberSaveable() { mutableStateOf(Mode.COLOUR) }
    var score by rememberSaveable { mutableIntStateOf(0) }
    var highscore by rememberSaveable { mutableIntStateOf(0) }
    var failures by rememberSaveable { mutableIntStateOf(0) }
    val maxFailure = 3
    var counter by rememberSaveable { mutableIntStateOf(5) }
    var answerIsLeft by rememberSaveable { mutableStateOf(false) }

    fun resetRandomizers() {
        textcolour = Colours.entries.random()
        do {
            inkcolour = Colours.entries.random()
        } while (inkcolour == textcolour)
        gamemode = Mode.entries.random()
        answerIsLeft = Random.nextBoolean()
        counter = 5
    }

    when (currentPage) {
        Pages3.HOME -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xfffafafa))
            ){
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 330.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Welcome",
                        fontSize = 26.sp,
                        color = Color(0xff4e4e4e)
                    )//Welcome
                    Text(
                        text = "to",
                        fontSize = 26.sp,
                        color = Color(0xff4e4e4e)
                    )//To
                    Text(
                        text = "Colour Word Matching",
                        fontSize = 26.sp,
                        color = Color(0xff4e4e4e)
                    )//Colour Word Matching
                    Spacer(modifier = Modifier.height(60.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffbac8d1)
                        ),
                        onClick = {
                            currentPage = Pages3.COUNTDOWN
                        }
                    ) {//Start game button
                        Text(
                            text = "Start Game",
                            fontSize = 18.sp,
                            color = Color(0xff2d3b44)
                        )//Start game
                    }
                }

            }
        }
        Pages3.COUNTDOWN -> {
            LaunchedEffect(Unit) {
                counter = 3
                delay(1000L)
                counter--
                delay(1000L)
                counter--
                delay(1000L)
                counter--
                delay(1000L)
                score = 0
                failures = 0
                resetRandomizers()
                currentPage = Pages3.GAME
            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xfffafafa)),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = if (counter > 0){
                        "${counter}"
                    } else {
                        "Start!"
                    },
                    fontSize = 22.sp,
                    color = Color(0xff2d3b44)
                )//Start game
            }
        }
        Pages3.GAME -> {
            LaunchedEffect(Unit) {
                while (currentPage == Pages3.GAME) {
                    delay(1000L)
                    counter--
                    if (counter < 0){
                        failures++
                        if (failures >= 3){
                            currentPage = Pages3.GAMEOVER
                        }
                        resetRandomizers()
                    }
                }
            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xfffafafa)),

            ){
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row() {
                        Text(
                            text = "Mode: ${gamemode}",
                            fontSize = 16.sp,
                            color = Color(0xff2d3b44)
                        )//Mode
                        Spacer(Modifier.width(120.dp))
                        Icon(
                            imageVector = Icons.Filled.CheckBox,
                            contentDescription = null,
                            tint = Color(0xFF76B852)
                        )//V
                        Text(
                            text = "${score}",
                            fontSize = 16.sp,
                            color = Color(0xff2d3b44)
                        )//Score
                        Spacer(Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            tint = Color.Red
                        )//X
                        Text(
                            text = "${failures}/${maxFailure}",
                            fontSize = 16.sp,
                            color = Color(0xff2d3b44)
                        )//Failure
                    }
                    Spacer(Modifier.height(100.dp))
                    Text(
                        text = "${counter}s",
                        fontSize = 18.sp,
                        color = Color(0xff2d3b44)
                    )//Counter
                    Spacer(Modifier.height(22.dp))
                    Text(
                        text = "${textcolour}",
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Light,
                        color = when (inkcolour) {
                            Colours.RED -> Color(0xffff0000)
                            Colours.YELLOW -> Color(0xffffd500)
                            Colours.GREEN -> Color(0xff45ff17)
                            Colours.BLUE -> Color(0xff21c0ff)
                            Colours.PURPLE -> Color(0xffc300ff)
                            Colours.PINK -> Color(0xFFFF00bf)
                            Colours.BLACK -> Color.Black
                        }
                    )//Display
                    Spacer(Modifier.height(200.dp))
                    Row() {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xffbac8d1)
                            ),
                            onClick = {
                                if (answerIsLeft){
                                    score++
                                } else {
                                    failures++
                                }
                                if (failures >= 3){
                                    currentPage = Pages3.GAMEOVER
                                }
                                resetRandomizers()
                            }
                        ) {//Left button
                            Text(
                                text = if (gamemode == Mode.COLOUR){
                                    if (answerIsLeft){
                                        "${inkcolour}"
                                    } else {
                                        "${textcolour}"
                                    }
                                } else {
                                    if (answerIsLeft){
                                        "${textcolour}"
                                    } else {
                                        "${inkcolour}"
                                    }
                                },
                                fontSize = 14.sp,
                                color = Color(0xff2d3b44)
                            )//Text
                        }
                        Spacer(Modifier.width(40.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xffbac8d1)
                            ),
                            onClick = {
                                if (answerIsLeft){
                                    failures++
                                } else {
                                    score++
                                }
                                if (failures >= 3){
                                    currentPage = Pages3.GAMEOVER
                                }
                                resetRandomizers()
                            }
                        ) {//Right button
                            Text(
                                text = if (gamemode == Mode.COLOUR){
                                    if (answerIsLeft){
                                        "${textcolour}"
                                    } else {
                                        "${inkcolour}"
                                    }
                                } else {
                                    if (answerIsLeft){
                                        "${inkcolour}"
                                    } else {
                                        "${textcolour}"
                                    }
                                },
                                fontSize = 14.sp,
                                color = Color(0xff2d3b44)
                            )//Text
                        }
                    }//Buttons
                }
            }
        }
        Pages3.GAMEOVER -> {
            if (score > highscore){
                highscore = score
            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xfffafafa))
            ){
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 230.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Game Over!",
                        fontSize = 32.sp,
                        color = Color(0xff2d3b44)
                    )//Game over
                    Spacer(modifier = Modifier.height(60.dp))
                    Text(
                        text = "Your Score",
                        fontSize = 22.sp,
                        color = Color(0xff2d3b44)
                    )//Your score (not You're score)
                    Text(
                        text = "${score}",
                        fontSize = 22.sp,
                        color = Color(0xff2d3b44)
                    )//Score
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Best Score",
                        fontSize = 14.sp,
                        color = Color(0xff2d3b44)
                    )//Best Score
                    Text(
                        text = "${highscore}",
                        fontSize = 14.sp,
                        color = Color(0xff2d3b44)
                    )//Best
                    Spacer(modifier = Modifier.height(26.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffbac8d1)
                        ),
                        onClick = {
                            currentPage = Pages3.COUNTDOWN
                        }
                    ) {//Restart game
                        Text(
                            text = "Restart Game",
                            fontSize = 18.sp,
                            color = Color(0xff2d3b44)
                        )//Restart game
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffbac8d1)
                        ),
                        onClick = {
                            currentPage = Pages3.HOME
                        }
                    ) {//Exit
                        Text(
                            text = "Exit",
                            fontSize = 18.sp,
                            color = Color(0xff2d3b44)
                        )//Exit
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview3() {
    Week3AssignmentTheme {
        View3()
    }
}

enum class Pages3 {
    HOME,
    COUNTDOWN,
    GAME,
    GAMEOVER
}



enum class Mode {
    COLOUR,
    TEXT
}
enum class Colours {
    RED,
    YELLOW,
    GREEN,
    BLUE,
    PURPLE,
    PINK,
    BLACK
}

enum class Choice {
    RED,
    YELLOW,
    GREEN,
    BLUE,
    PURPLE,
    PINK,
    BLACK
}

//I was asked to make a separate enum for choice and colour despite them serving seemingly the same purpose.