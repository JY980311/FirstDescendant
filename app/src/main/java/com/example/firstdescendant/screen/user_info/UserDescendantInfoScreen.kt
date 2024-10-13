package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.firstdescendant.data.user.descendantinfo.UserDescendantData
import com.example.firstdescendant.data.user.descendantinfo.UserModule
import com.example.firstdescendant.data.user.module.UserModuleInfo
import com.example.firstdescendant.ui.theme.moduleBorderColor
import com.example.firstdescendant.ui.theme.moduleCenterColor
import com.example.firstdescendant.ui.theme.rareColor
import com.example.firstdescendant.ui.theme.specialModColor
import com.example.firstdescendant.ui.theme.standardColor
import com.example.firstdescendant.ui.theme.transcendentColor

@Composable
fun UserDescendantInfoScreen(
    userDescendantInfo: UserDescendantData,
    userModules: List<UserModule>,
    userModulesInfo: List<UserModuleInfo>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "사용자 이름 : ${userDescendantInfo.user_name}")
        Text(text = "계승자 이름 : ${userDescendantInfo.descendant_id}")
        Text(text = "계승자 레벨 : ${userDescendantInfo.descendant_level}")
        Text(text = "계승자 슬롯 위치 : ${userDescendantInfo.descendant_slot_id}")
        Text(text = "모듈 용량 : ${userDescendantInfo.module_capacity}")
        Text(text = "모듈 최대 용량 : ${userDescendantInfo.module_max_capacity}")
        Text(text = "모듈 : ")

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