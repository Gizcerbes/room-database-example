package com.example.room.mapper

import com.example.room.dto.SettingDTO
import com.example.room.entity.SettingEntity

object SettingMapper {

    fun SettingDTO.toEntity() = SettingEntity(
        key = key,
        value = value
    )


    fun SettingEntity.toDTO() = SettingDTO(
        key = key,
        value = value
    )

}