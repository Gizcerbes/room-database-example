package org.example.project.database.colection.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import org.example.project.databse.model.Setting
import org.example.project.databse.repository.SettingRepository

object SettingRepositoryImpl : SettingRepository {

    private val hashMap = LinkedHashMap<String, String>()
    private val triggerFlow = MutableSharedFlow<Unit>()


    override suspend fun save(model: Setting) {
        hashMap[model.key] = model.value
        triggerFlow.emit(Unit)
    }

    override suspend fun read(key: String): Setting? {
        return hashMap[key]?.let { Setting(key, it) }
    }

    override fun readFlow(key: String): Flow<Setting?> {
        return triggerFlow.map { read(key) }
    }

    override suspend fun readAll(): List<Setting> {
        return hashMap.map { Setting(it.key, it.value) }
    }

    override fun readAllFlow(): Flow<List<Setting>> {
        return triggerFlow.map { readAll() }
    }

    override suspend fun delete(key: String) {
        hashMap.remove(key)?.let { triggerFlow.emit(Unit) }
    }
}