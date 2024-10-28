package com.jjy9811.thefirstdescendantlink.screen.user_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jjy9811.thefirstdescendantlink.R
import com.jjy9811.thefirstdescendantlink.component.ImageBox
import com.jjy9811.thefirstdescendantlink.component.NameBox
import com.jjy9811.thefirstdescendantlink.screen.viewmodel.UserScreenViewModel
import com.jjy9811.thefirstdescendantlink.ui.theme.DescendantContentText
import com.jjy9811.thefirstdescendantlink.ui.theme.DescendantTypography

@Composable
fun UserExternalInfoScreen(
    viewModel: UserScreenViewModel,
) {
    val userExternalInfo by viewModel.userExternalInfo.collectAsStateWithLifecycle()
    val userExternal by viewModel.userExternal.collectAsStateWithLifecycle()
    val userExternalValue by viewModel.userExternalValue.collectAsStateWithLifecycle()
    val userExternalStat by viewModel.userExternalStat.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("EXTERNAL COMPONENT")
                }
                withStyle(SpanStyle(fontSize = 33.sp)) {
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )

        Spacer(modifier = Modifier.height(30.dp))

        for (i in 0 until userExternalInfo.external_component.size) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userExternal[i].external_component_name,
                    style = DescendantTypography.weaponMainText
                )
                ImageBox(
                    modifier = Modifier
                        .size(250.dp, 170.dp)
                        .padding(top = 4.dp),
                    imageUrl = userExternal[i].image_url,
                    tier = userExternal[i].external_component_tier
                )
                Text(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(top = 4.dp),
                    text = "LV.${userExternalInfo.external_component[i].external_component_level}",
                    style = DescendantTypography.weaponLevelText,
                    textAlign = TextAlign.Start
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                NameBox(text = stringResource(id = R.string.exterior_part_base_option))
                Text(
                    text = buildAnnotatedString {
                        withStyle(DescendantContentText.mainTitleText) {
                            append(
                                "${userExternalStat.find { it.stat_id == userExternalValue[i].stat_id }?.stat_name ?:stringResource(id = R.string.cannot_retrieve)} : "
                            )
                        }
                        withStyle(DescendantContentText.mainContentText) {
                            append(
                                userExternalValue.find { it.stat_id == userExternalValue[i].stat_id }?.stat_value
                                    ?: stringResource(id = R.string.cannot_retrieve)
                            )
                        }
                    },
                )

                NameBox(text = stringResource(id = R.string.exterior_part_random_option))
                if (userExternalInfo.external_component[i].external_component_additional_stat.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.cannot_retrieve),
                        style = DescendantTypography.mainContentText
                    )
                } else {
                    for (j in 0 until userExternalInfo.external_component[i].external_component_additional_stat.size) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(DescendantContentText.mainTitleText) {
                                    append(
                                        "${userExternalInfo.external_component[i].external_component_additional_stat[j].additional_stat_name} : "
                                    )
                                }
                                withStyle(DescendantContentText.mainContentText) {
                                    append(userExternalInfo.external_component[i].external_component_additional_stat[j].additional_stat_value)
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}