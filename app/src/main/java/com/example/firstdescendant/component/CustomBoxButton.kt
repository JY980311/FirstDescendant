package com.example.firstdescendant.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstdescendant.R
import com.example.firstdescendant.ui.theme.DescendantTypography
import com.example.firstdescendant.ui.theme.mainButtonColor
import kotlinx.coroutines.delay

@Composable
fun CustomBoxButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    fontSize: Int? = null
) {

    val backgroundColor = if(enabled) mainButtonColor else Color.Red

    var isClicked by remember { mutableStateOf(true) }
    val clickDelay = 300L

    LaunchedEffect(isClicked) {
        if (!isClicked) {
            delay(clickDelay)
            isClicked = true
        }
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .width(160.dp)
            .height(100.dp)
            .background(color = backgroundColor)
            .clickable(enabled = isClicked && enabled) {
                if (isClicked) {
                    isClicked = false
                    onClick()
                }
            }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = text,
            style = DescendantTypography.boxButtonText,
            fontSize = fontSize?.sp ?: 15.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomBoxButtonPreview() {
    CustomBoxButton(
        text = "버튼",
        onClick = {}
    )
}