package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel

@Composable
fun UserBasicInfoScreen(
    viewModel: TestScreenViewModel,
) {
    val userBasic by viewModel.basicInfo.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
    ) {
        Text(text = "닉네임 : ${userBasic.user_name}")
        Text(text = "게임에 사용하는 언어 : ${userBasic.game_language}")
        Text(text = "마스터리 레벨 : ${userBasic.mastery_rank_level}")
        Text(text = "마스터리 경험치 : ${userBasic.mastery_rank_exp}")
        Text(text = "사용하는 플랫폼 : ${userBasic.platform_type}")
        Text(text = "사용하는 OS 언어 : ${userBasic.os_language}")
    }
}
