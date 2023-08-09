package com.myapp.notesapp.presentation.authentication

import android.annotation.SuppressLint
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.myapp.notesapp.presentation.authentication.components.PinTextField
import com.myapp.notesapp.presentation.util.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(
    navController: NavController,
    viewModel: AuthenticationViewModel = hiltViewModel()
) {
    val pinValue = viewModel.pinValue.value

    val scaffoldState = rememberScaffoldState()

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AuthenticationUiEvent.PinSuccess -> {
                    navController.navigate(
                        route = Screen.NotesScreen.route,
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(route = Screen.AuthenticationScreen.route, inclusive = true)
                            .build()
                    )
                }

                is AuthenticationUiEvent.ShowSnackBar -> {
                    keyboardController?.hide()
                    delay(timeMillis = 200)
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }

    Scaffold (
        scaffoldState = scaffoldState
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            PinTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                pinText = pinValue,
                onOtpTextChange = { otpText, isDone ->
                    viewModel.onEvent(AuthenticationEvent.OnPinChange(otpText, isDone))
                }
            )
        }
        SideEffect {
            focusRequester.requestFocus()
        }
    }
}