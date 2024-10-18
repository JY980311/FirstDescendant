package com.example.firstdescendant.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstdescendant.ui.theme.textFieldInnerColor

@Composable
fun ReactorEnchantLevelBox(
    level: Int? = null,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if(level == null){
            for (i in 0..1){
                UnReactorLevelCircleBox()
            }
        } else {
            for (i in 0 until level){
                OnReactorLevelCircleBox()
            }
            for (i in level until 2){
                UnReactorLevelCircleBox()
            }
        }
    }
}

@Composable
fun OnReactorLevelCircleBox(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .clip(CutCornerShape(6.dp))
            .size(12.dp)
            .background(Color(0xFFFF562A))
    )
}

@Composable
fun UnReactorLevelCircleBox(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .clip(CutCornerShape(6.dp))
            .size(12.dp)
            .background(textFieldInnerColor)
    )
}


@Preview(showBackground = true)
@Composable
fun ReactorEnchantLevelBoxPreview() {
    ReactorEnchantLevelBox(
        0
    )
}