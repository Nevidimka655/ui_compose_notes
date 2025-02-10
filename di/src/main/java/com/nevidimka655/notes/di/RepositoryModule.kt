package com.nevidimka655.notes.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.nevidimka655.astracrypt.notes.db.NoteItemEntity
import com.nevidimka655.astracrypt.notes.db.NotesDao
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.paging.PagingProvider
import com.nevidimka655.domain.notes.repository.Repository
import com.nevidimka655.domain.notes.repository.SettingsRepository
import com.nevidimka655.notes.data.mappers.DataToDomainMapper
import com.nevidimka655.notes.data.paging.PagingProviderImpl
import com.nevidimka655.notes.data.repository.RepositoryImpl
import com.nevidimka655.notes.data.repository.RepositoryProviderImpl
import com.nevidimka655.notes.data.repository.SettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.gromif.astracrypt.utils.Mapper

@Module
@InstallIn(ViewModelComponent::class)
internal object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideRepositoryProvider(
        repositoryImpl: Repository,
        //settingsDataStoreManager: SettingsDataStoreManager
    ): RepositoryProviderImpl = RepositoryProviderImpl(
        repository = repositoryImpl,
        //aeadInfoFlow = settingsDataStoreManager.aeadInfoFlow
    )

    @ViewModelScoped
    @Provides
    fun provideRepositoryImpl(
        dao: NotesDao,
        dataToDomainMapper: Mapper<NoteItemEntity, Note>
    ): Repository = RepositoryImpl(
        dao = dao,
        dataToDomainMapper = dataToDomainMapper
    )

    @ViewModelScoped
    @Provides
    fun provideSettingsRepository(
        @NotesDataStore dataStore: DataStore<Preferences>
    ): SettingsRepository = SettingsRepositoryImpl(dataStore = dataStore)

    @ViewModelScoped
    @Provides
    fun providePagingProvider(
        repositoryProviderImpl: RepositoryProviderImpl
    ): PagingProvider = PagingProviderImpl(
        repositoryProviderImpl = repositoryProviderImpl
    )

    @ViewModelScoped
    @Provides
    fun provideDataToDomainMapper(): Mapper<NoteItemEntity, Note> = DataToDomainMapper()

}