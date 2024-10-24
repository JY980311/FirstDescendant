package com.example.thefirstdescendantlink.screen.user_info

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.thefirstdescendantlink.R
import com.example.thefirstdescendantlink.component.UserSearchButton
import com.example.thefirstdescendantlink.component.FTextField
import com.example.thefirstdescendantlink.component.FButton
import com.example.thefirstdescendantlink.screen.viewmodel.UserScreenViewModel
import com.example.thefirstdescendantlink.ui.theme.DescendantContentText
import com.example.thefirstdescendantlink.ui.theme.DescendantTypography
import kotlinx.coroutines.delay

@Composable
fun UserMainScreen(
    navHostController: NavHostController,
    viewModel: UserScreenViewModel
) {

    val ouid = viewModel.test.collectAsStateWithLifecycle()

    val userBasic by viewModel.basicInfo.collectAsStateWithLifecycle()

    val textField = viewModel.textField.collectAsStateWithLifecycle()

    val errorMessage = viewModel.errorMessage.collectAsStateWithLifecycle()

    val isLoading = viewModel.isLoading.collectAsStateWithLifecycle()

    val nextScreenRoute = viewModel.nextScreenRoute.collectAsStateWithLifecycle()

    var disableScreen by remember { mutableStateOf(true) }

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(nextScreenRoute.value) {
        if (nextScreenRoute.value != null) {
            disableScreen = false
            navHostController.navigate(nextScreenRoute.value!!)
            viewModel.resetNextScreenRoute()

            delay(500)
            disableScreen = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontSize = 28.sp))
                {
                    append(" THE ")
                }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("FIRST")
                    append("\nDESCENDANT LINK")
                }
            },
            style = DescendantTypography.headLineText
        )

        FTextField(
            modifier = Modifier,
            value = textField.value,
            onValueChange = { viewModel.getText(it) }
        )

        UserSearchButton(
            modifier = Modifier
                .height(50.dp)
                .padding(bottom = 10.dp),
            onClick = {
                Log.d("UI", "사용자 조회 버튼 클릭됨")
                viewModel.getOuid()
                viewModel.getUserBasicInfo()
                keyboardController?.hide()
            },
            text = stringResource(R.string.user_lookup)
        )

        if (ouid.value.ouid.isNotEmpty()) {
            "닉네임 : ${userBasic.user_name}"
            Text(
                text = buildAnnotatedString {
                    withStyle(DescendantContentText.mainTitleText) {
                        append("${stringResource(R.string.nickname)} : ")
                    }
                    withStyle(DescendantContentText.mainContentText) {
                        append(userBasic.user_name)
                    }
                }
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(DescendantContentText.mainTitleText) {
                        append("${stringResource(R.string.game_language)} : ")
                    }
                    withStyle(DescendantContentText.mainContentText){
                        append(userBasic.game_language)
                    }
                }
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(DescendantContentText.mainTitleText) {
                        append("${stringResource(R.string.mastery_level)} : ")
                    }
                    withStyle(DescendantContentText.mainContentText){
                        append(userBasic.mastery_rank_level.toString())
                    }
                }
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(DescendantContentText.mainTitleText) {
                        append("${stringResource(R.string.platform_used)} : ")
                    }
                    withStyle(DescendantContentText.mainContentText){
                        append(userBasic.platform_type)
                    }
                }
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(DescendantContentText.mainTitleText) {
                        append("${stringResource(R.string.os_language)} : ")
                    }
                    withStyle(DescendantContentText.mainContentText){
                        append(userBasic.os_language)
                    }
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterHorizontally)
            ) {
                FButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.getUserDescendantInfo()
                    },
                    text = stringResource(R.string.equipped_descendant_info),
                    enabled = !isLoading.value,
                    icon = R.drawable.ic_descendant
                )

                FButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.getUserWeaponInfo()
                    },
                    text = stringResource(R.string.equipped_weapon_info),
                    enabled = !isLoading.value,
                    icon = R.drawable.ic_weapon
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =Arrangement.spacedBy(30.dp, Alignment.CenterHorizontally)
            ) {
                FButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.getUserReactorInfo()
                    },
                    text = stringResource(R.string.equipped_reactor_info),
                    enabled = !isLoading.value,
                    icon = R.drawable.ic_reactor
                )

                FButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.getUserExternalInfo()
                    },
                    text = stringResource(R.string.equipped_exterior_part_info),
                    enabled = !isLoading.value,
                    icon = R.drawable.ic_external
                )
            }
        } else if (
            errorMessage.value.isNotEmpty() && !isLoading.value
        ) {
            Text(
                text = errorMessage.value,
                color = Color.Red,
                style = DescendantTypography.mainTitleText
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.align(Alignment.Bottom),
                text = "* ",
                color = Color.LightGray,
                fontSize = 8.sp,
            )
            Text(
                text = stringResource(R.string.information),
                style = DescendantTypography.mainContentText,
                color = Color.LightGray,
                fontSize = 8.sp
            )
        }
    }

    if (isLoading.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = 0.5f)) //TODO: 색 지정하기
            ,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier,
                color = Color.White
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

@Preview(showBackground = true)
@Composable
fun Test1() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "* ",
            color = Color.LightGray,
            fontSize = 8.sp
        )
        Text(
            text = stringResource(R.string.information),
            color = Color.LightGray,
            fontSize = 8.sp
        )
    }
}
