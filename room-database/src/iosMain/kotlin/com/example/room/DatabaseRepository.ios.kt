package com.example.room

import androidx.room.Room
import com.example.room.MyDatabase.Companion.configure
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

actual class DatabaseRepositoryImpl private constructor(
    database: MyDatabase
) : DatabaseRepository(database) {


    companion object {

        private var db: DatabaseRepository? = null
        private val so = SynchronizedObject()

        operator fun invoke(
            databasePath: String
        ): DatabaseRepository {
            if (db == null) synchronized(so){
                if (db == null) db = DatabaseRepositoryImpl(
                    Room.databaseBuilder<MyDatabase>(
                        name = databasePath,
                        factory = { MyDatabase::class.instantiateImpl() }
                    ).configure()
                )
            }
            return db as DatabaseRepository
        }

    }


}