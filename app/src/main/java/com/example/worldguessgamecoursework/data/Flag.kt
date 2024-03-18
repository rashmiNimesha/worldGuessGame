package com.example.worldguessgamecoursework.data

import com.example.worldguessgamecoursework.R

data class Flag(
    val flagName: String,
    val imagePath :Int)


object FlagData{
    val flagsList= listOf(

        Flag("Andona", R.drawable.ad),
        Flag("unitt", R.drawable.ae),
        Flag("fghh", R.drawable.af),
        Flag("Antia", R.drawable.ag),
        Flag("Anguila", R.drawable.ai)
        )
}
