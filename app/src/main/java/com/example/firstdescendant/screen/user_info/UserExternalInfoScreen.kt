package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.example.firstdescendant.data.user.external.UserExternalData
import com.example.firstdescendant.data.user.external.UserExternalName

@Composable
fun UserExternalInfoScreen(
    userExternalInfo : UserExternalData,
    userExternal : List<UserExternalName>
) {
    Column() {
        for (i in 0 until userExternalInfo.external_component.size) {
            AsyncImage(
                model = userExternal[i].image_url,
                contentDescription = "외장부품 이미지"
            )
            Text(text = "외장부품 이름 : ${userExternal[i].external_component_name}")
            Text(text = "외장부품 레벨 : ${userExternalInfo.external_component[i].external_component_level}")
            Text(text = "외장부품 추가 능력 : ${userExternalInfo.external_component[i].external_component_additional_stat.map { "\n${it.additional_stat_name} : ${it.additional_stat_value}" }.joinToString("")}")
        }
    }
}