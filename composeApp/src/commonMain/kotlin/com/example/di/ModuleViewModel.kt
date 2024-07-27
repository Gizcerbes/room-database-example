package com.example.di

import com.example.ui.ListViewModel

object ModuleViewModel {

    val ListViewModel get() = ListViewModel(dependencyInjection().dbRepository.settingRepository)


}