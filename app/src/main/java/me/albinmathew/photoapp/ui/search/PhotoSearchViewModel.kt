package me.albinmathew.photoapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.albinmathew.photoapp.app.api.ImageData // Assuming ImageData is the photo model
import me.albinmathew.photoapp.data.repository.PhotoRepository
import javax.inject.Inject

// Define a UI state class
data class PhotoSearchUiState(
    val isLoading: Boolean = false,
    val photos: List<ImageData> = emptyList(),
    val error: String? = null,
    val currentQuery: String = ""
)

@HiltViewModel
class PhotoSearchViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoSearchUiState())
    val uiState: StateFlow<PhotoSearchUiState> = _uiState.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(currentQuery = query)
    }

    fun searchPhotos() {
        if (_uiState.value.currentQuery.isBlank()) {
            // Optionally handle blank query (e.g., clear results or show message)
            _uiState.value = _uiState.value.copy(photos = emptyList(), error = "Search query cannot be empty.")
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                val response = photoRepository.searchPhotos(_uiState.value.currentQuery)
                // Assuming response.photos.photo is the list of ImageData
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    photos = response.photos?.photo ?: emptyList()
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Failed to fetch photos: ${e.message}"
                )
            }
        }
    }
}
