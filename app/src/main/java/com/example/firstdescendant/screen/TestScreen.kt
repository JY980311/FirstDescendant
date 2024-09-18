package com.example.firstdescendant.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.firstdescendant.data.user.basicinfo.UserBasic
import com.example.firstdescendant.data.user.descendantinfo.UserModule
import com.example.firstdescendant.data.user.descendantinfo.UserDescendant
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel
import com.example.firstdescendant.ui.theme.moduleBorderColor
import com.example.firstdescendant.ui.theme.moduleCenterColor
import com.example.firstdescendant.ui.theme.rareColor
import com.example.firstdescendant.ui.theme.specialModColor
import com.example.firstdescendant.ui.theme.standardColor
import com.example.firstdescendant.ui.theme.transcendentColor
import com.example.firstdescendant.util.UserModuleMapping

@Composable
fun TestScreen(
    viewModel: TestScreenViewModel
) {

    val ouid = viewModel.test.collectAsStateWithLifecycle()

    val basicInfo = viewModel.basicInfo.collectAsStateWithLifecycle()

    val descendantInfo = viewModel.descendantInfo.collectAsStateWithLifecycle()

    val textField = viewModel.textField.collectAsStateWithLifecycle()

    val errorMessage = viewModel.errorMessage.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        OutlinedTextField(
            value = textField.value,
            onValueChange = {
                viewModel.getText(it)
            },
        )

        Button(onClick = {
            viewModel.getOuid()
        }) {
            Text(text = "사용자 조회")
        }

        Text(text = if (errorMessage.value == "") ouid.value.ouid else errorMessage.value)

        Button(onClick = {
            viewModel.getBasicInfo()
        }) {
            Text(text = "사용자 기본 정보 조회")
        }

        Button(onClick = {
            viewModel.getDescendantInfo()
        }) {
            Text(text = "장착 계승자 정보 조회")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "장착 무기 정보 조회")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "장착 반응로 정보 조회")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "장착 외장부품 정보 조회")
        }

        if (basicInfo.value.user_name != "") {
            BasicInfoScreen(userBasic = basicInfo.value)
            DescendantInfoScreen(
                userDescendantInfo = descendantInfo.value,
                userDescendantName = viewModel.getCharacterNameById(descendantInfo.value.descendant_id)
            )
        }
    }
}

@Composable
fun BasicInfoScreen(
    userBasic: UserBasic,
) {
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

@Composable
fun DescendantInfoScreen(
    userDescendantInfo: UserDescendant,
    userDescendantName: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "사용자 이름 : ${userDescendantInfo.user_name}")
        Text(text = "계승자 이름 : $userDescendantName")
        Text(text = "계승자 레벨 : ${userDescendantInfo.descendant_level}")
        Text(text = "계승자 슬롯 위치 : ${userDescendantInfo.descendant_slot_id}")
        Text(text = "모듈 용량 : ${userDescendantInfo.module_capacity}")
        Text(text = "모듈 최대 용량 : ${userDescendantInfo.module_max_capacity}")
        //Text(text = "모듈 : ${userDescendantInfo.module}")
        Text(text = "모듈 : ")

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            item {
                ModuleLayout(userDescendantInfo = userDescendantInfo)
            }
        }
    }
}

/** 모듈 정보를 보여줄 1개의 박스 */
@Composable
fun ModuleBox(
    userModules: List<UserModule>
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp, top = 8.dp, end = 8.dp)
                        .size(100.dp)
                        .background(
                            Brush.linearGradient(
                                colorStops =
                                when (UserModuleMapping.getModuleTierById(listOf(module.module_id))) {
                                    listOf("초월") -> transcendentModule
                                    listOf("전설") -> specialModule
                                    listOf("희귀") -> rareModule
                                    else -> standardModule
                                },
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(1.dp, moduleBorderColor, RoundedCornerShape(8.dp))
                ) {
                    AsyncImage(
                        model = UserModuleMapping.getModuleImageUrlById(listOf(module.module_id))
                            .joinToString(""), // [ ] 없는 하나의 String 값으로 출력
                        contentDescription = "모듈 이미지",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
                Row {
                    Text(
                        text = (UserModuleMapping.getModuleNameById(listOf(module.module_id))
                            .firstOrNull() + " (${module.module_enchant_level})"),
                        fontSize = 8.sp
                    )
                }
            }
        }
        Log.d("ModuleBox", "modules: $userModules")
    }
}

/** 모듈의 순서를 잡아줄 레이아웃 */
@Composable
fun ModuleLayout(
    userDescendantInfo: UserDescendant
) {
    val skillModule =
        userDescendantInfo.module.filter { module -> module.module_slot_id == "Skill 1" }
    val subModule =
        userDescendantInfo.module.filter { module -> module.module_slot_id == "Sub 1" }
    val mainModules = userDescendantInfo.module
        .filter { module -> module.module_slot_id.startsWith("Main ") }
        .sortedBy { module ->
            module.module_slot_id.replace("Main ", "").toIntOrNull() ?: 0
        }

    Log.d("MainModules", mainModules.toString())

    Column {
        // 첫 번째 줄: Skill1 + Main1, Main3, Main5, Main7, Main9 모듈
        val sortedMainUserModules = mutableListOf<UserModule?>().apply {
            // Main1, Main2, Main3, ..., Main10 순서대로 배열 초기화
            for (i in 1..10) {
                val module = mainModules.find { it.module_slot_id == "Main $i" }
                add(module)
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            if (skillModule.isNotEmpty()) {
                ModuleBox(userModules = skillModule)
            } else {
                ModuleBox(userModules = emptyList())
            }
            // 홀수 번째 Main 모듈 (Main1, Main3, ...)
            for (i in listOf(0, 2, 4, 6, 8)) {
                sortedMainUserModules[i]?.let { module ->
                    ModuleBox(userModules = listOf(module))
                } ?: run {
                    ModuleBox(userModules = emptyList())  // 빈칸 처리
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            if (subModule.isNotEmpty()) {
                ModuleBox(userModules = subModule)
            } else {
                ModuleBox(userModules = emptyList())
            }
            // 짝수 번째 Main 모듈 (Main2, Main4, ...)
            for (i in listOf(1, 3, 5, 7, 9)) {
                sortedMainUserModules[i]?.let { module ->
                    ModuleBox(userModules = listOf(module))
                } ?: run {
                    ModuleBox(userModules = emptyList())  // 빈칸 처리
                }
            }
        }
    }
}

//TODO: 일단 보류
@Composable
fun LevelBox(
    level: Int
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        for (i in 1..level) {
            Box(
                modifier = Modifier
                    .size(5.dp, 2.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestScreenPreview() {
    TestScreen(TestScreenViewModel())
    /*Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LevelBox(10) 
    }*/
}