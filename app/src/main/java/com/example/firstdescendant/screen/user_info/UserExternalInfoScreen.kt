package com.example.firstdescendant.screen.user_info

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.firstdescendant.component.CustomImageBox
import com.example.firstdescendant.component.NameBox
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
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Text(
            text = buildAnnotatedString {
                append("EXTERNAL COMPONENT")
                withStyle(SpanStyle(fontStyle = DescendantTypography.subHeadLineText.fontStyle)){
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )

        Spacer(modifier = Modifier.height(60.dp))

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
                CustomImageBox(
                    modifier = Modifier.size(250.dp, 170.dp).padding(top = 4.dp),
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

            NameBox(text = "외장부품 옵션")
            Text(text = userExternalInfo.external_component[i].external_component_additional_stat.map { "${it.additional_stat_name} : ${it.additional_stat_value}\n" }.joinToString(""))
        }
    }
}