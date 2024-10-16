package com.example.firstdescendant.screen.user_info

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.firstdescendant.component.DescendantImageBox
import com.example.firstdescendant.data.user.module.UserModuleInfo
import com.example.firstdescendant.data.user.weapon.UserWeaponModule
import com.example.firstdescendant.data.user.weapon.UserWeapon
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel
import com.example.firstdescendant.ui.theme.DescendantTypography
import com.example.firstdescendant.ui.theme.moduleBorderColor
import com.example.firstdescendant.ui.theme.moduleCenterColor
import com.example.firstdescendant.ui.theme.rareColor
import com.example.firstdescendant.ui.theme.specialModColor
import com.example.firstdescendant.ui.theme.standardColor

@Composable
fun UserWeaponInfoScreen(
    viewModel: TestScreenViewModel,
) {
    val userWeaponInfo by viewModel.userWeaponInfo.collectAsStateWithLifecycle()
    val userWeapon by viewModel.userWeapon.collectAsStateWithLifecycle()
    val userModulesInfo by viewModel.userModule.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append("WEAPON")
                }
                withStyle(style = SpanStyle(fontSize = 18.sp)) {
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )

        if (userWeaponInfo.weapon.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "무기 정보가 없습니다.")
            }
        } else {
            Spacer(modifier = Modifier.height(60.dp))

            for (i in 0 until userWeaponInfo.weapon.size) {
                Column(
                    modifier = Modifier.padding(vertical = 20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = userWeapon[i].weapon_name,
                                style = DescendantTypography.weaponMainText
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                text = "Lv.${userWeaponInfo.weapon[i].weapon_level.toString()}",
                                style = DescendantTypography.weaponLevelText
                            )
                        }
                    }
                    DescendantImageBox(
                        modifier = Modifier
                            .fillMaxWidth(),
                        imageUrl = userWeapon[i].image_url
                    )
                }
                Text(text = "고유 능력 강화 레벨 : ${userWeaponInfo.weapon[i].perk_ability_enchant_level ?: "레벨 없음"}")
                Text(text = "무기 최대 모듈 수용량 : ${userWeaponInfo.weapon[i].module_max_capacity}")
                Text(text = "무기 실제 적용된 수용량 : ${userWeaponInfo.weapon[i].module_capacity}")
                if (userWeaponInfo.weapon[i].weapon_additional_stat.isNullOrEmpty()) {
                    Text(text = "추가 스탯 없음")
                } else {
                    userWeaponInfo.weapon[i].weapon_additional_stat?.take(4)?.forEach { stat ->
                        Text(
                            text = "${stat.additional_stat_name} = ${stat.additional_stat_value}"
                        )
                    }
                }
                Text(text = "무기 모듈 : ")

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    item {
                        WeaponModuleLayout(
                            userWeapon = userWeaponInfo.weapon[i],
                            weaponModuleInfo = userModulesInfo
                        )
                    }
                }
            }
        }
    }
}


/** 무기 모듈 정보를 보여줄 1개의 박스 */
@Composable
fun WeaponModuleBox(
    weaponModule: List<UserWeaponModule>,
    weaponModuleInfo: List<UserModuleInfo>
) {
    //전설
    val specialModule = arrayOf(
        0.1f to specialModColor,
        0.4f to moduleCenterColor,
        0.5f to moduleCenterColor,
        0.6f to moduleCenterColor,
        0.9f to specialModColor
    )

    //희귀
    val rareModule = arrayOf(
        0.1f to rareColor,
        0.4f to moduleCenterColor,
        0.5f to moduleCenterColor,
        0.6f to moduleCenterColor,
        0.9f to rareColor
    )

    //일반
    val standardModule = arrayOf(
        0.1f to standardColor,
        0.4f to moduleCenterColor,
        0.5f to moduleCenterColor,
        0.6f to moduleCenterColor,
        0.9f to standardColor
    )

    if (weaponModule.isEmpty()) {
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
        weaponModule.forEach { module ->
            val matchingModule = weaponModuleInfo.find { it.main_module_id == module.module_id }
            val colorGradient = when (matchingModule?.module_tier) {
                "궁극" -> specialModule
                "희귀" -> rareModule
                else -> standardModule
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
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
                        model = matchingModule?.image_url ?: "", // [ ] 없는 하나의 String 값으로 출력
                        contentDescription = "모듈 이미지",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
                Row {
                    Text(
                        text = (matchingModule?.module_name
                            ?: "Unknown") + "(${module.module_enchant_level})",
                        fontSize = 12.sp
                    )
                }
            }
        }
        Log.d("WeaponModuleBox", "WeaponModules: $weaponModule")
    }
}

/** 무기 모듈의 순서를 잡아줄 레이아웃 */
@Composable
fun WeaponModuleLayout(
    userWeapon: UserWeapon,
    weaponModuleInfo: List<UserModuleInfo>
) {

    val mainModules = userWeapon.module
        .sortedBy { module ->
            module.module_slot_id.toIntOrNull() ?: 0
        }

    Log.d("MainModules", mainModules.toString())

    Column {
        val sortedMainUserModules = mutableListOf<UserWeaponModule?>().apply {
            // 1, 2, 3, ... 10 순서대로 배열 초기화
            for (i in 1..10) {
                val module = mainModules.find { it.module_slot_id == i.toString() }
                add(module)
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            // 홀수 번째 무기 모듈 (1, 3, ...)
            for (i in listOf(0, 2, 4, 6, 8)) {
                sortedMainUserModules[i]?.let { module ->
                    WeaponModuleBox(
                        weaponModule = listOf(module),
                        weaponModuleInfo = weaponModuleInfo
                    )
                } ?: run {
                    WeaponModuleBox(
                        weaponModule = emptyList(),
                        weaponModuleInfo = weaponModuleInfo
                    )  // 빈칸 처리
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            // 짝수 번째 무기 모듈 (2, 4, ...)
            for (i in listOf(1, 3, 5, 7, 9)) {
                sortedMainUserModules[i]?.let { module ->
                    WeaponModuleBox(
                        weaponModule = listOf(module),
                        weaponModuleInfo = weaponModuleInfo
                    )
                } ?: run {
                    WeaponModuleBox(
                        weaponModule = emptyList(),
                        weaponModuleInfo = weaponModuleInfo
                    )  // 빈칸 처리
                }
            }
        }
    }
}