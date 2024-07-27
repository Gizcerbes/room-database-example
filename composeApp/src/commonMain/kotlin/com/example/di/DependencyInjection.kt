package com.example.di

import com.example.room.DatabaseRepository

expect fun dependencyInjection(): DependencyInjection

abstract class DependencyInjection {

    val dbName = "hello-world.db"

    abstract val dbRepository: DatabaseRepository

}