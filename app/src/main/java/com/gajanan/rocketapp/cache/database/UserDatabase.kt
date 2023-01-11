package com.gajanan.rocketapp.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gajanan.rocketapp.cache.TypeConverter
import com.gajanan.rocketapp.modalClass.Diameter
import com.gajanan.rocketapp.modalClass.Engines
import com.gajanan.rocketapp.modalClass.Height
import com.gajanan.rocketapp.modalClass.RocketDetailResponse

@Database(entities = [RocketDetailResponse::class,Diameter::class,Height::class,Engines::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun rocketDao() : RocketDao

}