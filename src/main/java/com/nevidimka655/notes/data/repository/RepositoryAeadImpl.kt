package com.nevidimka655.notes.data.repository

import androidx.paging.PagingData
import com.nevidimka655.notes.data.database.NotesDao
import com.nevidimka655.notes.data.mappers.DataToDomainMapper
import com.nevidimka655.notes.domain.model.Note
import com.nevidimka655.notes.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

internal class RepositoryAeadImpl(
    private val dao: NotesDao,
    private val dataToDomainMapper: DataToDomainMapper
): Repository {
    override suspend fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: Long, name: String, text: String) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(name: String, text: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): Note {
        TODO("Not yet implemented")
    }

    override suspend fun getByPage(
        pageSize: Int,
        pageIndex: Int
    ): List<Note> {
        TODO("Not yet implemented")
    }

    override fun listOrderDescAsc(): Flow<PagingData<Note>> {
        TODO("Not yet implemented")
    }
}