package com.fahim.projectseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.unit.dp
import com.fahim.projectseries.ui.theme.ProjectSeriesTheme
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectSeriesTheme {
                //Loading Pages showing progress
                val state = rememberWebViewState("https://www.google.com")
                // load current page and navigate back and forward
                val navigator = rememberWebViewNavigator()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 24.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp)
                            ) {
                                var urlText by remember { mutableStateOf("https://google.com/") }
                                OutlinedTextField(
                                    value = urlText, onValueChange = { urlText = it },
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Button(onClick = { navigator.loadUrl(urlText) }) {
                                    Text(text = "Go")
                                }
                            }
                            when (val loadingstate = state.loadingState) {
                                is LoadingState.Loading -> {
                                    LinearProgressIndicator(progress = loadingstate.progress)
                                }

                                else -> {}
                            }
                        }
                    },
                    bottomBar = {
                        BottomAppBar() {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                IconButton(onClick = {
                                    navigator.navigateBack()
                                }) {
                                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                                }
                                IconButton(onClick = {
                                    navigator.navigateForward()
                                }) {
                                    Icon(Icons.Filled.ArrowForward, contentDescription = "Forward")

                                }
                                IconButton(onClick = {
                                    navigator.reload()
                                }) {
                                    Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)) {
                        WebView(
                            modifier = Modifier.fillMaxSize(),
                            state = state,
                            navigator = navigator,
                            onCreated = { webView ->
                                webView.settings.javaScriptEnabled = true
                                webView.settings.domStorageEnabled= true

                            })

                    }
                }

            }
        }
    }
}

