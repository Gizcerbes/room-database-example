package org.example.project

import org.example.project.databse.DatabaseCore
import org.example.project.databse.repository.SettingRepository
import org.example.project.repository.SettingRepositoryImp


expect class MyRoomRepositoryImpl: MyRoomRepository

abstract class MyRoomRepository(
    myRoomDatabase: MyRoomDatabase
) : DatabaseCore{

    override val settingRepository: SettingRepository = SettingRepositoryImp(myRoomDatabase.settingDAO)

}