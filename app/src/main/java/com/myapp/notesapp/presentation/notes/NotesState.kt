package com.myapp.notesapp.presentation.notes

import com.myapp.notesapp.feature_note.domain.model.Note
import com.myapp.notesapp.feature_note.domain.util.NoteOrder
import com.myapp.notesapp.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
