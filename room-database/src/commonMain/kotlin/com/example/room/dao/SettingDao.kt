package com.example.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.room.entity.SettingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDao {

    @Upsert
    suspend fun saveAll(vararg entity: SettingEntity)

    @Query("DELETE FROM setting_table WHERE `key` = :key")
    suspend fun delete(key: String)

    @Query("SELECT * FROM setting_table WHERE `key` = :key")
    suspend fun getValue(key: String) : SettingEntity?

    @Query("SELECT * FROM setting_table WHERE `key` = :key")
    fun getValueFlow(key: String): Flow<SettingEntity?>

    @Query("SELECT * FROM setting_table")
    fun getListFlow(): Flow<List<SettingEntity>>

}