package com.nevidimka655.notes.data.mappers

import android.text.format.DateFormat
import com.nevidimka655.notes.data.database.NoteItemEntity
import com.nevidimka655.notes.domain.model.Mapper
import com.nevidimka655.notes.domain.model.Note
import com.nevidimka655.notes.domain.model.NoteState

private const val TIME_PATTERN = "d MMMM yyyy"

class NoteItemEntityMapper: Mapper<NoteItemEntity, Note> {

    override fun invoke(item: NoteItemEntity): Note {
        return Note(
            id = item.id,
            name = item.name ?: "",
            text = item.text ?: "",
            textPreview = item.textPreview?.run { "$this..." } ?: "",
            state = NoteState.entries[item.state],
            creationTime = DateFormat.format(TIME_PATTERN, item.creationTime).toString()
        )
    }

}