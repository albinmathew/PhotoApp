package me.albinmathew.photoapp.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed // Changed from items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme // For MaterialTheme.colors.error
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.albinmathew.photoapp.domain.model.Photo // Changed from ImageData

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
                    itemsIndexed(uiState.photos, key = { index, photo -> photo.id ?: index }) { index, photo ->
                        PhotoItem(photo)
                    }
                }
            }
        }
    }
}

@Composable
fun PhotoItem(photo: Photo) { // Changed parameter type
    // Placeholder for how a single photo item would be displayed
    // In a real app, you'd use Glide/Coil here with a Composable image loader
    Card {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // AsyncImage(model = photo.url, contentDescription = photo.title) // Use photo.url
            Text(
                text = photo.title, // Use photo.title (no longer needs ?: "Untitled" if non-nullable in domain model)
                style = MaterialTheme.typography.caption, // MaterialTheme.typography.labelSmall for M3
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
