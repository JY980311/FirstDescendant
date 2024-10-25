package com.example.thefirstdescendantlink.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.thefirstdescendantlink.R
import com.example.thefirstdescendantlink.ui.theme.DescendantTypography
import kotlinx.coroutines.delay

@Composable
fun FButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    icon: Int,
    onClick: () -> Unit,

) {
    var isClicked by remember { mutableStateOf(true) }
    val clickDelay = 300L

    val fButtonBorderColor = arrayOf(
        0.1f to Color.White,
        0.4f to Color.Gray,
        0.5f to Color.Gray,
        0.6f to Color.Gray,
        0.9f to Color.White,
    )


    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(isClicked) {
        if (!isClicked) {
            delay(clickDelay)
            isClicked = true
        }
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                1.5.dp,
                Brush.horizontalGradient(colorStops = fButtonBorderColor),
                RoundedCornerShape(8.dp)
            )
            .background(Color.Transparent)
            .width(160.dp)
            .height(100.dp)
            .clickable(
                enabled = enabled && isClicked,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    color = Color.LightGray
                )
            ) {
                isClicked = false
                onClick()
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Image(
                modifier = Modifier,
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = stringResource(R.string.button_image)
            )
            Text(
                modifier = Modifier,
                text = text,
                style = DescendantTypography.boxButtonText,
                color = Color.White
            )
        }
    }
}