package com.example.ui

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.room.dto.SettingDTO
import com.example.room.repository.SettingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(
    private val settingRepository: SettingRepository
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)

    val list = settingRepository.getAllFlow().stateIn(scope, SharingStarted.WhileSubscribed(), emptyList())

    fun save(key:String, value:String){
        scope.launch {
            settingRepository.save(SettingDTO(key, value))
        }
    }

    fun delete(key: String){
        scope.launch {
            settingRepository.delete(key)
        }
    }

}