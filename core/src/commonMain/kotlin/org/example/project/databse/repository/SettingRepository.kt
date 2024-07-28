package org.example.project.databse.repository

import kotlinx.coroutines.flow.Flow
import org.example.project.databse.model.Setting

interface SettingRepository {

    suspend fun save(model: Setting)

    suspend fun read(key: String): Setting?

    fun readFlow(key: String): Flow<Setting?>

    suspend fun readAll(): List<Setting>

    fun readAllFlow(): Flow<List<Setting>>

    suspend fun delete(key:String)

}