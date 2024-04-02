package com.example.worldguessgamecoursework.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldguessgamecoursework.R

class AdvancedLevelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedLevelScreen()

        }
    }
}

@Composable
fun AdvancedLevelScreen(){
    var flagOptions by rememberSaveable { mutableStateOf(generateRandomFlags_()) }
    var guess by remember { mutableStateOf(List(flagOptions.size) { "" }) }
    val (guesses, setGuesses) = remember { mutableStateOf(List(flagOptions.size) { "" }) }
    var msg by remember { mutableStateOf("") }
    var submit by remember {mutableStateOf(false)}

    fun checkAnswers() {
        var allCorrect = true
        flagOptions.indices.forEach { index ->
            if (!flagOptions[index].flagName.equals(guess[index], ignoreCase = true)) {
                allCorrect = false
            }
        }
        submit = false
        msg = if (allCorrect) {
            "Correct"
        }
        else{
            "Wrong"
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally){

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
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Play and Win, Home Page",
                    color = Color(0xFF75A488),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(alignment = Alignment.CenterVertically))

            }
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))

            Row {

                flagOptions.forEachIndexed{index, flag_->
                    FlagImageAdvanced(flag = flag_) {

                    }

                }

            }
        }

        item {
            Row (modifier = Modifier.padding(16.dp)){
                Text(text = "(1)",
                    modifier = Modifier.padding(end = 56.dp, start = 36.dp),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black)
                Text(text = "(2)",
                    modifier = Modifier.padding(end = 56.dp, start = 46.dp),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black)
                Text(text = "(3)",
                    modifier = Modifier.padding( start = 40.dp),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black)
            }
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))


            flagOptions.forEachIndexed { index, _ ->
                //  FlagInputRow(index = index)
                Row {
                    Text(
                        text = "(${index + 1})",
                        modifier = Modifier.padding(end = 26.dp, start = 26.dp),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Black
                    )
                    TextField(
                        value = guess[index],
                        onValueChange = { newValue ->

                            // Update guess
                            if(!submit){
                                guess = guess.toMutableList().also { it[index] = newValue }
                            }

                        },
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .background(
                                if (submit && guess[index].equals(
                                        flagOptions[index].flagName,
                                        ignoreCase = false
                                    )
                                ) Color.LightGray else Color.Red
                            )

                            .border(
                                BorderStroke(
                                    width = 1.dp,
                                    color = if (submit && guess[index].equals(
                                            flagOptions[index].flagName,
                                            ignoreCase = true
                                        )
                                    ) Color.Green else Color.Red
                                )

                            ))


//

                }
            }
        }

        item {
            Button(onClick = {
                checkAnswers()
            }) {
                Text(text = "Submit")
            }
        }

        item {
            Text(text = msg)
        }


    }

}