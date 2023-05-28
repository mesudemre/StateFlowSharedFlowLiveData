package com.mesutemre.stateflowsharedflowlivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _liveData = MutableLiveData("LiveData Hello World")
    val liveData: LiveData<String> = _liveData

    private val _stateFlow = MutableStateFlow("StateFlow Hello World")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun runLiveData() {
        _liveData.value = "LiveData Mesut Emre"
    }

    fun runStateFlow() {
        _stateFlow.value = "StateFlow Mesut Emre"
    }

    fun runSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow Mesut Emre")
        }
    }
}