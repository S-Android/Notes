package com.example.notes.core.workmanager

import androidx.work.WorkManager

class NotesWorkManager {
    var workManager: WorkManager? = null

    init {
        workManager = WorkManager.getInstance()
    }

    companion object {
        private var INSTANCE: NotesWorkManager? = null
        fun getNotesWorker(): NotesWorkManager? {
            synchronized(this) {
                if (INSTANCE == null) {
                   INSTANCE = NotesWorkManager()
                }
            }
            return INSTANCE
        }
    }
}