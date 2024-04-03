package com.example.worldguessgamecoursework.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldguessgamecoursework.R
import com.example.worldguessgamecoursework.data.FlagData
import com.example.worldguessgamecoursework.data.buttonFontSize
import com.example.worldguessgamecoursework.data.themeColor
import kotlinx.coroutines.delay
import kotlin.concurrent.timer

class GuessTheCountryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val timerCountdown = intent.getBooleanExtra("timer",false)
            GuessTheCountryScreen(timerCountdown)

        }
    }
}

@Composable
fun GuessTheCountryScreen(timerCountdown : Boolean) {
    var randomFlagDisplay by remember { mutableStateOf(generateRandomFlag()) }
    var selectedFlagName by remember { mutableStateOf("") }
    var submitResult by remember { mutableStateOf("") }
    val correct = "CORRECT!"
    val wrong = "WRONG!"
    var result by remember { mutableStateOf(false) }
    var timer by remember { mutableStateOf(10) }



    LaunchedEffect(key1 = timer) { //countdown 10 to 1
        if (timer > 1) {
            delay(1000)
            timer--
        } else {
            submitResult = if (randomFlagDisplay.flagName == selectedFlagName) {
                correct
            } else {
                wrong
            }
            result = true

        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF75A488)),
                verticalAlignment = Alignment.CenterVertically,


                ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "WorldGuess Game",
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,


                    )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(10.dp),
                    painter = painterResource(id = R.drawable.ad),
                    contentDescription = ""
                )

            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Play and Win, Country Flag",
                    color = Color(0xFF75A488),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(alignment = Alignment.CenterVertically)
                )

            }
        }
        item {
            // countdown timer
            if(timerCountdown){
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Text(
                        text = "Timer : ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = timer.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }


        }

        item {
            Image(
                painter = painterResource(id = randomFlagDisplay.imagePath),
                contentDescription = randomFlagDisplay.flagName,
                modifier = Modifier
                    .size(250.dp)
            )

            if (!result) {
                Button(
                    onClick = {
                        submitResult = if (randomFlagDisplay.flagName == selectedFlagName) {
                            correct
                        } else {
                            wrong
                        }
                        result = true

                    },
                    colors = ButtonDefaults.buttonColors(themeColor),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(150.dp)
                        .height(40.dp)
                ) {
                    Text(
                        "Submit",
                        fontSize = buttonFontSize,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }
            } else {
                Text(
                    text = submitResult,
                    color = if (submitResult == correct) Color.Green else Color.Red,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                if (submitResult == wrong) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Correct Answer : ${randomFlagDisplay.flagName}",
                        color = Color.Blue,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        timer = 10
                        randomFlagDisplay = FlagData.flagsList.random()
                        selectedFlagName = ""
                        submitResult = ""
                        result = false


                    },
                    colors = ButtonDefaults.buttonColors(themeColor),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(150.dp)
                        .height(40.dp)
                ) {
                    Text(
                        "Next",
                        color = Color.White,
                        fontSize = buttonFontSize,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            FlagData.flagsList.forEach { flag ->       // display all flag names listed on flagList in Flag class
                Text(
                    text = flag.flagName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textDecoration = if (flag.flagName == selectedFlagName) TextDecoration.Underline else TextDecoration.None,
                    color = if (flag.flagName == selectedFlagName) Color.Magenta else Color.Black,
                    modifier = Modifier
                        .clickable {
                            if (!result) {
                                selectedFlagName = flag.flagName

                            }
                        }

                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}



