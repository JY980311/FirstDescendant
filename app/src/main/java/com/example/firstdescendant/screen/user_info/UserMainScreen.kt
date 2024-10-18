package com.example.firstdescendant.screen.user_info

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

    LaunchedEffect(isLoading.value) {
        if (!isLoading.value && nextScreenRoute.value != null) {
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
            .navigationBarsPadding()
            .pointerInput(disableScreen) {
                if (disableScreen && isLoading.value){
                    awaitPointerEventScope {
                        while (disableScreen) {
                            awaitPointerEvent()
                        }
                    }
                }
            },
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
                viewModel.getOuid()
            },
            text = "사용자 조회",
            enabled = !isLoading.value
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
                    enabled =!isLoading.value && disableScreen
                )

                CustomBoxButton(
                    modifier = Modifier,
                    onClick = {
                        viewModel.getUserDescendantInfo()
                    },
                    text = "장착 계승자 정보 조회",
                    enabled = !isLoading.value && disableScreen
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
                    enabled = !isLoading.value && disableScreen
                )

                CustomBoxButton(
                    onClick = {
                        viewModel.getUserReactorInfo()
                    },
                    text = "장착 반응로 정보 조회",
                    enabled = !isLoading.value && disableScreen
                )
            }

            CustomBoxButton(
                onClick = {
                    viewModel.getUserExternalInfo()
                },
                text = "장착 외장부품 정보 조회",
                enabled = !isLoading.value && disableScreen
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
