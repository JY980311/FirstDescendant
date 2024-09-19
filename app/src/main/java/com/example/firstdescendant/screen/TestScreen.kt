package com.example.firstdescendant.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import com.example.firstdescendant.network.RetrofitClient
import com.example.firstdescendant.screen.viewmodel.TestScreenViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

@Composable
fun TestScreen(
    viewModel: TestScreenViewModel
) {
    val reactor = viewModel.all_reactor.collectAsState()

    Column {
        Button(onClick = { viewModel.getReactorData() }) {
            Text(text = "Get Reactor Data")
        }

        Text(text = "Reactor ID : ${reactor.value}")
    }
}

@Preview(showBackground = true)
@Composable
fun TestScreen2Preview() {
    val viewModel = TestScreenViewModel()
    TestScreen(viewModel = viewModel)
}
