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

@Composable
fun HomeScreenBar(value: String) {
    LazyColumn{

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
                horizontalArrangement = Arrangement.Center){
                Text(text = value,
                    color = Color(0xFF75A488),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(alignment = Alignment.CenterVertically))

            }
        }


    }

}

@Composable
fun ButtonDisplay(onClicked: (String) -> Unit ){
    LazyColumn (modifier = Modifier
        .padding(vertical = 20.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){


        item {
            Row (modifier = Modifier.padding(vertical = 10.dp) ){
                Button(
                    onClick = {
                        onClicked("GuessTheCountryActivity")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF75A488)),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(200.dp),
                ) {
                    Text("Guess the Country",
                        fontSize = 17.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        item {
            Row (modifier = Modifier.padding(vertical = 10.dp)){
                Button(onClick = {
                    onClicked("GuessTheHintActivity")
                },
                    colors = ButtonDefaults.buttonColors(containerColor  = Color(0xFF75A488)),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(200.dp),
                ) {
                    Text("Guess the Hint",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium)
                }
            }
        }

        item {
            Row (modifier = Modifier.padding(vertical = 10.dp)){
                Button(onClick = {
                    onClicked("GuessTheFlagActivity")

                },
                    colors = ButtonDefaults.buttonColors(containerColor  = Color(0xFF75A488)),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(200.dp),
                ) {
                    Text("Guess the Flag",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium)
                }
            }
        }

        item {
            Row (modifier = Modifier.padding(vertical = 10.dp)){
                Button(onClick = {
                    onClicked("AdvancedLevelActivity")

                },
                    colors = ButtonDefaults.buttonColors(containerColor  = Color(0xFF75A488)),
                    modifier = Modifier
                        .padding(2.dp)
                        .width(200.dp),
                ) {
                    Text("Advanced level",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium)
                }
            }
        }
    }

}

// ============================== Guess The Country

@Composable
fun GuessTheCountry() {
    var randomFlagDisplay by remember { mutableStateOf(generateRandomFlag())}
    var selectedFlagName by remember{ mutableStateOf("") }
    var submitResult by remember { mutableStateOf("") }
    val correct = "CORRECT!"
    val wrong = "WRONG!"
    var showResult by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            Image(
                painter = painterResource(id = randomFlagDisplay.imagePath),
                contentDescription = randomFlagDisplay.flagName,
                modifier = Modifier
                    .size(250.dp)
                //.clip(shape = RoundedCornerShape(8.dp))
            )
        }
        // Display the flag image

item {
    if (!showResult) {
        Button(onClick = {

            submitResult = if (randomFlagDisplay.flagName == selectedFlagName) {
                correct
            } else {
                wrong
            }
            showResult = true

        },
            colors = ButtonDefaults.buttonColors(themeColor),
            modifier = Modifier
                .padding(2.dp)
                .width(150.dp)
                .height(40.dp)
        ) {
            Text("Submit",
                fontSize = buttonFontSize,
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = Color.White)
        }
    } else {
        Text(
            text = submitResult,
            color = if(submitResult == correct) Color.Green else Color.Red,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        if (submitResult == wrong) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Correct Answer : ${randomFlagDisplay.flagName}",
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            randomFlagDisplay = generateRandomFlag()
            selectedFlagName = ""
            submitResult = ""
            showResult = false
        },
            colors = ButtonDefaults.buttonColors(themeColor),
            modifier = Modifier
                .padding(2.dp)
                .width(150.dp)
                .height(40.dp)
        ) {
            Text("Next",
                color = Color.White,
                fontSize = buttonFontSize,
                fontWeight = FontWeight.Medium)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))

}

        item {

            // Display list of flag names
            LazyColumn() {
                item {
                    FlagData.flagsList.forEach { flag ->
                        Text(
                            text = flag.flagName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textDecoration = if (flag.flagName == selectedFlagName) TextDecoration.Underline else TextDecoration.None,
                            color = if (flag.flagName == selectedFlagName) Color.Blue else Color.Black,
                            modifier = Modifier
                                .clickable {
                                    if (!showResult) {
                                        selectedFlagName = flag.flagName

                                    }
                                }

                                .padding(vertical = 4.dp)


                        )
                    }
                }
            }
        }




    }
}

// Function to generate a random flag
fun generateRandomFlag(): Flag {
    return FlagData.flagsList.random()
}


//======================== hint


@Composable
fun GuessTheHint(){
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

    Column (modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(id = randomFlagDisplay.imagePath),
            contentDescription = randomFlagDisplay.flagName,
            modifier = Modifier
                .size(250.dp)
        )

        Text(
            text = countryNameHas.value,
            modifier = Modifier.padding(bottom = 36.dp)
        )

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

        // Button to submit guess
        Button(
            onClick = {


                if (guess.isNotBlank()) {
                    val guessedCountry = countryNameHas.value.replace(" ", "").lowercase()


                    if (guessedCountry.equals(countryName.lowercase())){
                        message ="Coorect"
                    }
                    else{
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
            Text("Submit",
                fontSize = buttonFontSize,
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = message)
        Spacer(modifier = Modifier.height(20.dp))
    }
}

//================ guess the flag

@Composable
fun GuessTheFlag() {
    var currentFlag by remember { mutableStateOf(FlagData.flagsList.random()) }
    var flagOptions by remember { mutableStateOf(generateOptions(currentFlag)) }
    var showMessage by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Find the ${currentFlag.flagName}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))


        flagOptions.forEach { flag_->
            FlagImage(
                flag = flag_,
                onClick = {
                    showMessage = true
                    message = if (flag_.flagName == currentFlag.flagName) {
                        "CORRECT !!"

                    } else {
                        "WRONG !! "

                    }
//                        currentFlag = FlagData.flagsList.random()
//                        currentOptions = generateOptions(currentFlag)
                }

            )

        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = message,
            color = if(message == "CORRECT !!") Color.Green else Color.Red,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
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
            Text(text = "Next",
                fontSize = buttonFontSize,
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = Color.White,
            )
        }
    }
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
fun AdvancedLevel(){
  //  var currentFlag by remember { mutableStateOf(FlagData.flagsList.random()) }
    var flagOptions by rememberSaveable { mutableStateOf(generateRandomFlags_()) }
    var guess by remember { mutableStateOf(List(flagOptions.size) { "" }) }
    val (guesses, setGuesses) = remember { mutableStateOf(List(flagOptions.size) { "" }) }
 var msg by remember { mutableStateOf("") }
    var submit by remember {
        mutableStateOf(false)
    }

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

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(40.dp))

        Row {

            flagOptions.forEachIndexed{index, flag_->
                FlagImageAdvanced(flag = flag_) {

                }

            }

        }

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
            }
        }
        Column (modifier = Modifier.align(Alignment.CenterHorizontally)){
            Button(onClick = {
                checkAnswers()
            }) {
                Text(text = "Submit")
            }
            Text(text = msg)
        }
    }
}




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
