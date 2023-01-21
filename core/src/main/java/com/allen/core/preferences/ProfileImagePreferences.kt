package com.allen.core.preferences

import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileImagePreferences @Inject constructor(
    private val store: DataStore<Preferences>
) {
    private val keys = stringPreferencesKey("profile_photo")
    suspend fun saveProfilePicture(data: String) {
        store.edit {
            it[keys] = data
        }
    }

    fun readValue(): Flow<String> {
        return store.data.map {
            it[keys] ?: ""
        }
    }
}