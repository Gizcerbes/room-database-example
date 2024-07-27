package com.example.room

import androidx.room.Room
import com.example.room.MyDatabase.Companion.configure

actual class DatabaseRepositoryImpl private constructor(
    database: MyDatabase
) : DatabaseRepository(database){

    companion object{

        private var db: DatabaseRepository? = null

        operator fun invoke(
            databasePath: String
        ): DatabaseRepository {
            if (db == null) synchronized(this){
                if (db == null) db = DatabaseRepositoryImpl(
                    Room.databaseBuilder<MyDatabase>(
                        name = databasePath
                    ).configure()
                )
            }
            return db as DatabaseRepository
        }

    }



}