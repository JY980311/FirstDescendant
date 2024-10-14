package com.example.firstdescendant.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused = interactionSource.collectIsFocusedAsState().value

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .height(40.dp)
            .border(1.dp, if(focused) Color.Black else Color.Red, RoundedCornerShape(4.dp))
            .background(if(focused) Color.Gray else Color.Yellow)
            .padding(6.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // Placeholder는 사용자가 글자를 입력하기 전까지 보이도록 설정
        if (value.isEmpty()) {
            Text(
                text = "abcd#1234",
                color = Color.Black,
                fontSize = 12.sp
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            interactionSource = interactionSource,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        var value by remember { mutableStateOf("") }

        CustomTextField(
            value = value,
            onValueChange = { value = it }
        )
    }
}
