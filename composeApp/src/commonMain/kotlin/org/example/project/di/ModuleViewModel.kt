package org.example.project.di

import org.example.project.ui.SettingViewModel
import org.example.project.ui.SettingViewModelImpl

object ModuleViewModel {

    val SettingViewModel: SettingViewModel get() = SettingViewModelImpl(getDI().database.settingRepository)


}