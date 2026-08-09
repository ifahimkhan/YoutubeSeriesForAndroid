package com.fahim.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fahim.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {

            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        OutlinedTextField(value = number1, onValueChange = { number1 = it })
        OutlinedTextField(value = number2, onValueChange = { number2 = it })
        Row {
            Button(onClick = {
                result = (number1.toDouble() + number2.toDouble()).toString()
            }) {
                Text("+")
            }
            Button(onClick = {
                result = (number1.toDouble() - number2.toDouble()).toString()
            }) { Text("-") }

            Button(onClick = {
                result = (number1.toDouble() * number2.toDouble()).toString()
            }) { Text("*") }
            Button(onClick = {
                result = (number1.toDouble() / number2.toDouble()).toString()
            }) { Text("/") }
        }


        Text(result)
    }
}

@Preview
@Composable
private fun CalculatorScreenPreview() {
    CalculatorTheme() {
        CalculatorScreen()
    }
}