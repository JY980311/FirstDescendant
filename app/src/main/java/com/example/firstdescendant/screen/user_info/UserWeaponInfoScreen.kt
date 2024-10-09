package com.example.firstdescendant.screen.user_info

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
import androidx.compose.foundation.lazy.items
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
import com.example.firstdescendant.data.user.weapon.Module
import com.example.firstdescendant.data.user.weapon.UserWeapon
import com.example.firstdescendant.data.user.weapon.Weapon
import com.example.firstdescendant.ui.theme.moduleBorderColor
import com.example.firstdescendant.ui.theme.moduleCenterColor
import com.example.firstdescendant.ui.theme.rareColor
import com.example.firstdescendant.ui.theme.specialModColor
import com.example.firstdescendant.ui.theme.standardColor
import com.example.firstdescendant.util.ModuleMapping
import com.example.firstdescendant.util.WeaponMapping

@Composable
fun UserWeaponInfoScreen(
    userWeaponInfo: UserWeapon
) {
    val weaponID = userWeaponInfo.weapon.map { it.weapon_id }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        for(i in 0 until userWeaponInfo.weapon.size) {
            Text(text = "무기 ID(무기 이름) : ${WeaponMapping.getWeaponNameById(weaponID)?.getOrNull(i) ?: "이름 없음"}")
            Text(text = "무기 레벨 : ${userWeaponInfo.weapon[i].weapon_level}")
            Text(text = "고유 능력 강화 레벨 : ${userWeaponInfo.weapon[i].perk_ability_enchant_level ?: "레벨 없음"}")
            Text(text = "무기 강화 레벨 : ")
            if (userWeaponInfo.weapon[i].weapon_additional_stat.isNullOrEmpty()) {
                Text(text = "추가 스탯 없음")
            } else {
                /*for (a in 0..3) {
                    Text(
                        text = userWeaponInfo.weapon[i].run {
                            "${weapon_additional_stat?.getOrNull(a)?.additional_stat_name ?: "스탯 없음"} = ${weapon_additional_stat?.getOrNull(a)?.additional_stat_value ?: "값 없음"}"
                        }
                    )
                }*/
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
                /*items(userWeaponInfo.weapon) { weapon ->
                    WeaponModuleLayout(userWeapon = weapon)
                }*/
                item {
                    WeaponModuleLayout(userWeapon = userWeaponInfo.weapon[i])
                }
            }
        }
    }
}

/** 무기 모듈 정보를 보여줄 1개의 박스 */
@Composable
fun WeaponModuleBox(
    weaponModule: List<Module>
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
                                when (ModuleMapping.getModuleTierById(listOf(module.module_id))) {
                                    listOf("궁극") -> specialModule
                                    listOf("희귀") -> rareModule
                                    else -> standardModule
                                },
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(1.dp, moduleBorderColor, RoundedCornerShape(8.dp))
                ) {
                    AsyncImage(
                        model = ModuleMapping.getModuleImageUrlById(listOf(module.module_id))
                            .joinToString(""), // [ ] 없는 하나의 String 값으로 출력
                        contentDescription = "모듈 이미지",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
                Row {
                    Text(
                        text = (ModuleMapping.getModuleNameById(listOf(module.module_id))
                            .firstOrNull() + " (${module.module_enchant_level})"),
                        fontSize = 8.sp
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
    userWeapon: Weapon
) {

    val mainModules = userWeapon.module
        .sortedBy { module ->
            module.module_slot_id.toIntOrNull() ?: 0
        }

    Log.d("MainModules", mainModules.toString())

    Column {
        val sortedMainUserModules = mutableListOf<Module?>().apply {
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
                    WeaponModuleBox(weaponModule = listOf(module))
                } ?: run {
                    WeaponModuleBox(weaponModule = emptyList())  // 빈칸 처리
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            // 짝수 번째 무기 모듈 (2, 4, ...)
            for (i in listOf(1, 3, 5, 7, 9)) {
                sortedMainUserModules[i]?.let { module ->
                    WeaponModuleBox(weaponModule = listOf(module))
                } ?: run {
                    WeaponModuleBox(weaponModule = emptyList())  // 빈칸 처리
                }
            }
        }
    }
}