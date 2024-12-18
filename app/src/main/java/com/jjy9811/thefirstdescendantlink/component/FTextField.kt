package com.jjy9811.thefirstdescendantlink.component

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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jjy9811.thefirstdescendantlink.R
import com.jjy9811.thefirstdescendantlink.ui.theme.DescendantTypography
import com.jjy9811.thefirstdescendantlink.ui.theme.focusedBorderColor
import com.jjy9811.thefirstdescendantlink.ui.theme.textFieldInnerColor
import com.jjy9811.thefirstdescendantlink.ui.theme.unFocusedBorderColor

@Composable
fun FTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused = interactionSource.collectIsFocusedAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current
    val prefix = "#"
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(53.dp)
            .border(
                1.dp,
                if (focused || value != "") focusedBorderColor else unFocusedBorderColor,
                RoundedCornerShape(8.dp)
            )
            .background(textFieldInnerColor)
            .padding(6.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // Placeholder는 사용자가 글자를 입력하기 전까지 보이도록 설정
        if (value.isEmpty()) {
            Text(
                text = stringResource(id = R.string.placeholder_text),
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

        FTextField(
            value = value,
            onValueChange = { value = it }
        )
    }
}
