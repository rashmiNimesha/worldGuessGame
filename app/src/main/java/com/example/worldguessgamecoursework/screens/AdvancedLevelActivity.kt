package com.example.worldguessgamecoursework.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
    Surface (modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier.padding(18.dp)){
            HomeScreenBar("Play & Win - Advanced Level")
            AdvancedLevel()


        }
    }

}