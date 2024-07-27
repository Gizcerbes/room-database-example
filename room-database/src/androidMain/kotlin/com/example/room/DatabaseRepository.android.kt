package com.example.room

import android.content.Context
import androidx.room.Room
import com.example.room.MyDatabase.Companion.configure
import com.example.room.repository.SettingRepository

actual class DatabaseRepositoryImpl private constructor(
    database: MyDatabase
) : DatabaseRepository(database) {

    companion object {

        private var db: DatabaseRepository? = null

        operator fun invoke(
            context: Context,
            databasePath: String
        ): DatabaseRepository {
            if (db == null) synchronized(this) {
                if (db == null) db = DatabaseRepositoryImpl(
                    Room.databaseBuilder<MyDatabase>(
                        context,
                        databasePath
                    ).configure()
                )
            }
            return db as DatabaseRepository
        }

    }

}