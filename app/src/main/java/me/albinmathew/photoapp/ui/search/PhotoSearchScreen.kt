package me.albinmathew.photoapp.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.* // Use androidx.compose.material3.* for M3
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.albinmathew.photoapp.app.api.ImageData

@Composable
fun PhotoSearchScreen(
    viewModel: PhotoSearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold( // Basic screen structure
        topBar = {
            TopAppBar(title = { Text("Photo Search") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Search Input and Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = uiState.currentQuery,
                    onValueChange = { viewModel.onSearchQueryChanged(it) },
                    label = { Text("Search query") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { viewModel.searchPhotos() }) {
                    Text("Search")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Loading Indicator
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            // Error Message
            uiState.error?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colors.error, // MaterialTheme.colorScheme.error for M3
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            // Photos Grid
            if (!uiState.isLoading && uiState.error == null) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    contentPadding = PaddingValues(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.photos, key = { photo -> photo.id ?: photo.url_s ?: "" }) { photo ->
                        PhotoItem(photo)
                    }
                }
            }
        }
    }
}

@Composable
fun PhotoItem(photo: ImageData) {
    // Placeholder for how a single photo item would be displayed
    // In a real app, you'd use Glide/Coil here with a Composable image loader
    Card {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // AsyncImage(model = photo.url_s, contentDescription = photo.title) // Example with Coil
            Text(
                text = photo.title ?: "Untitled",
                style = MaterialTheme.typography.caption, // MaterialTheme.typography.labelSmall for M3
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
