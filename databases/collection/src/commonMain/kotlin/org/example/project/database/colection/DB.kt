package org.example.project.database.colection

import org.example.project.database.colection.repository.SettingRepositoryImpl
import org.example.project.databse.Database
import org.example.project.databse.DatabaseCore
import org.example.project.databse.repository.SettingRepository


fun Database.database(): DatabaseCore = CollectionDB

object CollectionDB : DatabaseCore {

    override val settingRepository: SettingRepository = SettingRepositoryImpl

}