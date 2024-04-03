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
import androidx.compose.material3.TextField
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
import com.example.worldguessgamecoursework.data.buttonFontSize
import com.example.worldguessgamecoursework.data.themeColor
import kotlinx.coroutines.delay

class GuessTheHintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val timerCountdown = intent.getBooleanExtra("timer",false)
            GuessTheHintScreen(timerCountdown)
        }
    }
}

@Composable
fun GuessTheHintScreen(timerCountdown : Boolean) {
    var randomFlagDisplay by remember { mutableStateOf(generateRandomFlag()) }
    val nameOfCountry = randomFlagDisplay.flagName
    val (guess, setGuess) = remember { mutableStateOf("") }
    val (guessedLetters, setGuessedLetters) = remember { mutableStateOf(mutableListOf<Char>()) }
    var countryNameHas = remember {
        mutableStateOf(nameOfCountry.map { if (it.isLetter()) "_" else it }.joinToString(" "))
    }
    var message by remember { mutableStateOf("") }
    var correctAns by remember { mutableStateOf("") }
    var attempts by remember { mutableStateOf(3) }
    var button by remember { mutableStateOf("Submit") }
    var timer by remember { mutableStateOf(10) }

    if(timerCountdown){
        LaunchedEffect(key1 = timer) {
            if (timer > 1) {
                delay(1000)
                timer--
            } else {
                if (button != "Next") {
                    message = "WRONG!"
                    correctAns = "Correct Answer : $nameOfCountry"
                    button = "Next"
                }
            }
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
                    text = "Play and Win, Country The Hint",
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
            Image(
                painter = painterResource(id = randomFlagDisplay.imagePath),
                contentDescription = randomFlagDisplay.flagName,
                modifier = Modifier
                    .size(250.dp)
            )
        }

        item {
            Text(
                text = countryNameHas.value,
                modifier = Modifier.padding(bottom = 36.dp)
            )

        }

        item {
            TextField(
                value = guess,
                onValueChange =
                { newValue ->
                    setGuess(newValue.takeLast(1)) //only one char can get last char
                    if (newValue.length > guess.length) {
                        val enteredChar =
                            newValue.last().lowercaseChar() // assign new val into enterchar
                        if (enteredChar in nameOfCountry) {    // check enter char in country name
                            val updatedString =
                                nameOfCountry.map { if (it.lowercaseChar() == enteredChar) enteredChar else "_" }
                                    .joinToString(" ")
                            countryNameHas.value = updatedString
                            if (!updatedString.contains("_")) {
                                message = "Correct!"
                            }
                        }
                    }
                },

                label = { Text("Enter guess") },
                modifier = Modifier.width(300.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Button(
                onClick = {
                    if (button == "Submit") {
                        if (guess.isNotBlank()) {
                            val guessedCountry = countryNameHas.value.replace(" ", "").lowercase()
                            if (guessedCountry.lowercase() == nameOfCountry.lowercase()) {
                                button = "Next"
                            } else {
                                val guessedChar = guess.first().lowercaseChar()
                                if (guessedChar in nameOfCountry) {

                                    setGuessedLetters((guessedLetters + guessedChar).toMutableList())  //update list
                                    val updatedString = nameOfCountry.map {
                                        if (it.lowercaseChar() in guessedLetters || it.lowercaseChar() == guessedChar) it else "_"
                                    }.joinToString(" ")
                                    countryNameHas.value = updatedString
                                } else {
                                    attempts--
                                    if (attempts == 0) {
                                        message = "WRONG!"
                                        correctAns = "Correct Answer : ${nameOfCountry}"
                                        button = "Next"


                                    }
                                }
                                setGuess("")   // text feild clear
                                if (!countryNameHas.value.contains("_")) {
                                    message = "Correct !"
                                    button = "Next"

                                }

                            }
                        }
                    } else if (button == "Next") {
                        randomFlagDisplay = generateRandomFlag()
                        countryNameHas.value =
                            randomFlagDisplay.flagName.map { if (it.isLetter()) "_" else it }
                                .joinToString(" ")
                        message = ""
                        correctAns = ""
                        button = "Submit"
                        attempts = 3
                        setGuessedLetters(mutableListOf())   // previously get letters clear (reset)
                        timer = 10
                    }

                },

                colors = ButtonDefaults.buttonColors(themeColor),
                modifier = Modifier
                    .padding(2.dp)
                    .width(150.dp)
                    .height(40.dp)

            ) {
                Text(
                    button,
                    fontSize = buttonFontSize,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

        }
        item {
            Text(
                text = message,
                color = if (message == "Correct !") Color.Green else Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Text(
                text = correctAns,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }
    }
}

