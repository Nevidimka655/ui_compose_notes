package com.nevidimka655.notes.data.repository.impl

import androidx.paging.PagingSource
import com.nevidimka655.notes.data.database.NoteItemEntity
import com.nevidimka655.notes.data.database.NotesDao
import com.nevidimka655.notes.data.database.tuples.TransformNotesTuple
import com.nevidimka655.notes.domain.repository.NotesRepository

class NotesRepositoryImpl(private val dao: NotesDao) : NotesRepository {
    override suspend fun deleteById(id: Long) {
        dao.deleteById(id = id)
    }

    private fun createNoteItemEntity(id: Long = 0L, name: String, text: String): NoteItemEntity {
        val trimmedName = name.trim().ifEmpty { null }
        val trimmedText = text.trim()
        val textPreview = if (trimmedText.isNotEmpty()) trimmedText.take(80) else null
        return NoteItemEntity(
            id = id,
            name = trimmedName,
            text = trimmedText,
            textPreview = textPreview,
            creationTime = System.currentTimeMillis()
        )
    }

    override suspend fun update(id: Long, name: String, text: String) {
        val noteItemEntity = createNoteItemEntity(id = id, name, text)
        dao.update(noteItemEntity = noteItemEntity)
    }

    override suspend fun insert(name: String, text: String) {
        val noteItemEntity = createNoteItemEntity(name = name, text = text)
        dao.insert(noteItemEntity = noteItemEntity)
    }

    override suspend fun getById(id: Long): NoteItemEntity {
        return dao.getById(id = id)
    }

    override suspend fun updateTransform(transformTuple: TransformNotesTuple) {
        dao.updateTransform(transformTuple = transformTuple)
    }

    override suspend fun getTransformItems(
        pageSize: Int, pageIndex: Int
    ): List<TransformNotesTuple> {
        return dao.getTransformItems(pageSize, pageIndex)
    }

    override fun listOrderDescAsc(): PagingSource<Int, NoteItemEntity> {
        return dao.listOrderDescAsc()
    }
}