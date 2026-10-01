package com.fahim.projectseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fahim.projectseries.ui.theme.ProjectSeriesTheme

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectSeriesTheme {
                SimpleStorageAppScreen()
            }

        }

    }
}

@Composable
fun SimpleStorageAppScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(value = title, onValueChange = { title = it })
        OutlinedTextField(value = desc, onValueChange = { desc = it })
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { result = ExternalStorage.read() }) { Text("Read") }
            Button(onClick = { ExternalStorage.write( "$title\n$desc") }) { Text("Write") }
        }
        Text(
            result, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SimpleStorageAppScreenPreview() {
    ProjectSeriesTheme {
        SimpleStorageAppScreen()
    }
}













