package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.room.dao.SettingDao
import com.example.room.entity.SettingEntity

@Database(
    version = 1,
    entities = [
        SettingEntity::class
    ]
)
abstract class MyDatabase: RoomDatabase(), DB {

    abstract fun settingDAO(): SettingDao

    companion object {

        fun RoomDatabase.Builder<MyDatabase>.configure(): MyDatabase {
            return setDriver(BundledSQLiteDriver())
                .build()
        }

    }

    override fun clearAllTables() {
        super.clearAllTables()
    }

}

interface DB {

    fun clearAllTables() {}

}