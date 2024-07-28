package org.example.project.di

import org.example.project.MyRoomRepositoryImpl.Companion.database
import org.example.project.databse.Database
import org.example.project.databse.DatabaseCore

actual fun getDI(): DiModule = DesktopDiModule

object DesktopDiModule : DiModule() {


    override val database: DatabaseCore = Database.database(dbName)


}