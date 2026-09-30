package com.example.week3assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week3assignment.ui.theme.Week3AssignmentTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Soal2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3AssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    View2(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun View2(modifier: Modifier = Modifier) {
    var totalcoin by rememberSaveable { mutableDoubleStateOf(0.0) }
    var upgradeCost by rememberSaveable { mutableDoubleStateOf(10.0) }
    var coinsNeeded by rememberSaveable { mutableDoubleStateOf(10.0) }
    var multiplier by rememberSaveable { mutableDoubleStateOf(1.0) }
    var nextMultiplier by rememberSaveable { mutableDoubleStateOf(1.5) }
    var buttonisclicked by rememberSaveable { mutableStateOf(false)}
    var resetJob by remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.cute),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )//Background
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .widthIn(min = 170.dp)
                    .height(150.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 20.dp)
            ){//Your coins
                Column(
                    modifier = Modifier
                        .widthIn(min = 140.dp)
                        .padding(top = 18.dp, bottom = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Your coins",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xffffffff)
                    )//Your coins
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${totalcoin}",
                        fontSize = 54.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xff02e575)
                    )//Total count
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = if (multiplier == 1.0){
                            "1 coin per tap"
                        } else {
                            "${multiplier} coin per tap"
                        },
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xffffffff)
                    )//Coins per tap
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Tap the Cat!",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xffffffff)
            )//Tap the cat
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = if (buttonisclicked){
                    R.drawable.open
                } else {
                    R.drawable.close
                } ),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(){
                        totalcoin += multiplier
                        coinsNeeded = upgradeCost - totalcoin
                        scope.launch {
                            buttonisclicked = true
                            resetJob?.cancel()
                            resetJob = scope.launch {
                                delay(100L)
                                buttonisclicked = false
                            }
                        }
                    }
            )//Cool Image//Your Coins
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = if (!buttonisclicked){
                    "Purr~"
                } else {
                    "Meow!"
                },
                fontSize = 18.sp,
                color = Color(0xffffffff)
            )//purr~
            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(160.dp)
                    .background(
                        color = Color(0xfff8f7f3),
                        shape = RoundedCornerShape(16.dp)
                    )
            ){//Gimme your coins
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 18.dp, bottom = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Give Me Your Coins",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xff4b4b4a)
                    )//Gimme your coins
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Next upgrade: +${nextMultiplier} coins per tap",
                        fontSize = 16.sp,
                        color = Color(0xff949494)
                    )//Next upgrade
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffbebebf)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                            if (coinsNeeded < 0){
                                totalcoin -= upgradeCost
                                multiplier = multiplier*1.5
                                nextMultiplier = multiplier*1.5
                                upgradeCost = upgradeCost*2
                                coinsNeeded = upgradeCost - totalcoin
                            }
                        }
                    ) {//Upgrade Button
                        Text(
                            text = if (coinsNeeded >= 0) {
                                "Find ${coinsNeeded} more coins"
                            } else {
                                "Upgrade"
                            },
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xffececec)
                        )//Find * more coins
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview2() {
    Week3AssignmentTheme {
        View2()
    }
}