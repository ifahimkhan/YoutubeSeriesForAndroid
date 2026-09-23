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
import androidx.compose.foundation.layout.statusBarsPadding
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
                var url by remember { mutableStateOf("https://google.com") }
                val state = rememberWebViewState(url)
                val navigator = rememberWebViewNavigator()
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                        .statusBarsPadding(),
                    topBar = {
                        Column(modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = url,
                                    onValueChange = { url = it },
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Button(onClick = { navigator.loadUrl(url) }) {
                                    Text(text = "Go")
                                }
                            }

                            when (val loadingState = state.loadingState) {
                                is LoadingState.Loading -> {
                                    LinearProgressIndicator(
                                        progress = { loadingState.progress },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }

                                else -> {}
                            }
                        }
                    },
                    bottomBar = {
                        BottomAppBar {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                IconButton(onClick = {navigator.navigateBack()},
                                    enabled = navigator.canGoBack) {
                                    Icon(Icons.Default.ArrowBack,contentDescription = null)
                                }
                                IconButton(onClick = {navigator.navigateForward()},
                                    enabled = navigator.canGoForward) {
                                    Icon(Icons.Default.ArrowForward,contentDescription = null)
                                }
                                IconButton(onClick = {navigator.reload()}){
                                    Icon(Icons.Default.Refresh,contentDescription = null)
                                }

                            }
                        }
                    }) { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) {
                        WebView(
                            state = state,
                            navigator = navigator,
                            onCreated = { webView ->
                                webView.settings.apply {
                                    javaScriptEnabled = true
                                    domStorageEnabled = true
                                    javaScriptCanOpenWindowsAutomatically = true
                                }
                            })
                    }
                }

            }

        }

    }
}

