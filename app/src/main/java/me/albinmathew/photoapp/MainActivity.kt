package me.albinmathew.photoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme // Use androidx.compose.material3.MaterialTheme if using M3
import androidx.compose.material.Surface // Use androidx.compose.material3.Surface if using M3
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import me.albinmathew.photoapp.ui.search.PhotoSearchScreen // Placeholder for actual screen
import me.albinmathew.photoapp.ui.theme.PhotoAppTheme // Placeholder for app theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoAppTheme { // AppTheme will be defined later
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background // Use MaterialTheme.colorScheme.background for M3
                ) {
                    // Later this will be replaced by a NavHost
                    PhotoSearchScreen()
                }
            }
        }
    }
}
