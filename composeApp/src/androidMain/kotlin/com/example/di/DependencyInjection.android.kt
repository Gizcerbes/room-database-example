package com.example.di

import org.example.project.MyApplication
import com.example.room.DatabaseRepository
import com.example.room.DatabaseRepositoryImpl


object DependencyInjectionImpl : DependencyInjection() {

    override val dbRepository: DatabaseRepository = DatabaseRepositoryImpl(
        MyApplication.appContext,
        MyApplication.appContext.getDatabasePath(dbName).absolutePath
    )

}

actual fun dependencyInjection(): DependencyInjection = DependencyInjectionImpl