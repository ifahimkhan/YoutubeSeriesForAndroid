package com.fahim.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fahim.calculator.ui.theme.CalculatorTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            rememberComposable()
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun rememberComposable(modifier: Modifier = Modifier) {

    var state by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(state)
        OutlinedTextField(
            value = state,
            onValueChange = { state = it })
    }
}

@Preview
@Composable
private fun rememberComposablePreview() {

    rememberComposable()
}
