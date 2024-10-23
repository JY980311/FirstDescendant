package com.example.thefirstdescendantlink.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thefirstdescendantlink.R
import com.example.thefirstdescendantlink.ui.theme.mainBackgroundColor
import com.example.thefirstdescendantlink.ui.theme.moduleCenterColor
import com.example.thefirstdescendantlink.ui.theme.rareColor
import com.example.thefirstdescendantlink.ui.theme.specialModColor
import com.example.thefirstdescendantlink.ui.theme.standardColor

@Composable
fun ImageBox(
    modifier: Modifier = Modifier,
    imageUrl: String,
    tier: String? = null
) {
    //궁극
    val specialBorder = arrayOf(
        0.1f to specialModColor,
        0.4f to moduleCenterColor,
        0.5f to moduleCenterColor,
        0.6f to moduleCenterColor,
        0.9f to specialModColor
    )

    //희귀
    val rareBorder = arrayOf(
        0.1f to rareColor,
        0.4f to moduleCenterColor,
        0.5f to moduleCenterColor,
        0.6f to moduleCenterColor,
        0.9f to rareColor
    )

    //일반
    val standardBorder = arrayOf(
        0.1f to standardColor,
        0.4f to moduleCenterColor,
        0.5f to moduleCenterColor,
        0.6f to moduleCenterColor,
        0.9f to standardColor
    )

    val nullBorder = arrayOf(
        0.1f to Color.White,
        0.4f to Color.White,
        0.5f to Color.White,
        0.6f to Color.White,
        0.9f to Color.White
    )

    val tierBorder = when(tier){
        "궁극" -> specialBorder
        "희귀" -> rareBorder
        "일반" -> standardBorder
        else -> nullBorder
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .size(180.dp, 100.dp)
            .border(
                1.5f.dp,
                Brush.verticalGradient(colorStops = tierBorder),
                RoundedCornerShape(8.dp)
            )
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl,
            contentDescription = stringResource(R.string.data_image),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
    }
}