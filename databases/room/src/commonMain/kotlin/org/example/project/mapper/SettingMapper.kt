package org.example.project.mapper

import org.example.project.databse.model.Setting
import org.example.project.entity.SettingEntity

object SettingMapper {


    fun Setting.toEntity() = SettingEntity(
        key = key,
        value = value
    )

    fun SettingEntity.toDTO() = Setting(
        key = key,
        value = value
    )

}