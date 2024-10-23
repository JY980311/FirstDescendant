package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.firstdescendant.R

@Composable
fun Test() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(
                    fontFamily = FontFamily(Font(R.font.nanum_square_r))
                )){
                    append(text = "닉네임 : ")
                }
                withStyle(SpanStyle(
                    fontFamily = FontFamily(Font(R.font.nanum_square_r))
                )){
                    append(text = "개미똥꾸멍")
                }
            },
            fontSize = 30.sp
        )

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(
                    fontFamily = FontFamily(Font(R.font.nanum_square_eb))
                )){
                    append(text = "닉네임 : ")
                }
                withStyle(SpanStyle(
                    fontFamily = FontFamily(Font(R.font.nanum_square_ac_r))
                )){
                    append(text = "개미똥꾸멍")
                }
            },
            fontSize = 30.sp
        )

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(
                    fontFamily = FontFamily(Font(R.font.dohyeon))
                )){
                    append(text = "닉네임 : ")
                }
                withStyle(SpanStyle(
                    fontFamily = FontFamily(Font(R.font.nanum_square_r))
                )){
                    append(text = "개미똥꾸멍")
                }
            },
            fontSize = 30.sp
        )

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(
                    fontFamily = FontFamily(Font(R.font.dohyeon)),
                    fontWeight = FontWeight.Bold
                )){
                    append(text = "닉네임 : ")
                }
                withStyle(SpanStyle(
                    fontFamily = FontFamily(Font(R.font.nanum_square_r))
                )){
                    append(text = "개미똥꾸멍")
                }
            },
            fontSize = 30.sp
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF313131)
@Composable
fun TestPreview() {
    Test()
}
