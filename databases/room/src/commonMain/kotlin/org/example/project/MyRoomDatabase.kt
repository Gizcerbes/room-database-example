@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.example.project

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.project.dao.SettingDAO
import org.example.project.entity.SettingEntity

@Database(
    version = 1,
    entities = [
        SettingEntity::class
    ]
)
@ConstructedBy(MyRoomDatabaseConstructor::class)
abstract class MyRoomDatabase : RoomDatabase(), DB {

    abstract val settingDAO: SettingDAO

    companion object {

        fun RoomDatabase.Builder<MyRoomDatabase>.configure(): MyRoomDatabase {
            return setDriver(BundledSQLiteDriver())
                .build()
        }

    }

    override fun clearAllTables() {}


}

interface DB {

    fun clearAllTables()
}

@Suppress("KotlinNoActualForExpect")
expect object MyRoomDatabaseConstructor: RoomDatabaseConstructor<MyRoomDatabase>{
    override fun initialize(): MyRoomDatabase
}