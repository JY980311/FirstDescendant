package com.example.firstdescendant.component

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.firstdescendant.ui.theme.mainBackgroundColor

@Composable
fun DescendantImageBox(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .size(180.dp, 100.dp)
            .border(1.dp, Color.White, RoundedCornerShape(8.dp))
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl,
            contentDescription = "장착 계승자 이미지",
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DescendantImageBoxPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBackgroundColor)
    ) {
        DescendantImageBox(
            imageUrl = "https://open.api.nexon.com/static/tfd/img/133a00069f113afbdcd05b7bfc4c2cfa"
        )
    }
}