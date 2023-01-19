package com.allen.mainscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {
    var searchNews by mutableStateOf("")
        private set

    private var _selectedUserProfile = MutableStateFlow<Any?>(null)
    val selectedUserProfile = _selectedUserProfile.asStateFlow()

    fun onNewsSearchChange(text: String) {
        searchNews = text
    }

    fun setProfilePicture(data: Any?) {
        _selectedUserProfile.update {
            data
        }
    }
}