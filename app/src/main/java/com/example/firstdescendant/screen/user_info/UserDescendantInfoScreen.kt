package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.firstdescendant.component.DescendantImageBox
import com.example.firstdescendant.data.user.descendantinfo.UserDescendantData
import com.example.firstdescendant.data.user.descendantinfo.UserDescendantName
import com.example.firstdescendant.data.user.descendantinfo.UserModule
import com.example.firstdescendant.data.user.module.UserModuleInfo
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel
import com.example.firstdescendant.ui.theme.DescendantTypography
import com.example.firstdescendant.ui.theme.mainBackgroundColor
import com.example.firstdescendant.ui.theme.moduleBorderColor
import com.example.firstdescendant.ui.theme.moduleCenterColor
import com.example.firstdescendant.ui.theme.rareColor
import com.example.firstdescendant.ui.theme.specialModColor
import com.example.firstdescendant.ui.theme.standardColor
import com.example.firstdescendant.ui.theme.transcendentColor

@Composable
fun UserDescendantInfoScreen(
    viewModel: TestScreenViewModel,
) {
    val userDescendantInfo by viewModel.userDescendantInfo.collectAsStateWithLifecycle()
    val userDescendant by viewModel.userDescendant.collectAsStateWithLifecycle()
    val userModulesInfo by viewModel.userModule.collectAsStateWithLifecycle()
    val userModules = userDescendantInfo.module

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append("DESCENDANT")
                }
                withStyle(style = SpanStyle(fontSize = 18.sp)) {
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )
        Row(
            modifier = Modifier
                .padding(top = 60.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ){
            DescendantImageBox(
                modifier = Modifier
                    .height(280.dp),
                imageUrl = userDescendant.descendant_image_url
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = "사용자 이름 : ${userDescendantInfo.user_name}")
                Text(text = "계승자 이름 : ${userDescendant.descendant_name}")
                Text(text = "계승자 레벨 : ${userDescendantInfo.descendant_level}")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "[모듈]")

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(text = "현재 용량 : ${userDescendantInfo.module_capacity}")
                Text(text = "최대 용량 : ${userDescendantInfo.module_max_capacity}")
            }
        }


        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            item {
                ModuleLayout(userModules = userModules, userModulesInfo = userModulesInfo)
            }
        }
    }
}

/** 모듈 정보를 보여줄 1개의 박스 */
@Composable
fun ModuleBox(
    userModules: List<UserModule>,
    userModulesInfo: List<UserModuleInfo>
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
                text = "Empty(빈칸)",
                fontSize = 12.sp
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(100.dp)
                        .background(
                            Brush.linearGradient(colorStops = colorGradient),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(1.dp, moduleBorderColor, RoundedCornerShape(8.dp))
                ) {
                    AsyncImage(
                        model = matchingModule?.image_url ?: "",
                        contentDescription = "모듈 이미지",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
                Row {
                    Text(
                        text = (matchingModule?.module_name?: "Unknown") + "(${module.module_enchant_level})",
                        fontSize = 12.sp,
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
    userModulesInfo: List<UserModuleInfo>
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
            ModuleBox(userModules = skillModule, userModulesInfo = userModulesInfo)
            for (i in listOf(0, 2, 4, 6, 8)) {
                sortedMainUserModules[i]?.let { module ->
                    ModuleBox(userModules = listOf(module), userModulesInfo = userModulesInfo)
                } ?: ModuleBox(userModules = emptyList(), userModulesInfo = userModulesInfo)
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            ModuleBox(userModules = subModule, userModulesInfo = userModulesInfo)
            for (i in listOf(1, 3, 5, 7, 9)) {
                sortedMainUserModules[i]?.let { module ->
                    ModuleBox(userModules = listOf(module), userModulesInfo = userModulesInfo)
                } ?: ModuleBox(userModules = emptyList(), userModulesInfo = userModulesInfo)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDescendantInfoScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBackgroundColor)
    ) {
        UserDescendantInfoScreen(
            viewModel = TestScreenViewModel()
        )
    }
}