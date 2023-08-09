package com.myapp.notesapp.presentation.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.notesapp.feature_note.domain.use_case.Authenticate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val authenticate: Authenticate) : ViewModel() {
    private val _pinValue = mutableStateOf("")
    val pinValue: State<String> = _pinValue

    private val _eventFlow = MutableSharedFlow<AuthenticationUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AuthenticationEvent) {
        when(event) {
            is AuthenticationEvent.OnPinChange -> {
                _pinValue.value = event.value

                if (event.isDone) {
                    viewModelScope.launch {
                        val isAuthenticated = authenticate(_pinValue.value)
                        if (isAuthenticated) {
                            _eventFlow.emit(AuthenticationUiEvent.PinSuccess)
                        } else {
                            _pinValue.value = ""
                            _eventFlow.emit(AuthenticationUiEvent.ShowSnackBar(message = "Invalid pin"))
                        }
                    }
                }
            }
        }
    }
}