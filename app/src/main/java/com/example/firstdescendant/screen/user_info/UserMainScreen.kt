package com.example.firstdescendant.screen.user_info

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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.firstdescendant.component.CustomBoxButton
import com.example.firstdescendant.component.CustomTextField
import com.example.firstdescendant.navigation.WEAPONINFOSCREEN_ROUTE
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel
import com.example.firstdescendant.ui.theme.DescendantTypography
import kotlinx.coroutines.delay

@Composable
fun UserMainScreen(
    navHostController: NavHostController,
    viewModel: TestScreenViewModel
) {

    val ouid = viewModel.test.collectAsStateWithLifecycle()

    val textField = viewModel.textField.collectAsStateWithLifecycle()

    val errorMessage = viewModel.errorMessage.collectAsStateWithLifecycle()

    val isLoading = viewModel.isLoading.collectAsStateWithLifecycle()

    val nextScreenRoute = viewModel.nextScreenRoute.collectAsStateWithLifecycle()

    var disableScreen by remember { mutableStateOf(true) }

    LaunchedEffect(nextScreenRoute.value) {
        if (nextScreenRoute.value != null) {
            disableScreen = false
            navHostController.navigate(nextScreenRoute.value!!)
            viewModel.resetNextScreenRoute()

            delay(500)
            disableScreen = true
        }
    }

    /*LaunchedEffect(nextScreenRoute.value) {
        nextScreenRoute.value?.let {
            navHostController.navigate(it)
            viewModel.resetNextScreenRoute()

            // 화면 전환 후 잠시 대기 후 로딩 상태 해제
            delay(500)
            // 필요에 따라 추가 로직을 처리
        }
    }*/



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
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontSize = 18.sp))
                {
                    append(" THE ")
                }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("FIRST")
                    append("\nDESCENDANT")
                }
            },
            style = DescendantTypography.headLineText
        )

        CustomTextField(
            value = textField.value,
            onValueChange = { viewModel.getText(it) }
        )

        CustomBoxButton(
            modifier = Modifier.height(50.dp),
            onClick = {
                Log.d("UI", "사용자 조회 버튼 클릭됨")
                viewModel.getOuid()
            },
            text = "사용자 조회"
        )

        if (ouid.value.ouid.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomBoxButton(
                    modifier = Modifier,
                    onClick = {
                        viewModel.getUserBasicInfo()
                    },
                    text = "사용자 기본 정보 조회",
                    enabled =!isLoading.value
                )

                CustomBoxButton(
                    modifier = Modifier,
                    onClick = {
                        viewModel.getUserDescendantInfo()
                    },
                    text = "장착 계승자 정보 조회",
                    enabled = !isLoading.value
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomBoxButton(
                    onClick = {
                        viewModel.getUserWeaponInfo()
                    },
                    text = "장착 무기 정보 조회",
                    enabled = !isLoading.value
                )

                CustomBoxButton(
                    onClick = {
                        viewModel.getUserReactorInfo()
                    },
                    text = "장착 반응로 정보 조회",
                    enabled = !isLoading.value
                )
            }

            CustomBoxButton(
                onClick = {
                    viewModel.getUserExternalInfo()
                },
                text = "장착 외장부품 정보 조회",
                enabled = !isLoading.value
            )
        } else if (
            errorMessage.value.isNotEmpty() && !isLoading.value
        ) {
            Text(
                text = errorMessage.value,
                color = Color.Red
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
