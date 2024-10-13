package com.example.firstdescendant.screen.user_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel

@Composable
fun UserMainScreen(
    viewModel: TestScreenViewModel
) {

    val ouid = viewModel.test.collectAsStateWithLifecycle()

    val basicInfo = viewModel.basicInfo.collectAsStateWithLifecycle()

    val descendantInfo = viewModel.descendantInfo.collectAsStateWithLifecycle()

    val userWeaponInfo = viewModel.userWeaponInfo.collectAsStateWithLifecycle()

    val userExternalInfo = viewModel.userExternalInfo.collectAsStateWithLifecycle()

    val userExternal = viewModel.userExternal.collectAsStateWithLifecycle()

    val userWeapon = viewModel.userWeapon.collectAsStateWithLifecycle()

    val userReactorInfo = viewModel.userReactorInfo.collectAsStateWithLifecycle()

    val userReactorImage = viewModel.userReactorImage.collectAsStateWithLifecycle()

    val reactorNameReady = viewModel.isReactorNameReady.collectAsStateWithLifecycle()

    val descendantNameReady = viewModel.isDescendantNameReady.collectAsStateWithLifecycle()

    val weaponNameReady = viewModel.isWeaponNameReady.collectAsStateWithLifecycle()

    val externalNameReady = viewModel.isExternalNameReady.collectAsStateWithLifecycle()

    val textField = viewModel.textField.collectAsStateWithLifecycle()

    val errorMessage = viewModel.errorMessage.collectAsStateWithLifecycle()

    val userModuleInfo by viewModel.userModule.collectAsStateWithLifecycle()


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

        Button(onClick = {
            viewModel.getUserWeaponInfo()
        }) {
            Text(text = "장착 무기 정보 조회")
        }

        Button(onClick = {
            viewModel.getUserReactorInfo()
        }) {
            Text(text = "장착 반응로 정보 조회")
        }

        Button(onClick = {
            viewModel.getUserExternalInfo()
        }) {
            Text(text = "장착 외장부품 정보 조회")
        }

        if (basicInfo.value.user_name != "") {
            UserBasicInfoScreen(userBasic = basicInfo.value)
        }
        if (descendantNameReady.value) {
            UserDescendantInfoScreen(
                userDescendantInfo = descendantInfo.value,
                userModules = descendantInfo.value.module,
                userModulesInfo = userModuleInfo
            )
            if (weaponNameReady.value) {
                UserWeaponInfoScreen(
                    userWeapon = userWeaponInfo.value,
                    userWeaponInfo = userWeapon.value,
                    userModulesInfo = userModuleInfo
                )
            }
            if (reactorNameReady.value) {
                UserReactorInfoScreen(
                    userReactorInfo = userReactorInfo.value,
                    userReactorImage = userReactorImage.value
                )
            }
            if (externalNameReady.value) {
                UserExternalInfoScreen(
                    userExternal = userExternal.value,
                    userExternalInfo = userExternalInfo.value
                )
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
}