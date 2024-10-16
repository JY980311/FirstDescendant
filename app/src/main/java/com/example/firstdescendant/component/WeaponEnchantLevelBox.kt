package com.example.firstdescendant.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WeaponEnchantLevelBox(
    level: Int? = null,
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(40.dp)
            .background(Color(0xFFF16626))
    ){

    }
}