package com.myapp.notesapp.feature_note.data.repository

import android.icu.util.Calendar
import com.myapp.notesapp.feature_note.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl: AuthenticationRepository {
    override fun authenticate(passKey: String): Boolean {
        val calender = Calendar.getInstance()
        val hour = calender.get(Calendar.HOUR)
        val minute = calender.get(Calendar.MINUTE)
        val additionValue = hour % 10 + hour / 10 + minute % 10 + minute / 10

        val tempHour = (hour + additionValue) % 12
        val tempMinute = (minute + additionValue) % 60

        val newHour = if (tempHour == 0) 12 else tempHour
        val newMinute = if (tempMinute == 0) 60 else tempMinute

        val generatedPassKey = String.format("%02d%02d", newHour, newMinute)

        return passKey == generatedPassKey
    }
}