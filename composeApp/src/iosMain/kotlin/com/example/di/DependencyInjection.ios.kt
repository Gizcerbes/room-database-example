package com.example.di

import com.example.room.DatabaseRepository
import com.example.room.DatabaseRepositoryImpl
import platform.Foundation.NSHomeDirectory


object DependencyInjectionImpl : DependencyInjection() {

    override val dbRepository: DatabaseRepository =
        DatabaseRepositoryImpl(NSHomeDirectory() + "/$dbName")

}

actual fun dependencyInjection(): DependencyInjection = DependencyInjectionImpl
