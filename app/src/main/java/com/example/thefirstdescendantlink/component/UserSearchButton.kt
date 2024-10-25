package com.example.thefirstdescendantlink.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thefirstdescendantlink.ui.theme.DescendantTypography
import com.example.thefirstdescendantlink.ui.theme.mainButtonColor
import kotlinx.coroutines.delay

@Composable
fun UserSearchButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    fontSize: Int? = null
) {

    val backgroundColor = if(enabled) mainButtonColor else Color.Red

    var isClicked by remember { mutableStateOf(true) }
    val clickDelay = 300L

    val interactionSource = remember { MutableInteractionSource()}

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
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(
                    color = Color.Black
                ),
                enabled = enabled && isClicked,
                onClick = {
                    if (isClicked) {
                        isClicked = false
                        onClick()
                    }
                }
            )
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
    UserSearchButton(
        text = "버튼",
        onClick = {}
    )
}