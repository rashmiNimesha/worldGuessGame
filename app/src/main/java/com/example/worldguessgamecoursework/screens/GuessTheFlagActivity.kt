package com.example.worldguessgamecoursework.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldguessgamecoursework.R
import com.example.worldguessgamecoursework.data.FlagData
import com.example.worldguessgamecoursework.data.buttonFontSize
import com.example.worldguessgamecoursework.data.themeColor
import kotlinx.coroutines.delay

class GuessTheFlagActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val timerCountdown = intent.getBooleanExtra("timer",false)
            GuessTheFlagScreen(timerCountdown)
        }
    }
}

@Composable
fun GuessTheFlagScreen(timerCountdown : Boolean) {
    var currentFlag by remember { mutableStateOf(FlagData.flagsList.random()) }
    var flagOptions by remember { mutableStateOf(generateOptions(currentFlag)) }
    var showMessage by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var timer by remember { mutableStateOf(10) }

    var timerrunning by remember { mutableStateOf(true) }

    if(timerCountdown){
        LaunchedEffect(key1 = timer, timerrunning) {
            while (timerrunning && timer > 1) {
                delay(1000)
                timer--
            }
//        if (timerSeconds == 0) {
//            message = "Time's up!"
//        }
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
                    text = "Play and Win, Guess the Flag",
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
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Find the ${currentFlag.flagName}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
        }

        item {      // display 3 random flags
            flagOptions.forEach { flag_ ->
                FlagImage(
                    flag = flag_,
                    onClick = {
                        showMessage = true
                        if (flag_.flagName == currentFlag.flagName) {
                            message = "CORRECT !!"
                            timerrunning = false

                        } else {
                            message = "WRONG !! "
                            timerrunning = false

                        }
                    },
                    )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Text(
                text = message,
                color = if (message == "CORRECT !!") Color.Green else Color.Red,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            Button(
                onClick = {
                    timer = 10
                    timerrunning = true
                    currentFlag = FlagData.flagsList.random()
                    flagOptions = generateOptions(currentFlag)
                    message = ""
                },
                colors = ButtonDefaults.buttonColors(themeColor),
                modifier = Modifier
                    .padding(2.dp)
                    .width(150.dp)
                    .height(40.dp)
            ) {
                Text(
                    text = "Next",
                    fontSize = buttonFontSize,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    color = Color.White,
                )

            }
        }
    }

}