package com.myapp.notesapp.presentation.authentication

sealed class AuthenticationEvent {
    data class OnPinChange(val value: String, val isDone: Boolean): AuthenticationEvent()
}