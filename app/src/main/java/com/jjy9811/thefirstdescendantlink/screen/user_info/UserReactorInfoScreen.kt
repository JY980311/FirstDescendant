package com.jjy9811.thefirstdescendantlink.screen.user_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jjy9811.thefirstdescendantlink.R
import com.jjy9811.thefirstdescendantlink.component.ImageBox
import com.jjy9811.thefirstdescendantlink.component.NameBox
import com.jjy9811.thefirstdescendantlink.component.ReactorEnchantLevelBox
import com.jjy9811.thefirstdescendantlink.screen.viewmodel.UserScreenViewModel
import com.jjy9811.thefirstdescendantlink.ui.theme.DescendantContentText
import com.jjy9811.thefirstdescendantlink.ui.theme.DescendantTypography

@Composable
fun UserReactorInfoScreen(
    viewModel: UserScreenViewModel,
) {
    val userReactorInfo by viewModel.userReactorInfo.collectAsStateWithLifecycle()
    val userReactor by viewModel.userReactorImage.collectAsStateWithLifecycle()
    val userReactorSkillPower by viewModel.userReactorSkillPower.collectAsStateWithLifecycle()
    val reactorSkillCoefficient by viewModel.reactorCoefficient.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
                    append("REACTOR")
                }
                withStyle(SpanStyle(fontSize = 33.sp)){
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
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
                ImageBox(
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 21.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(DescendantContentText.mainTitleText) {
                            append("${stringResource(R.string.skill_power)} : ")
                        }
                        withStyle(DescendantContentText.mainContentText){
                            append("${userReactorSkillPower.skill_atk_power}")
                        }
                    },
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(DescendantContentText.mainTitleText) {
                            append("${stringResource(R.string.sub_attack_power)} : ")
                        }
                        withStyle(DescendantContentText.mainContentText){
                            append("${userReactorSkillPower.sub_skill_atk_power}")
                        }
                    }
                )

                NameBox(
                    modifier= Modifier.padding(vertical = 4.dp),
                    text = stringResource(R.string.optimization_condition)
                )

                Text(
                    text = userReactor.optimized_condition_type,
                    style = DescendantTypography.mainContentText
                )


                NameBox(
                    modifier= Modifier.padding(vertical = 4.dp),
                    text = stringResource(R.string.skill_power_increase_rate)
                )
                
                if(reactorSkillCoefficient.isNotEmpty()) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(DescendantContentText.mainTitleText) {
                                append("${stringResource(R.string.skill_power_increase_rate)} [1] : ")
                            }
                            withStyle(DescendantContentText.mainContentText){
                                append(reactorSkillCoefficient[0].coefficient_stat_id)
                            }
                        }
                    )

                    Text(
                        text = buildAnnotatedString {
                            withStyle(DescendantContentText.mainTitleText) {
                                append("${stringResource(R.string.skill_power_increase_value)} [1] :")
                            }
                            withStyle(DescendantContentText.mainContentText){
                                append("${reactorSkillCoefficient[0].coefficient_stat_value}")
                            }
                        }
                    )

                    Text(
                        text = buildAnnotatedString {
                            withStyle(DescendantContentText.mainTitleText) {
                                append("${stringResource(R.string.skill_power_increase_rate)} [2] : ")
                            }
                            withStyle(DescendantContentText.mainContentText){
                                append(reactorSkillCoefficient[1].coefficient_stat_id)
                            }
                        }
                    )

                    Text(
                        text = buildAnnotatedString {
                            withStyle(DescendantContentText.mainTitleText) {
                                append("${stringResource(R.string.skill_power_increase_value)} [2] :")
                            }
                            withStyle(DescendantContentText.mainContentText){
                                append("${reactorSkillCoefficient[1].coefficient_stat_value}")
                            }
                        }
                    )
                }

                NameBox(
                    modifier= Modifier.padding(vertical = 4.dp),
                    text = stringResource(R.string.reactor_option)
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(DescendantContentText.mainTitleText) {
                            append(userReactorInfo.reactor_additional_stat[0].additional_stat_name)
                        }
                        withStyle(DescendantContentText.mainContentText){
                            append(" : ${userReactorInfo.reactor_additional_stat[0].additional_stat_value}")
                        }
                    }
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(DescendantContentText.mainTitleText) {
                            append(userReactorInfo.reactor_additional_stat[1].additional_stat_name)
                        }
                        withStyle(DescendantContentText.mainContentText){
                            append(" : ${userReactorInfo.reactor_additional_stat[1].additional_stat_value}")
                        }
                    }
                )
            }
        }
    }
}