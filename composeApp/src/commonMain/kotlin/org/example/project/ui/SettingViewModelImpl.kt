package org.example.project.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.example.project.databse.model.Setting
import org.example.project.databse.repository.SettingRepository

class SettingViewModelImpl(
    private val settingRepository: SettingRepository
) : SettingViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)

    override val list: StateFlow<List<Setting>> = settingRepository.readAllFlow()
        .stateIn(scope, SharingStarted.WhileSubscribed(), emptyList())


    override fun save(key: String, value: String) {
        scope.launch {
            settingRepository.save(Setting(key, value))
        }
    }

    override fun delete(key: String) {
        scope.launch {
            settingRepository.delete(key)
        }
    }
}