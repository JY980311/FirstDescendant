package com.example.thefirstdescendantlink.screen.user_info

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.thefirstdescendantlink.R
import com.example.thefirstdescendantlink.component.ImageBox
import com.example.thefirstdescendantlink.component.NameBox
import com.example.thefirstdescendantlink.component.WeaponEnchantLevelBox
import com.example.thefirstdescendantlink.data.user.module.UserWeaponModuleInfo
import com.example.thefirstdescendantlink.data.user.weapon.UserWeaponModule
import com.example.thefirstdescendantlink.data.user.weapon.UserWeapon
import com.example.thefirstdescendantlink.screen.viewmodel.UserScreenViewModel
import com.example.thefirstdescendantlink.ui.theme.DescendantContentText
import com.example.thefirstdescendantlink.ui.theme.DescendantTypography
import com.example.thefirstdescendantlink.ui.theme.moduleBorderColor
import com.example.thefirstdescendantlink.ui.theme.moduleCenterColor
import com.example.thefirstdescendantlink.ui.theme.rareColor
import com.example.thefirstdescendantlink.ui.theme.specialModColor
import com.example.thefirstdescendantlink.ui.theme.standardColor
import com.example.thefirstdescendantlink.ui.theme.weaponGuideLineColor

@Composable
fun UserWeaponInfoScreen(
    viewModel: UserScreenViewModel,
) {
    val userWeaponInfo by viewModel.userWeaponInfo.collectAsStateWithLifecycle()
    val userWeapon by viewModel.userWeapon.collectAsStateWithLifecycle()
    val userWeaponModulesInfo by viewModel.userWeaponModule.collectAsStateWithLifecycle()

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
                    append("WEAPON")
                }
                withStyle(SpanStyle(fontSize = 33.sp)) {
                    append(" INFO")
                }
            },
            style = DescendantTypography.headLineText
        )

        if (userWeaponInfo.weapon.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_weapon_info),
                    style = DescendantTypography.mainTitleText
                )
            }
        } else {
            for (i in 0 until userWeaponInfo.weapon.size) { //indices 유효한 범위 반환
                Column(
                    modifier = Modifier.padding(top = 30.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = userWeapon[i].weapon_name,
                                style = DescendantTypography.weaponMainText
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                text = "Lv.${userWeaponInfo.weapon[i].weapon_level}",
                                style = DescendantTypography.weaponLevelText
                            )
                        }
                    }
                    ImageBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        imageUrl = userWeapon[i].image_url,
                        tier = userWeapon[i].weapon_tier
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        WeaponEnchantLevelBox(
                            userWeaponInfo.weapon[i].perk_ability_enchant_level
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(DescendantContentText.mainTitleText) {
                                    append("${stringResource(R.string.unique_ability_upgrade_level)} : ")
                                }
                                withStyle(DescendantContentText.mainContentText) {
                                    append("${userWeaponInfo.weapon[i].perk_ability_enchant_level ?: stringResource(
                                        id = R.string.no_level
                                    )}")
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                NameBox(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = stringResource(R.string.weapon_option)
                )
                if (userWeaponInfo.weapon[i].weapon_additional_stat.isNullOrEmpty()) {
                    Text(
                        text = stringResource(R.string.no_additional_stat),
                        style = DescendantTypography.mainTitleText
                    )
                } else {
                    userWeaponInfo.weapon[i].weapon_additional_stat?.take(4)?.forEach { stat ->
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp),
                            text = buildAnnotatedString {
                                withStyle(DescendantContentText.mainTitleText) {
                                    append("${stat.additional_stat_name} = ")
                                }
                                withStyle(DescendantContentText.mainContentText) {
                                    append(stat.additional_stat_value)
                                }
                            }
                        )
                        //가로선 추가
                        Spacer(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(weaponGuideLineColor)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp), //모듈 자체에 padding 8이 들어감
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NameBox(
                        text = stringResource(id = R.string.module_info)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(DescendantContentText.mainTitleText) {
                                    append("${stringResource(R.string.current_capacity)} : ")
                                }
                                withStyle(DescendantContentText.mainContentText) {
                                    append("${userWeaponInfo.weapon[i].module_capacity}")
                                }
                            }
                        )

                        Text(
                            text = buildAnnotatedString {
                                withStyle(DescendantContentText.mainTitleText) {
                                    append("${stringResource(R.string.max_capacity)} : ")
                                }
                                withStyle(DescendantContentText.mainContentText) {
                                    append("${userWeaponInfo.weapon[i].module_max_capacity}")
                                }
                            }
                        )
                    }
                }
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    item {
                        WeaponModuleLayout(
                            userWeapon = userWeaponInfo.weapon[i],
                            weaponModuleInfo = userWeaponModulesInfo
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
    weaponModuleInfo: List<UserWeaponModuleInfo>
) {
    //궁극
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
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.nanum_square_r))
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
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = "${matchingModule?.module_name ?: "Unknown"}(${module.module_enchant_level})",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.nanum_square_r)),
                        textAlign = TextAlign.Center
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
    weaponModuleInfo: List<UserWeaponModuleInfo>
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

@Preview(showBackground = true)
@Composable
fun UserWeaponInfoScreenPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        WeaponModuleBox(
            weaponModule =
            listOf(
                UserWeaponModule(
                    module_id = 1,
                    module_slot_id = "1",
                    module_enchant_level = 1
                )
            ),
            weaponModuleInfo =
            listOf(
                UserWeaponModuleInfo(
                    main_module_id = 1,
                    module_name = "모듈 이름",
                    module_tier = "희귀",
                    image_url = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg"
                )
            )
        )
    }
}