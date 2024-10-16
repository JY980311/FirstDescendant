package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.firstdescendant.data.user.external.UserExternalData
import com.example.firstdescendant.data.user.external.UserExternalName
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel
import com.example.firstdescendant.ui.theme.DescendantTypography

@Composable
fun UserExternalInfoScreen(
    viewModel : TestScreenViewModel,
) {
    val userExternalInfo by viewModel.userExternalInfo.collectAsStateWithLifecycle()
    val userExternal by viewModel.userExternal.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append("EXTERNAL_COMPONENT")
                }
                withStyle(style = SpanStyle(fontSize = 18.sp)) {
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )

        Spacer(modifier = Modifier.height(60.dp))

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