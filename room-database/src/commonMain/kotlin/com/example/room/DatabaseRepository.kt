package com.example.room

import com.example.room.repository.SettingRepository

expect class DatabaseRepositoryImpl : DatabaseRepository

abstract class DatabaseRepository(private val database: MyDatabase) {

    val settingRepository: SettingRepository by lazy { SettingRepository(database.settingDAO()) }

}