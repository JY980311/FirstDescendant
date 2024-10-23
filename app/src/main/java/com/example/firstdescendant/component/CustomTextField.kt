package com.example.firstdescendant.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstdescendant.R
import com.example.firstdescendant.ui.theme.DescendantTypography
import com.example.firstdescendant.ui.theme.focusedBorderColor
import com.example.firstdescendant.ui.theme.textFieldInnerColor
import com.example.firstdescendant.ui.theme.textFieldPlaceholderColor
import com.example.firstdescendant.ui.theme.unFocusedBorderColor

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused = interactionSource.collectIsFocusedAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(53.dp)
            .border(1.dp, if(focused) focusedBorderColor else unFocusedBorderColor, RoundedCornerShape(8.dp))
            .background(textFieldInnerColor)
            .padding(6.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // Placeholder는 사용자가 글자를 입력하기 전까지 보이도록 설정
        if (value.isEmpty()) {
            Text(
                text = "abcd#1234",
                style = DescendantTypography.placeholderText
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            interactionSource = interactionSource,
            textStyle = DescendantTypography.textFieldText,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            minLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions( onDone = {
                keyboardController?.hide()
            }
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        var value by remember { mutableStateOf("") }

        CustomTextField(
            value = value,
            onValueChange = { value = it }
        )
    }
}
