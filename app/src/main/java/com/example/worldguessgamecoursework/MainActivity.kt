package com.example.worldguessgamecoursework

import android.content.Intent
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
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldguessgamecoursework.screens.AdvancedLevelActivity
import com.example.worldguessgamecoursework.screens.GuessTheCountryActivity
import com.example.worldguessgamecoursework.screens.GuessTheFlagActivity
import com.example.worldguessgamecoursework.screens.GuessTheHintActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(::nav)
        }
    }

    fun nav(activityName: String, countdown: Boolean) {   //Navigate using intents
        val intent = when (activityName) {
            "GuessTheCountryActivity" -> Intent(this, GuessTheCountryActivity::class.java).apply {putExtra("timer", countdown) }
            "GuessTheHintActivity" -> Intent(this, GuessTheHintActivity::class.java).apply {putExtra("timer", countdown) }
            "GuessTheFlagActivity" -> Intent(this, GuessTheFlagActivity::class.java).apply {putExtra("timer", countdown) }
            "AdvancedLevelActivity" -> Intent(this, AdvancedLevelActivity::class.java).apply {putExtra("timer", countdown) }

            else -> {
                null
            }
        }
        startActivity(intent)

    }
}

@Composable
fun HomeScreen(onClicked: (String, Boolean) -> Unit) {
    var countdown by remember  { mutableStateOf(false) }
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
                    modifier = Modifier.padding(10.dp),   //header
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
                modifier = Modifier.fillMaxWidth(),  //header
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Play and Win, Home Page",
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
            Spacer(modifier = Modifier.height(50.dp))// when button clicks navigate to guess the country page
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                Button(
                    onClick = {
                        onClicked("GuessTheCountryActivity", countdown)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF75A488)),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(200.dp),
                ) {
                    Text(
                        "Guess the Country",
                        fontSize = 17.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        item {   //// when button clicks navigate to guess the hints page
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                Button(
                    onClick = {
                        onClicked("GuessTheHintActivity", countdown)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF75A488)),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(200.dp),
                ) {
                    Text(
                        "Guess the Hint",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        item {  //// when button clicks navigate to guess the flag Activity
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                Button(
                    onClick = {
                        onClicked("GuessTheFlagActivity", countdown)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF75A488)),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(200.dp),
                ) {
                    Text(
                        "Guess the Flag",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        item {
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                Button(
                    onClick = {
                        onClicked("AdvancedLevelActivity", countdown)

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF75A488)),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(200.dp),
                ) {
                    Text(
                        "Advanced level",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        item {
            // switch is enable / disable
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                androidx.compose.material3.Switch(
                    checked = countdown,
                    onCheckedChange = { countdown = it },
                    colors = androidx.compose.material3.SwitchDefaults.colors(
                        checkedThumbColor = Color.Green,
                        uncheckedThumbColor = Color.Red,
                    ),
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Switch ON",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

    }
}



