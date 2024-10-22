package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.firstdescendant.component.CustomImageBox
import com.example.firstdescendant.component.NameBox
import com.example.firstdescendant.component.ReactorEnchantLevelBox
import com.example.firstdescendant.data.user.reactor.UserReactorAdditionalStat
import com.example.firstdescendant.data.user.reactor.UserReactorData
import com.example.firstdescendant.data.user.reactor.UserReactorInfo
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel
import com.example.firstdescendant.ui.theme.DescendantTypography
import com.example.firstdescendant.ui.theme.mainBackgroundColor

@Composable
fun UserReactorInfoScreen(
    viewModel: TestScreenViewModel,
) {
    val userReactorInfo by viewModel.userReactorInfo.collectAsStateWithLifecycle()
    val userReactor by viewModel.userReactorImage.collectAsStateWithLifecycle()
    val userReactorSkillPower by viewModel.userReactorSkillPower.collectAsStateWithLifecycle()
    val reactorSkillCoefficient by viewModel.reactorCoefficient.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Text(
            text = buildAnnotatedString {
                append("REACTOR")
                withStyle(SpanStyle(fontStyle = DescendantTypography.subHeadLineText.fontStyle)){
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(bottom = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userReactor.reactor_name,
                    style = DescendantTypography.weaponMainText
                )
                CustomImageBox(
                    modifier = Modifier.height(200.dp),
                    imageUrl = userReactor.image_url,
                    tier = userReactor.reactor_tier
                )
            }

            Row(
                modifier = Modifier.width(180.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                ReactorEnchantLevelBox(userReactorInfo.reactor_enchant_level)
                Text(
                    text = "Lv.${userReactorInfo.reactor_level}",
                    style = DescendantTypography.weaponLevelText
                )
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(top = 4.dp)
            ) {

                Text(text = "스킬 위력 : ${userReactorSkillPower.skill_atk_power}")
                Text(text = "보조 공격 위력 : ${userReactorSkillPower.sub_skill_atk_power}")

                NameBox(
                    modifier= Modifier.padding(vertical = 4.dp),
                    text = "최적화 조건"
                )

                Text(text = userReactor.optimized_condition_type)


                NameBox(
                    modifier= Modifier.padding(vertical = 4.dp),
                    text = "스킬 위력 증가율"
                )
                
                if(reactorSkillCoefficient.isNotEmpty()) {
                    Text(text = "스킬 위력 증가율 [1] : ${reactorSkillCoefficient[0].coefficient_stat_id}")
                    Text(text = "스킬 위력 증가율 수치 [1] : ${reactorSkillCoefficient[0].coefficient_stat_value}")
                    Text(text = "스킬 위력 증가율 [2] : ${reactorSkillCoefficient[1].coefficient_stat_id}")
                    Text(text = "스킬 위력 증가율 수치 [2] : ${reactorSkillCoefficient[1].coefficient_stat_value}")
                }

                NameBox(
                    modifier= Modifier.padding(vertical = 4.dp),
                    text = "반응로 옵션"
                )

                Text(
                    text = userReactorInfo.reactor_additional_stat.joinToString(""){"${it.additional_stat_name}\n${it.additional_stat_value}\n"}
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF313131)
@Composable
fun UserReactorInfoScreenPreview() {
    val userReactorInfo = UserReactorData(
        ouid = "ouid",
        reactor_id = "리액터 이름",
        reactor_level = 100,
        reactor_enchant_level = 2,
        reactor_slot_id = "2",
        user_name = "",
        reactor_additional_stat = listOf(
            UserReactorAdditionalStat("스텟1", "100"),
            UserReactorAdditionalStat("스텟2", "200"),
        )
    )
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
            //style = DescendantTypography.headLineText
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "리액터 이름",
                    //style = DescendantTypography.weaponMainText
                )
                CustomImageBox(
                    modifier = Modifier.height(200.dp),
                    imageUrl = "https://open.api.nexon.com/static/tfd/img/133a00069f113afbdcd05b7bfc4c2c"
                )
            }

            Row(
                modifier = Modifier.width(180.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "2")
                Text(text = "Lv.100")
            }

            Spacer(modifier = Modifier.height(4.dp))

            NameBox(modifier = Modifier.padding(bottom = 4.dp),text = "반응로 추가 능력")


            Text(
                text = userReactorInfo.reactor_additional_stat.joinToString(""){"${it.additional_stat_name}\n${it.additional_stat_value}\n"}
            )
        }
    }
}

