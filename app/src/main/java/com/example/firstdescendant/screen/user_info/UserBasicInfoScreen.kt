package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel
import com.example.firstdescendant.ui.theme.DescendantTypography

@Composable
fun UserBasicInfoScreen(
    viewModel: TestScreenViewModel,
) {
    val userBasic by viewModel.basicInfo.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Text(
            text = buildAnnotatedString {
                append("BASIC")
                withStyle(SpanStyle(fontStyle = DescendantTypography.subHeadLineText.fontStyle)) {
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )
        
        Spacer(modifier = Modifier.height(60.dp))

        Text(text = "닉네임 : ${userBasic.user_name}")
        Text(text = "게임에 사용하는 언어 : ${userBasic.game_language}")
        Text(text = "마스터리 레벨 : ${userBasic.mastery_rank_level}")
        Text(text = "마스터리 경험치 : ${userBasic.mastery_rank_exp}")
        Text(text = "사용하는 플랫폼 : ${userBasic.platform_type}")
        Text(text = "사용하는 OS 언어 : ${userBasic.os_language}")
    }
}
