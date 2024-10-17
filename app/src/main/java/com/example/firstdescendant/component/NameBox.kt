package com.example.firstdescendant.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NameBox(
    text : String
) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.White, RoundedCornerShape(8.dp))
    ){
        Text(
            modifier = Modifier.padding(4.dp),
            text = text
        )
    }
}