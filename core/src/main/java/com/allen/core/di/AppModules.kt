package com.allen.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.allen.core.preferences.ProfileImagePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    private const val PREFS_USER_IMAGE = "user_image"

    @Provides
    @Singleton
    fun providesDataStore(@ApplicationContext context: Context) = PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = {
            emptyPreferences()
        }), migrations = listOf(SharedPreferencesMigration(context, PREFS_USER_IMAGE)),
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob()), produceFile = {
            context.preferencesDataStoreFile(PREFS_USER_IMAGE)
        }
    )

    @Provides
    @Singleton
    fun providesProfilePreferences(store:DataStore<Preferences>) = ProfileImagePreferences(store)
}