package com.jjy9811.thefirstdescendantlink.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjy9811.thefirstdescendantlink.ui.theme.DescendantTypography

@Composable
fun NameBox(
    modifier: Modifier = Modifier,
    text : String,
    fontSize : Int? = null
) {
    Box(
        modifier = modifier
            .border(1.dp, Color.White, RoundedCornerShape(8.dp))
    ){
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            style = DescendantTypography.mainTitleText.copy(fontSize = fontSize?.sp ?: 18.sp)
        )
    }
}