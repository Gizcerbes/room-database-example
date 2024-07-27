package com.example.room.repository

import com.example.room.dao.SettingDao
import com.example.room.dto.SettingDTO
import com.example.room.mapper.SettingMapper.toDTO
import com.example.room.mapper.SettingMapper.toEntity
import kotlinx.coroutines.flow.map

class SettingRepository(
    private val dao: SettingDao
) {

    suspend fun save(vararg dto: SettingDTO) =
        dao.saveAll(*dto.map { it.toEntity() }.toTypedArray())

    suspend fun delete(key: String) = dao.delete(key)

    fun getAllFlow() = dao.getListFlow().map { it.map { entity -> entity.toDTO() } }


}