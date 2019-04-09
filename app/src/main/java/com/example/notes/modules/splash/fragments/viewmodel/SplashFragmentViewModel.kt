package com.example.notes.modules.splash.fragments.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import com.example.notes.core.base.BaseViewModel
import com.example.notes.modules.splash.fragments.repository.SplashRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class SplashFragmentViewModel (private val context: Context, val repository: SplashRepository) : BaseViewModel(context) {
    val splashTimerLiveData = MutableLiveData<Int>()
    var oneTimeRequest: OneTimeWorkRequest = repository.getOneTimeWorkRequest()

    fun init() {
        launch(context = coroutineContext) {
            val waiting = repository.waitingOnSplashScreen()
            waiting.await()

            if (isActive) {
                Toast.makeText(context, "hello world from", Toast.LENGTH_SHORT).show()
                splashTimerLiveData.postValue(0)
            }

            delay(2000)
            repository.workManager.enqueue(oneTimeRequest)
        }
    }
}