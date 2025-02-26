package com.nevidimka655.notes.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.domain.notes.repository.SettingsRepository
import io.gromif.crypto.tink.model.KeysetTemplates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
): SettingsRepository {
    private val aeadKey = intPreferencesKey("aead")
    override fun getAeadModeFlow(): Flow<AeadMode> {
        return dataStore.data.map {
            val aeadIndex = it[aeadKey] ?: -1
            when {
                aeadIndex == -1 -> AeadMode.None
                else -> AeadMode.Template(
                    id = aeadIndex,
                    name = KeysetTemplates.AEAD.entries[aeadIndex].name
                )
            }
        }
    }

    override suspend fun getAeadMode(): AeadMode {
        return getAeadModeFlow().first()
    }

    override suspend fun setAeadMode(aeadMode: AeadMode) {
        val aeadIndex = when(aeadMode) {
            AeadMode.None -> -1
            is AeadMode.Template -> aeadMode.id
        }
        dataStore.edit {
            it[aeadKey] = aeadIndex
        }
    }
}