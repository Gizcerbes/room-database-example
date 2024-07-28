package org.example.project.databse

import org.example.project.databse.repository.SettingRepository

object Database


interface DatabaseCore {

    val settingRepository: SettingRepository

}