package org.example.project.di

import org.example.project.databse.DatabaseCore

expect fun getDI(): DiModule

abstract class DiModule {

    val dbName = "my_db_name.db"

    abstract val database: DatabaseCore

}