package org.example.project

import android.content.Context
import androidx.room.Room
import org.example.project.MyRoomDatabase.Companion.configure
import org.example.project.databse.Database
import org.example.project.databse.DatabaseCore

actual class MyRoomRepositoryImpl private constructor(
    myRoomDatabase: MyRoomDatabase
) : MyRoomRepository(myRoomDatabase) {


    companion object {

        private var repo: MyRoomRepository? = null

        operator fun invoke(
            context: Context,
            databasePath: String
        ): DatabaseCore {
            if (repo == null) synchronized(this) {
                if (repo == null) repo = MyRoomRepositoryImpl(
                    Room.databaseBuilder<MyRoomDatabase>(
                        context = context,
                        name = databasePath
                    ).configure()
                )
            }
            return repo!!
        }

        fun Database.database(
            context: Context,
            databasePath: String
        ) = invoke(context, databasePath)


    }


}