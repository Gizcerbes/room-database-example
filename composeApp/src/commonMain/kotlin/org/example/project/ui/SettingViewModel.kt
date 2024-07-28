package org.example.project.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import org.example.project.databse.model.Setting

abstract class SettingViewModel : ViewModel() {

    abstract val list: StateFlow<List<Setting>>

    abstract fun save(key: String, value: String)

    abstract fun delete(key: String)

}