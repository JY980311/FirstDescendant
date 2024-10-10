package com.example.firstdescendant.screen.user_info

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.example.firstdescendant.data.user.reactor.UserReactor
import com.example.firstdescendant.data.user.reactor.UserReactorImage
import com.example.firstdescendant.data.user.reactor.UserReactorName
import kotlinx.coroutines.delay

@Composable
fun UserReactorInfoScreen(
    userReactorInfo: UserReactor,
    userReactorImage: UserReactorImage
) {
    AsyncImage(
        model = userReactorImage.image_url,
        contentDescription = "반응로 이미지"
    )

    Text(text = "반응로 이름 : ${userReactorInfo.reactor_id}")
    Text(text = "반응로 레벨 : ${userReactorInfo.reactor_level}")
    Text(text = "반응로 강화 레벨 : ${userReactorInfo.reactor_enchant_level}")
    Text(text = "반응로 추가 능력 : ${userReactorInfo.reactor_additional_stat.mapIndexed { index, it -> "\n스텟[${index + 1}] 이름 : ${it.additional_stat_name}\n스텟[${index + 1}] 수치 : ${it.additional_stat_value}"}.joinToString(" ")}")
}