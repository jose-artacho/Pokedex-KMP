package util

const val VIEW_ERROR = "View Error"

sealed interface UiState<out T> {

    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error<out T>(val message: String) : UiState<T>
}