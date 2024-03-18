package com.example.worldguessgamecoursework.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.worldguessgamecoursework.screens.ui.theme.WorldGuessGameCourseworkTheme

class GuessTheFlagActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheFlagScreen()
        }
    }
}

@Composable
fun GuessTheFlagScreen(){
    Surface (modifier = Modifier.fillMaxSize()){
        LazyColumn (modifier = Modifier.padding(18.dp)){

            item {           HomeScreenBar("Play & Win - Guess The Flag")}

            item {
                GuessTheFlag()
            }



        }
    }
}