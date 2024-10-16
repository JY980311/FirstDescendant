package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel

@Composable
fun UserReactorInfoScreen(
    viewModel: TestScreenViewModel,
) {
    val userReactorInfo by viewModel.userReactorInfo.collectAsStateWithLifecycle()
    val userReactor by viewModel.userReactorImage.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = userReactor.image_url,
            contentDescription = "반응로 이미지"
        )

        Text(text = "반응로 이름 : ${userReactor.reactor_name}")
        Text(text = "반응로 레벨 : ${userReactorInfo.reactor_level}")
        Text(text = "반응로 강화 레벨 : ${userReactorInfo.reactor_enchant_level}")
        Text(text = "반응로 추가 능력 : ${userReactorInfo.reactor_additional_stat.mapIndexed { index, it -> "\n스텟[${index + 1}] 이름 : ${it.additional_stat_name}\n스텟[${index + 1}] 수치 : ${it.additional_stat_value}"}.joinToString(" ")}")
    }
}