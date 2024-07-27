package com.example.di

import com.example.room.DatabaseRepository
import com.example.room.DatabaseRepositoryImpl


object DependencyInjectionImpl : DependencyInjection() {


    override val dbRepository: DatabaseRepository = DatabaseRepositoryImpl(dbName)


}

actual fun dependencyInjection(): DependencyInjection = DependencyInjectionImpl