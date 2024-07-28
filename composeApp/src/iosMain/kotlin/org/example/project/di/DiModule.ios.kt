package org.example.project.di

import org.example.project.MyRoomRepositoryImpl.Companion.database
import org.example.project.databse.Database
import org.example.project.databse.DatabaseCore
import platform.Foundation.NSHomeDirectory

actual fun getDI(): DiModule = IosDiModule

object IosDiModule: DiModule(){
    

    override val database: DatabaseCore = Database.database(
        NSHomeDirectory() + "/$dbName"
    )


}

