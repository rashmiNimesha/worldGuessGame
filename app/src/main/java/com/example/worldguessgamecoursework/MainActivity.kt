package com.example.worldguessgamecoursework

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldguessgamecoursework.screens.AdvancedLevelActivity
import com.example.worldguessgamecoursework.screens.ButtonDisplay

import com.example.worldguessgamecoursework.screens.GuessTheCountryActivity
import com.example.worldguessgamecoursework.screens.GuessTheFlagActivity
import com.example.worldguessgamecoursework.screens.GuessTheHintActivity
import com.example.worldguessgamecoursework.screens.HomeScreenBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            HomeScreen(::nav)

        }
    }

        fun nav (activityName: String){
            val intent = when(activityName){
                "GuessTheCountryActivity" -> Intent(this, GuessTheCountryActivity::class.java)
                "GuessTheHintActivity" -> Intent(this, GuessTheHintActivity::class.java)
                "GuessTheFlagActivity" -> Intent(this, GuessTheFlagActivity::class.java)
                "AdvancedLevelActivity" -> Intent(this, AdvancedLevelActivity::class.java)

                else -> {null}
            }
            startActivity(intent)

        }


    }

@Composable
fun HomeScreen(activityName: (String)-> Unit){
    Surface (modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier.padding(18.dp)){
            HomeScreenBar("Play & Win - Home Page")
            ButtonDisplay(activityName)



        }
    }

}



