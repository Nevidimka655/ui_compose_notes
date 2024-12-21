package com.nevidimka655.notes.domain.repository

import androidx.paging.PagingSource
import com.nevidimka655.notes.data.database.NoteItemEntity
import com.nevidimka655.notes.data.database.tuples.TransformNotesTuple
import com.nevidimka655.notes.domain.model.Note

interface NotesRepositoryDecorator {

    suspend fun deleteById(id: Long)

    suspend fun update(id: Long, name: String, text: String)

    suspend fun insert(name: String, text: String)

    suspend fun getById(id: Long): Note

    suspend fun updateTransform(transformNotesTuple: TransformNotesTuple)

    suspend fun getTransformItems(pageSize: Int, pageIndex: Int): List<TransformNotesTuple>

    fun listOrderDescAsc(): PagingSource<Int, NoteItemEntity>

}