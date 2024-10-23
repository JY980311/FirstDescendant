package com.example.thefirstdescendantlink.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thefirstdescendantlink.ui.theme.textFieldInnerColor

@Composable
fun WeaponEnchantLevelBox(
    level: Int? = null,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if(level == null){
            for (i in 0..3){
                UnLevelCircleBox()
            }
        } else {
            for (i in 0 until level){
                OnLevelCircleBox()
            }
            for (i in level until 4){
                UnLevelCircleBox()
            }
        }
    }
}

@Composable
fun OnLevelCircleBox(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(12.dp)
            .background(Color(0xFFFF562A))
    )
}

@Composable
fun UnLevelCircleBox(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(12.dp)
            .background(textFieldInnerColor)
    )
}


@Preview(showBackground = true)
@Composable
fun WeaponEnchantLevelBoxPreview() {
    WeaponEnchantLevelBox(
        null
    )
}