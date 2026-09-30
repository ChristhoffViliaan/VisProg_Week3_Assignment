package com.example.week3assignment

import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week3assignment.ui.theme.Week3AssignmentTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

class Soal1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3AssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    View1(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun View1(modifier: Modifier = Modifier) {
    var currentPage by rememberSaveable() { mutableStateOf(Pages1.HOME) }
    var startTime by rememberSaveable { mutableStateOf(0L) }
    var reactionTime by rememberSaveable { mutableStateOf(0L) }
    var average by rememberSaveable { mutableStateOf(0L) }
    var counter by rememberSaveable { mutableIntStateOf(0) }
    val reactionTimes = rememberSaveable { mutableStateListOf<Long>() }

    when (currentPage) {
        Pages1.HOME -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xFF72cedd))
                    .clickable{
                        currentPage = Pages1.WAITING
                    }
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 260.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Reaction",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFFFFFF)
                    )//Reaction
                    Spacer(modifier = Modifier.height(30.dp))
                    Icon(
                        imageVector = Icons.Filled.FlashOn,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(150.dp)
                    )//Lightning Bolt
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "Test",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFFFFFF)
                    )//Test
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Click to Start",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xffFFFFFF)
                    )//Click to start
                }
            }
        }
        Pages1.WAITING -> {
            LaunchedEffect(Unit) {
                val randomTime = Random.nextLong(500L, 4500L)
                delay(randomTime)
                counter++
                startTime = System.currentTimeMillis()
                currentPage = Pages1.CLICKING
            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xFFdbdbdb))
                    .clickable{
                        currentPage = Pages1.FAILURE
                    }
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 260.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Get Ready",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFFFFFF)
                    )//Get ready
                    Spacer(modifier = Modifier.height(30.dp))
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(150.dp)
                    )//Triangle with !
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "Wait for green light...",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFFFFFF)
                    )//Wait for green
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "DON'T CLICK YET!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xffFFFFFF)
                    )//Don't click
                }
            }
        }
        Pages1.FAILURE -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xfffe4445))
                    .clickable{
                        currentPage = Pages1.WAITING
                    }
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 180.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "FAIL!",
                        fontSize = 38.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xffFFFFFF)
                    )//Fail
                    Spacer(modifier = Modifier.height(30.dp))
                    Icon(
                        imageVector = Icons.Filled.ThumbDown,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(150.dp)
                    )//Thumbs down
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "You clicked too early, TRY TO READ THE RULE BRO",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(300.dp)
                    )//You clicked too early
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "TRY AGAIN",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xffFFFFFF)
                    )//Try again
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        modifier = Modifier
                            .widthIn(min = 180.dp)
                            .height(140.dp)
                            .shadow(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .background(
                                color = Color(0xFFf0f2ef),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 20.dp)
                    ) {//Results box
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                        ) {
                            Text(
                                text = "Trial Results",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2)
                            )//Results
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.widthIn(min = 150.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "1",
                                        fontSize = 16.sp,
                                        color = if (reactionTimes.size > 0)
                                            Color(0xFF4CB050)
                                        else
                                            Color.Gray
                                    )
                                    Text(
                                        text = if (reactionTimes.size > 0)
                                            "${reactionTimes[0]}ms"
                                        else
                                            "-",
                                        fontSize = 14.sp,
                                        color = if (reactionTimes.size > 0)
                                            Color.Black
                                        else
                                            Color.Gray
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "2",
                                        fontSize = 16.sp,
                                        color = if (reactionTimes.size > 1)
                                            Color(0xFF4CB050)
                                        else
                                            Color.Gray
                                    )
                                    Text(
                                        text = if (reactionTimes.size > 1)
                                            "${reactionTimes[1]}ms"
                                        else
                                            "-",
                                        fontSize = 14.sp,
                                        color = if (reactionTimes.size > 1)
                                            Color.Black
                                        else
                                            Color.Gray
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "3",
                                        fontSize = 16.sp,
                                        color = if (reactionTimes.size > 2)
                                            Color(0xFF4CB050)
                                        else
                                            Color.Gray
                                    )
                                    Text(
                                        text = if (reactionTimes.size > 2)
                                            "${reactionTimes[2]}ms"
                                        else
                                            "-",
                                        fontSize = 14.sp,
                                        color = if (reactionTimes.size > 2)
                                            Color.Black
                                        else
                                            Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Pages1.CLICKING -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xFF4cb050))
                    .clickable{
                        reactionTime = System.currentTimeMillis() - startTime
                        reactionTimes.add(reactionTime)
                        currentPage = if (counter > 2){
                            for (time in reactionTimes) {
                                average += time
                            }
                            average = average/(reactionTimes.size)
                            Pages1.FINALRESULT
                        } else {
                            Pages1.RESULT
                        }
                    }
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 260.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "GO!",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFFFFFF)
                    )//Go
                    Spacer(modifier = Modifier.height(30.dp))
                    Icon(
                        imageVector = Icons.Filled.DirectionsRun,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(150.dp)
                    )//Man running
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "CLICK NOW!",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFFFFFF)
                    )//Click now
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "TAP AS FAST AS YOU CAN!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xffFFFFFF)
                    )//Tap tap tap
                }
            }
        }
        Pages1.RESULT -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color(0xFF4cb050))
                    .clickable{
                        currentPage = Pages1.WAITING
                    }
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 180.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Trial $counter Complete!",
                        fontSize = 38.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xffFFFFFF)
                    )//Trial * complete
                    Spacer(modifier = Modifier.height(30.dp))
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(150.dp)
                    )//Tick (v)
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "Time: ${reactionTime}ms",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFFFFFF)
                    )//Reaction time in ms
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Continue to trial ${counter + 1}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xffFFFFFF)
                    )//Continue to trial
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        modifier = Modifier
                            .widthIn(min = 180.dp)
                            .height(140.dp)
                            .shadow(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .background(
                                color = Color(0xFFf0f2ef),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 20.dp)
                    ) {//Results box
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                        ) {
                            Text(
                                text = "Trial Results",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2)
                            )//Results
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.widthIn(min = 150.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "1",
                                        fontSize = 16.sp,
                                        color = if (reactionTimes.size > 0)
                                            Color(0xFF4CB050)
                                        else
                                            Color.Gray
                                    )
                                    Text(
                                        text = if (reactionTimes.size > 0)
                                            "${reactionTimes[0]}ms"
                                        else
                                            "-",
                                        fontSize = 14.sp,
                                        color = if (reactionTimes.size > 0)
                                            Color.Black
                                        else
                                            Color.Gray
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "2",
                                        fontSize = 16.sp,
                                        color = if (reactionTimes.size > 1)
                                            Color(0xFF4CB050)
                                        else
                                            Color.Gray
                                    )
                                    Text(
                                        text = if (reactionTimes.size > 1)
                                            "${reactionTimes[1]}ms"
                                        else
                                            "-",
                                        fontSize = 14.sp,
                                        color = if (reactionTimes.size > 1)
                                            Color.Black
                                        else
                                            Color.Gray
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "3",
                                        fontSize = 16.sp,
                                        color = if (reactionTimes.size > 2)
                                            Color(0xFF4CB050)
                                        else
                                            Color.Gray
                                    )
                                    Text(
                                        text = if (reactionTimes.size > 2)
                                            "${reactionTimes[2]}ms"
                                        else
                                            "-",
                                        fontSize = 14.sp,
                                        color = if (reactionTimes.size > 2)
                                            Color.Black
                                        else
                                            Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Pages1.FINALRESULT-> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        if (average >= 450){
                            Color(0xfffc5622)
                        } else if (average >= 280){
                            Color(0xfffe9700)
                        } else if (average >= 180){
                            Color(0xff2296f3)
                        } else {
                            Color(0xff01e676)
                        }
                    )
                    .clickable{
                        counter = 0
                        average = 0
                        reactionTimes.clear()
                        currentPage = Pages1.HOME
                    }
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 180.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (average >= 450){
                            "YOU LIKE A SNAIL"
                        } else if (average >= 280){
                            "MEH LIKE OTHER PERSON"
                        } else if (average >= 180){
                            "YOUR REFLEX IS GOOD"
                        } else {
                            "DANG YOU ARE SO FAST BRO"
                        },
                        fontSize = 38.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xffFFFFFF),
                        modifier = modifier.widthIn(min = 300.dp)
                    )//Alternating Title
                    Spacer(modifier = Modifier.height(30.dp))
                    Image(
                        painter = painterResource(id = if (average >= 450){
                            R.drawable.bad
                        } else if (average >= 280){
                            R.drawable.soso
                        } else if (average >= 180){
                            R.drawable.good
                        } else {
                            R.drawable.gas
                        } ),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )//Cool Image
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "Average: ${average}ms",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFFFFFF)
                    )//Average reaction time in ms
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Click to Start New Test",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xffFFFFFF)
                    )//CNew test
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        modifier = Modifier
                            .widthIn(min = 180.dp)
                            .height(200.dp)
                            .shadow(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .background(
                                color = Color(0xFFf0f2ef),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 20.dp)
                    ) {//Results box
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                        ) {
                            Text(
                                text = "Trial Results",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2)
                            )//Results
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.widthIn(min = 150.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "1",
                                        fontSize = 16.sp,
                                        color = if (reactionTimes.size > 0)
                                            Color(0xFF4CB050)
                                        else
                                            Color.Gray
                                    )
                                    Text(
                                        text = if (reactionTimes.size > 0)
                                            "${reactionTimes[0]}ms"
                                        else
                                            "-",
                                        fontSize = 14.sp,
                                        color = if (reactionTimes.size > 0)
                                            Color.Black
                                        else
                                            Color.Gray
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "2",
                                        fontSize = 16.sp,
                                        color = if (reactionTimes.size > 1)
                                            Color(0xFF4CB050)
                                        else
                                            Color.Gray
                                    )
                                    Text(
                                        text = if (reactionTimes.size > 1)
                                            "${reactionTimes[1]}ms"
                                        else
                                            "-",
                                        fontSize = 14.sp,
                                        color = if (reactionTimes.size > 1)
                                            Color.Black
                                        else
                                            Color.Gray
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "3",
                                        fontSize = 16.sp,
                                        color = if (reactionTimes.size > 2)
                                            Color(0xFF4CB050)
                                        else
                                            Color.Gray
                                    )
                                    Text(
                                        text = if (reactionTimes.size > 2)
                                            "${reactionTimes[2]}ms"
                                        else
                                            "-",
                                        fontSize = 14.sp,
                                        color = if (reactionTimes.size > 2)
                                            Color.Black
                                        else
                                            Color.Gray
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Average Score",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2)
                            )//Average Score
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "${average}ms",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFf08735)
                            )//Average
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview1() {
    Week3AssignmentTheme {
        View1()
    }
}

enum class Pages1 {
    HOME,
    WAITING,
    CLICKING,
    RESULT,
    FINALRESULT,
    FAILURE
}