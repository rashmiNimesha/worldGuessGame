package com.example.worldguessgamecoursework.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldguessgamecoursework.R
import com.example.worldguessgamecoursework.data.Flag
import com.example.worldguessgamecoursework.data.FlagData
import com.example.worldguessgamecoursework.data.buttonFontSize
import com.example.worldguessgamecoursework.data.themeColor






// Function to generate a random flag
fun generateRandomFlag(): Flag {
    return FlagData.flagsList.random()
}



@Composable
fun FlagImage(flag: Flag, onClick: () -> Unit,  ) {
    Image(
        painterResource(id = flag.imagePath),
        contentDescription = flag.flagName,
        Modifier
            .size(150.dp)
            .clickable(onClick = onClick),
    )
}




fun generateOptions(currentFlag_: Flag): List<Flag> {
    val options = mutableListOf(currentFlag_)
    while (options.size < 3) {
        val randomDog = FlagData.flagsList.random()
        if (randomDog !in options) {
            options.add(randomDog)
        }
    }
    return options.shuffled()
}

//=========================================================


@Composable
fun FlagImageAdvanced(flag: Flag, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = flag.imagePath),
        contentDescription = flag.flagName,
        modifier = Modifier
            .size(125.dp)
            .padding(4.dp),
     //   contentScale = ContentScale.Crop
        )
}

fun generateRandomFlags_(): List<Flag> {
    val flags = mutableListOf<Flag>()
    while (flags.size < 3) {
        val randomFlag = FlagData.flagsList.random()
        if (randomFlag !in flags) {
            flags.add(randomFlag)
        }
    }
    return flags
}
