package com.allen.boardingscreen

import androidx.lifecycle.ViewModel
import com.allen.core.preferences.ProfileImagePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BoardingViewModel @Inject constructor(

) : ViewModel() {
    private var _currentSelected = MutableStateFlow<Int?>(null)
    val currentSelected = _currentSelected.asStateFlow()

    fun pickedCategory(data: Int) {
        _currentSelected.update {
            data
        }
    }
}