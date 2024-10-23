package com.example.thefirstdescendantlink.screen.user_info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.thefirstdescendantlink.R
import com.example.thefirstdescendantlink.component.ImageBox
import com.example.thefirstdescendantlink.component.NameBox
import com.example.thefirstdescendantlink.data.user.descendantinfo.UserModule
import com.example.thefirstdescendantlink.data.user.module.UserDescendantModuleInfo
import com.example.thefirstdescendantlink.screen.viewmodel.UserScreenViewModel
import com.example.thefirstdescendantlink.ui.theme.DescendantContentText
import com.example.thefirstdescendantlink.ui.theme.DescendantTypography
import com.example.thefirstdescendantlink.ui.theme.moduleBorderColor
import com.example.thefirstdescendantlink.ui.theme.moduleCenterColor
import com.example.thefirstdescendantlink.ui.theme.rareColor
import com.example.thefirstdescendantlink.ui.theme.specialModColor
import com.example.thefirstdescendantlink.ui.theme.standardColor
import com.example.thefirstdescendantlink.ui.theme.transcendentColor

@Composable
fun UserDescendantInfoScreen(
    viewModel: UserScreenViewModel,
) {
    val userDescendantInfo by viewModel.userDescendantInfo.collectAsStateWithLifecycle()
    val userDescendant by viewModel.userDescendant.collectAsStateWithLifecycle()
    val userDescendantModule by viewModel.userDescendantModule.collectAsStateWithLifecycle()
    val userModules = userDescendantInfo.module

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
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("DESCENDANT")
                }
                withStyle(SpanStyle(fontSize = 33.sp)){
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )
        Row(
            modifier = Modifier
                .padding(top = 30.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ){
            ImageBox(
                modifier = Modifier
                    .height(280.dp),
                imageUrl = userDescendant.descendant_image_url
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(DescendantContentText.mainTitleText) {
                            append("${stringResource(R.string.user_name)} : ")
                        }
                        withStyle(DescendantContentText.mainContentText){
                            append(userDescendantInfo.user_name)
                        }
                    }
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(DescendantContentText.mainTitleText) {
                            append("${stringResource(R.string.descendant_name)} : ")
                        }
                        withStyle(DescendantContentText.mainContentText){
                            append(userDescendant.descendant_name)
                        }
                    }
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(DescendantContentText.mainTitleText) {
                            append("${stringResource(R.string.descendant_level)} : ")
                        }
                        withStyle(DescendantContentText.mainContentText){
                            append(userDescendantInfo.descendant_level.toString())
                        }
                    }
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            NameBox(text = stringResource(R.string.module_info))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text = buildAnnotatedString {
                        withStyle(DescendantContentText.mainTitleText) {
                            append("${stringResource(R.string.current_capacity)} : ")
                        }
                        withStyle(DescendantContentText.mainContentText){
                            append(userDescendantInfo.module_capacity.toString())
                        }
                    }
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(DescendantContentText.mainTitleText) {
                            append("${stringResource(R.string.max_capacity)} : ")
                        }
                        withStyle(DescendantContentText.mainContentText){
                            append(userDescendantInfo.module_max_capacity.toString())
                        }
                    }
                )
            }
        }


        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            item {
                ModuleLayout(
                    userModules = userModules,
                    userModulesInfo = userDescendantModule
                )
            }
        }
    }
}

/** 모듈 정보를 보여줄 1개의 박스 */
@Composable
fun ModuleBox(
    userModules: List<UserModule>,
    userModulesInfo: List<UserDescendantModuleInfo>,
) {
    val transcendentModule = arrayOf(
        0.1f to transcendentColor,
        0.4f to moduleCenterColor,
        0.5f to moduleCenterColor,
        0.6f to moduleCenterColor,
        0.9f to transcendentColor
    )

    val specialModule = arrayOf(
        0.1f to specialModColor,
        0.4f to moduleCenterColor,
        0.5f to moduleCenterColor,
        0.6f to moduleCenterColor,
        0.9f to specialModColor
    )

    val rareModule = arrayOf(
        0.1f to rareColor,
        0.4f to moduleCenterColor,
        0.5f to moduleCenterColor,
        0.6f to moduleCenterColor,
        0.9f to rareColor
    )

    val standardModule = arrayOf(
        0.1f to standardColor,
        0.4f to moduleCenterColor,
        0.5f to moduleCenterColor,
        0.6f to moduleCenterColor,
        0.9f to standardColor
    )

    if (userModules.isEmpty()) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp, top = 8.dp, end = 8.dp)
                .size(100.dp)
                .border(1.dp, moduleBorderColor, RoundedCornerShape(8.dp))
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Empty(${stringResource(R.string.empty)})",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.nanum_square_r))
            )
        }
    } else {
        userModules.forEach { module ->
            val matchingModule = userModulesInfo.find { it.main_module_id == module.module_id }
            val colorGradient = when (matchingModule?.module_tier) {
                "초월" -> transcendentModule
                "궁극" -> specialModule
                "희귀" -> rareModule
                else -> standardModule
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 8.dp, top = 8.dp, end = 8.dp)
                            .size(100.dp)
                            .background(
                                Brush.linearGradient(colorStops = colorGradient),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(1.dp, moduleBorderColor, RoundedCornerShape(8.dp))
                    ) {
                        AsyncImage(
                            model = matchingModule?.image_url ?: "",
                            contentDescription = stringResource(R.string.module_image),
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = (matchingModule?.module_name?: "Unknown") + "(${module.module_enchant_level})",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.nanum_square_r)),
                    )
                }
            }
        }
    }
}

/** 모듈의 순서를 잡아줄 레이아웃 */
@Composable
fun ModuleLayout(
    userModules: List<UserModule>,
    userModulesInfo: List<UserDescendantModuleInfo>
) {
    val skillModule = userModules.filter { module -> module.module_slot_id == "Skill 1" }
    val subModule = userModules.filter { module -> module.module_slot_id == "Sub 1" }
    val mainModules = userModules
        .filter { module -> module.module_slot_id.startsWith("Main ") }
        .sortedBy { module ->
            module.module_slot_id.replace("Main ", "").toIntOrNull() ?: 0
        }

    Column {
        val sortedMainUserModules = mutableListOf<UserModule?>().apply {
            for (i in 1..10) {
                val module = mainModules.find { it.module_slot_id == "Main $i" }
                add(module)
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            ModuleBox(
                userModules = skillModule,
                userModulesInfo = userModulesInfo,
            )
            for (i in listOf(0, 2, 4, 6, 8)) {
                sortedMainUserModules[i]?.let { module ->
                    ModuleBox(
                        userModules = listOf(module),
                        userModulesInfo = userModulesInfo,
                    )
                } ?: ModuleBox(
                    userModules = emptyList(),
                    userModulesInfo = userModulesInfo,
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            ModuleBox(
                userModules = subModule,
                userModulesInfo = userModulesInfo,
            )
            for (i in listOf(1, 3, 5, 7, 9)) {
                sortedMainUserModules[i]?.let { module ->
                    ModuleBox(
                        userModules = listOf(module),
                        userModulesInfo = userModulesInfo,
                    )
                } ?: ModuleBox(
                    userModules = emptyList(),
                    userModulesInfo = userModulesInfo,
                )
            }
        }
    }
}