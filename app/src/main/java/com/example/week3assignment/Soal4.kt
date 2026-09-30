package com.example.week3assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class Soal4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3AssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    View4(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun View4(modifier: Modifier = Modifier) {
    var currentPage by rememberSaveable() { mutableStateOf(Pages4.INITIAL) }
    var humanChoice by rememberSaveable() { mutableStateOf(RPS.ROCK)}
    var aiChoice by rememberSaveable() { mutableStateOf(RPS.ROCK)}
    var result by rememberSaveable() { mutableStateOf(Result.DRAW)}
    val N = 5//Question said best out of N so i made it best out of N
    var score by rememberSaveable { mutableIntStateOf(0) }
    var aiscore by rememberSaveable { mutableIntStateOf(0) }
    var highscore by rememberSaveable { mutableIntStateOf(0) }

    fun calculate() {
        when {
            humanChoice == RPS.ROCK && aiChoice == RPS.SCISSORS -> {
                result = Result.WIN
                score++
            }
            humanChoice == RPS.PAPER && aiChoice == RPS.ROCK -> {
                result = Result.WIN
                score++
            }
            humanChoice == RPS.SCISSORS && aiChoice == RPS.PAPER -> {
                result = Result.WIN
                score++
            }
            humanChoice == aiChoice -> {
                result = Result.DRAW
            }
            else -> {
                result = Result.LOSE
                aiscore++
            }
        }

        if(score == (N/2 + 1)){
            result = Result.WIN
            highscore = score
            currentPage = Pages4.FINISHED
        } else if (aiscore == (N/2 + 1)){
            result = Result.LOSE
            highscore = score
            currentPage = Pages4.FINISHED
        } else {
            currentPage = Pages4.REVEAL
        }
    }
    when (currentPage) {
        Pages4.INITIAL -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xfffafafa))
            ){
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 260.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){//Top row
                        Text(
                            text = "👦 ${score} - ${aiscore} 🤖",
                            fontSize = 18.sp,
                            color = Color.DarkGray
                        )//Score board
                        Text(
                            text = "Best of ${N}",
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )//Best of
                    }
                    Spacer(modifier = Modifier.height(120.dp))
                    Text(
                        text = "Rock • Paper • Scissors",
                        fontSize = 24.sp,
                        color = Color(0xff2d3b44)
                    )//Rock . Paper . Scissors
                    Spacer(modifier.height(46.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffbac8d1)
                        ),
                        modifier = Modifier.size(190.dp, 50.dp),
                        onClick = {
                            score = 0
                            aiscore = 0
                            currentPage = Pages4.PICK
                        }
                    ) {//Start button
                        Text(
                            text = "Start",
                            fontSize = 16.sp,
                            color = Color(0xff2d3b44)
                        )//Start
                    }
                }
            }
        }
        Pages4.PICK -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xfffafafa))
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 260.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){//Top row
                        Text(
                            text = "👦 ${score} - ${aiscore} 🤖",
                            fontSize = 18.sp,
                            color = Color(0xff2d3b44)
                        )//Score Board
                        Text(
                            text = "Best of ${N}",
                            fontSize = 16.sp,
                            color = Color(0xff2d3b44)
                        )//Best of
                    }
                    Spacer(modifier = Modifier.height(70.dp))
                    Text(
                        text = "Pick your move",
                        fontSize = 16.sp,
                        color = Color(0xff2d3b44)
                    )//Best of
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "❔ VS ❔",
                        fontSize = 34.sp,
                        color = Color(0xff2d3b44)
                    )//? vs ?
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xffbac8d1)
                            ),
                            onClick = {
                                humanChoice = RPS.ROCK
                                aiChoice = RPS.entries.random()
                                calculate()
                            }
                        ) {//Right button
                            Text(
                                text = "✊ Rock",
                                fontSize = 12.sp,
                                color = Color(0xff2d3b44)
                            )//Text
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xffbac8d1)
                            ),
                            onClick = {
                                humanChoice = RPS.PAPER
                                aiChoice = RPS.entries.random()
                                calculate()
                            }
                        ) {//Right button
                            Text(
                                text = "✋ Paper",
                                fontSize = 12.sp,
                                color = Color(0xff2d3b44)
                            )//Text
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xffbac8d1)
                            ),
                            onClick = {
                                humanChoice = RPS.SCISSORS
                                aiChoice = RPS.entries.random()
                                calculate()
                            }
                        ) {//Right button
                            Text(
                                text = "✌ Scissors",
                                fontSize = 12.sp,
                                color = Color(0xff2d3b44)
                            )//Text
                        }
                    }
                }
            }
        }
        Pages4.REVEAL -> {

            LaunchedEffect(Unit) {
                delay(700L)
                currentPage = Pages4.PICK
            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xfffafafa))
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 260.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {//Top row
                        Text(
                            text = "👦 ${score} - ${aiscore} 🤖",
                            fontSize = 18.sp,
                            color = Color(0xff2d3b44)
                        )//Score Board
                        Text(
                            text = "Best of ${N}",
                            fontSize = 16.sp,
                            color = Color(0xff2d3b44)
                        )//Best of
                    }
                    Spacer(modifier = Modifier.height(120.dp))
                    Text(
                        text = when (humanChoice) {
                            RPS.ROCK -> when (aiChoice) {
                                RPS.ROCK -> "✊ VS ✊"
                                RPS.PAPER -> "✊ VS ✋"
                                RPS.SCISSORS -> "✊ VS ✌"
                            }

                            RPS.PAPER -> when (aiChoice) {
                                RPS.ROCK-> "✋ VS ✊"
                                RPS.PAPER -> "✋ VS ✋"
                                RPS.SCISSORS -> "✋ VS"
                            }

                            RPS.SCISSORS -> when (aiChoice) {
                                RPS.ROCK -> "✌ VS ✊"
                                RPS.PAPER -> "✌ VS ✋"
                                RPS.SCISSORS -> "✌ VS ✌"
                            }
                        },
                        fontSize = 36.sp,
                        color = Color(0xff2d3b44)
                    )//? vs ?
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = when (result) {
                            Result.WIN -> {
                                "You Win!"
                            }
                            Result.LOSE -> {
                                "You Lost..."
                            }
                            Result.DRAW -> {
                                "Draw"
                            }
                        },
                        fontSize = 18.sp,
                        color = Color(0xff2d3b44)
                    )//Result
                }
            }
        }
        Pages4.FINISHED -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xfffafafa))
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 260.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){//Top row
                        Text(
                            text = "👦 ${score} - ${aiscore} 🤖",
                            fontSize = 18.sp,
                            color = Color(0xff2d3b44)
                        )//Score Board
                        Text(
                            text = "Best of ${N}",
                            fontSize = 16.sp,
                            color = Color(0xff2d3b44)
                        )//Best of
                    }
                    Spacer(modifier = Modifier.height(70.dp))
                    Text(
                        text = when (result){
                            Result.WIN ->"You Win the Match!"
                            Result.LOSE ->"You lose the Match..."
                            else ->""
                        },
                        fontSize = 22.sp,
                        color = Color(0xff2d3b44)
                    )//Best of
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Best Score: ${highscore}",
                        fontSize = 16.sp,
                        color = Color(0xff2d3b44)
                    )//? vs ?
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xffbac8d1)
                            ),
                            onClick = {
                                score = 0
                                aiscore = 0
                                currentPage = Pages4.PICK
                            }
                        ) {//Restart
                            Text(
                                text = "Restart",
                                fontSize = 12.sp,
                                color = Color(0xff2d3b44)
                            )//Restart
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xffbac8d1)
                            ),
                            onClick = {
                                currentPage = Pages4.INITIAL
                            }
                        ) {//Exit
                            Text(
                                text = "Exit",
                                fontSize = 12.sp,
                                color = Color(0xff2d3b44)
                            )//Exit
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview4() {
    Week3AssignmentTheme {
        View4()
    }
}

enum class Pages4 {
    INITIAL,
    PICK,
    REVEAL,
    FINISHED
}

enum class RPS {
    ROCK,
    PAPER,
    SCISSORS
}

enum class Result {
    WIN,
    LOSE,
    DRAW
}