package com.example.firstdescendant.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.firstdescendant.R

object DescendantTypography {
    val headLineText = TextStyle(
        fontFamily = FontFamily(Font(R.font.dohyeon)),
        fontWeight = FontWeight.Medium,
        fontSize = 40.sp,
        letterSpacing = 40.sp * (-0.006),
        color = Color.White
    )

    // HeadLine하고 7 차이 나게 설정함.
    val subHeadLineText = TextStyle(
        fontFamily = FontFamily(Font(R.font.dohyeon)),
        fontWeight = FontWeight.Medium,
        fontSize = 33.sp,
        letterSpacing = 33.sp * (-0.006),
        color = Color.White
    )

    val boxButtonText = TextStyle(
        fontFamily = FontFamily(Font(R.font.dohyeon)),
        fontWeight = FontWeight.Normal,
        lineHeight = 15.sp * 1.5,
        letterSpacing = 15.sp * (-0.006),
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

    val weaponMainText = TextStyle(
        fontFamily = FontFamily(Font(R.font.dohyeon)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 20.sp * 1.5,
        letterSpacing = 20.sp * (-0.006),
        color = Color.White
    )

    val weaponLevelText = TextStyle(
        fontFamily = FontFamily(Font(R.font.dohyeon)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 14.sp * 1.5,
        letterSpacing = 14.sp * (-0.006),
        color = Color.White
    )

    val mainTitleText = TextStyle(
        fontFamily = FontFamily(Font(R.font.nanum_square_eb)),
        fontSize = 15.sp,
        lineHeight = 15.sp * 1.5,
        letterSpacing = 15.sp * (-0.006),
        color = Color.White
    )

    val mainContentText = TextStyle(
        fontFamily = FontFamily(Font(R.font.nanum_square_r)),
        fontSize = 15.sp,
        lineHeight = 15.sp * 1.5,
        letterSpacing = 15.sp * (-0.006),
        color = Color.White
    )
}

object DescendantContentText {
    val mainTitleText = SpanStyle(
        fontFamily = FontFamily(Font(R.font.nanum_square_eb)),
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 15.sp * (-0.006),
        color = Color.White
    )

    val mainContentText = SpanStyle(
        fontFamily = FontFamily(Font(R.font.nanum_square_r)),
        fontSize = 15.sp,
        letterSpacing = 15.sp * (-0.006),
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