package com.example.testcompose.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.testcompose.R

val fonts = FontFamily(
    Font(R.font.poppins_black),
    Font(R.font.poppins_light, weight = FontWeight.Light),
    Font(R.font.poppins_regular, weight = FontWeight.Normal),
    Font(R.font.poppins_bold, weight = FontWeight.Bold),
    Font(R.font.poppins_semibold, weight = FontWeight.SemiBold),
    Font(R.font.poppins_thin, weight = FontWeight.Thin)
)