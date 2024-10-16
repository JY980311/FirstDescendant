package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.firstdescendant.component.CustomImageBox
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel
import com.example.firstdescendant.ui.theme.DescendantTypography

@Composable
fun UserReactorInfoScreen(
    viewModel: TestScreenViewModel,
) {
    val userReactorInfo by viewModel.userReactorInfo.collectAsStateWithLifecycle()
    val userReactor by viewModel.userReactorImage.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("REACTOR")
                }
                withStyle(style = SpanStyle(fontSize = 18.sp)) {
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userReactor.reactor_name,
                    style = DescendantTypography.weaponMainText
                )
                CustomImageBox(
                    modifier = Modifier.height(200.dp),
                    imageUrl = userReactor.image_url
                )
            }

            Text(text = "반응로 레벨 : ${userReactorInfo.reactor_level}")
            Text(text = "반응로 강화 레벨 : ${userReactorInfo.reactor_enchant_level}")
            Text(
                text = "반응로 추가 능력 : ${
                    userReactorInfo.reactor_additional_stat.mapIndexed { index, it -> "\n스텟[${index + 1}] 이름 : ${it.additional_stat_name}\n스텟[${index + 1}] 수치 : ${it.additional_stat_value}" }
                        .joinToString(" ")
                }"
            )
        }
    }
}