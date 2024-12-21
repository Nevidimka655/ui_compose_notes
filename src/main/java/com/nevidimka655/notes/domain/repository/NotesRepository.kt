package com.nevidimka655.notes.domain.repository

import androidx.paging.PagingData
import com.nevidimka655.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun deleteById(id: Long)

    suspend fun update(id: Long, name: String, text: String)

    suspend fun insert(name: String, text: String)

    suspend fun getById(id: Long): Note

    suspend fun getByPage(pageSize: Int, pageIndex: Int): List<Note>

    fun listOrderDescAsc(): Flow<PagingData<Note>>

}