package me.albinmathew.photoapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.albinmathew.photoapp.domain.model.Photo // Changed from ImageData
import me.albinmathew.photoapp.data.repository.PhotoRepository
import javax.inject.Inject
import java.io.IOException // Added for specific exception handling
import com.google.gson.JsonParseException // Added for specific exception handling

// Define a UI state class
data class PhotoSearchUiState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(), // Changed from ImageData
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
                val photosList = photoRepository.searchPhotos(_uiState.value.currentQuery)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    photos = photosList // response is now List<Photo>
                )
            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Network error: ${e.message}"
                )
            } catch (e: JsonParseException) { // Assuming Gson is still used for parsing by Retrofit
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Data parsing error: ${e.message}"
                )
            } catch (e: Exception) { // General fallback
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "An unexpected error occurred: ${e.message}"
                )
            }
        }
    }
}
