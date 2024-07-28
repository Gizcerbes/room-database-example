package org.example.project.di

import org.example.project.MyRoomRepositoryImpl.Companion.database
import org.example.project.RoomDatabaseApplication
import org.example.project.databse.Database
import org.example.project.databse.DatabaseCore

actual fun getDI(): DiModule = AndroidDiModule

object AndroidDiModule : DiModule() {



    override val database: DatabaseCore = Database.database(
        RoomDatabaseApplication.appContext,
        RoomDatabaseApplication.appContext.getDatabasePath(dbName).absolutePath
    )


}