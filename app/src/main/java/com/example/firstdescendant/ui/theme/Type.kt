package com.example.firstdescendant.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.firstdescendant.R

object DescendantTypography {
    val headLineText = TextStyle(
        fontFamily = FontFamily(Font(R.font.dohyeon)),
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp,
        lineHeight = 18.sp * 1.5,
        letterSpacing = 25.sp * (-0.006),
        color = Color.White
    )

    val boxButtonText = TextStyle(
        fontFamily = FontFamily(Font(R.font.dohyeon)),
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 13.sp * 1.5,
        letterSpacing = 13.sp * (-0.006),
        color = Color.Black
    )

    val textFieldText = TextStyle(
        fontFamily = FontFamily(Font(R.font.dohyeon)),
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 18.sp * 1.5,
        letterSpacing = 18.sp * (-0.006),
        color = Color.White,
        textAlign = TextAlign.Start
    )

    val placeholderText = TextStyle(
        fontFamily = FontFamily(Font(R.font.dohyeon)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 12.sp * 1.5,
        letterSpacing = 12.sp * (-0.006),
        color = textFieldPlaceholderColor
    )

    val bodyText = TextStyle(
        fontFamily = FontFamily(Font(R.font.dohyeon)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 16.sp * 1.5,
        letterSpacing = 16.sp * (-0.006),
        color = Color.White
    )
}

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)