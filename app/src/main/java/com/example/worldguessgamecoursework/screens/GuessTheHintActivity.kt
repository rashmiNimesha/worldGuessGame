package com.example.worldguessgamecoursework.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldguessgamecoursework.R
import com.example.worldguessgamecoursework.data.buttonFontSize
import com.example.worldguessgamecoursework.data.themeColor
import com.example.worldguessgamecoursework.screens.ui.theme.WorldGuessGameCourseworkTheme

class GuessTheHintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheHintScreen()
        }
    }
}

@Composable
fun GuessTheHintScreen(){
    var randomFlagDisplay by remember { mutableStateOf(generateRandomFlag()) }
    val countryName = randomFlagDisplay.flagName
    val (guess, setGuess) = remember { mutableStateOf("") }
    val (guessedLetters, setGuessedLetters) = remember { mutableStateOf(mutableListOf<Char>()) }

    // var countryNameHas = countryName.map { if (it.isLetter()) "_" else it }.joinToString("    ")
    // var dashesCountryName = countryNameHas.value
    var countryNameHas = remember { mutableStateOf(countryName.map { if (it.isLetter()) "_" else it }.joinToString(" ")) }
//      var dashesCountryName by remember {
//          mutableStateOf(countryNameHas.value)
//      }
    var message by remember {
        mutableStateOf("")
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
            // Text field for user input
            TextField(
                value = guess,
                onValueChange =
                { newValue ->
                    setGuess(newValue.takeLast(1)) // Take the last character entered
                    if (newValue.length > guess.length ) { // Check if a new character was added
                        val enteredChar = newValue.last().lowercaseChar()
                        if (enteredChar in countryName) {
                            val updatedString = countryName.map { if (it.lowercaseChar() == enteredChar) enteredChar else "_" }.joinToString(" ")
                            countryNameHas.value = updatedString
                        }
                    }
                },

                label = { Text("Enter guess") },
                modifier = Modifier.width(300.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            // Button to submit guess
            Button(
                onClick = {


                    if (guess.isNotBlank()) {
                        val guessedCountry = countryNameHas.value.replace(" ", "").lowercase()


                        if (guessedCountry.equals(countryName.lowercase())) {
                            message = "Coorect"
                        } else {
                            val guessedChar = guess.first().lowercaseChar()
                            if (guessedChar in countryName) {
                                // Update guessed characters list
                                setGuessedLetters((guessedLetters + guessedChar).toMutableList())
                                // Update countryNameHas immediately
                                val updatedString = countryName.map {
                                    if (it.lowercaseChar() in guessedLetters || it.lowercaseChar() == guessedChar) it else "_"
                                }.joinToString(" ")
                                countryNameHas.value = updatedString


                            }
                            // Clear the guess text field
                            setGuess("")
                        }


                    }

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
            Spacer(modifier = Modifier.height(10.dp))

        }
        item {
            Text(text = message)
            Spacer(modifier = Modifier.height(20.dp))
        }





    }
}

