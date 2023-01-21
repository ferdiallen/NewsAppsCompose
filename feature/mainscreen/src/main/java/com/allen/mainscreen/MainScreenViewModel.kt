package com.allen.mainscreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allen.core.preferences.ProfileImagePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val store: ProfileImagePreferences,
    @ApplicationContext private val context: Context
) : ViewModel() {
    var searchNews by mutableStateOf("")
        private set

    private var _selectedUserProfile = MutableStateFlow<Any?>(null)
    val selectedUserProfile = _selectedUserProfile.asStateFlow()

    init {
        initImagesIfAvailable()
    }

    private fun initImagesIfAvailable() {
        viewModelScope.launch {
            val data = store.readValue().first().takeIf {
                it!=""
            }
            data?.let {
                val base64Bitmap = Base64.decode(data, Base64.NO_PADDING)
                val image = BitmapFactory.decodeByteArray(base64Bitmap, 0, base64Bitmap.size)
                _selectedUserProfile.update {
                    image
                }
            }
        }
    }

    fun onNewsSearchChange(text: String) {
        searchNews = text
    }

    fun setProfilePicture(data: Uri?) {
        val resolver = context.contentResolver
        _selectedUserProfile.update {
            data
        }
        viewModelScope.launch {
            val resultBitmap: Bitmap? = try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(resolver, data as Uri))
                } else {
                    MediaStore.Images.Media.getBitmap(resolver, data as Uri)
                }
            } catch (e: Exception) {
                null
            }
            val byteArray = ByteArrayOutputStream()
            resultBitmap?.compress(Bitmap.CompressFormat.PNG, 80, byteArray)
            val outputByteArray = byteArray.toByteArray()
            val stringBitmap =
                Base64.encodeToString(outputByteArray, Base64.NO_PADDING)
            store.saveProfilePicture(stringBitmap)
        }
    }
}