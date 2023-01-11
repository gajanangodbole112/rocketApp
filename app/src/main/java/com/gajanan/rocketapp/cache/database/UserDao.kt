package com.gajanan.rocketapp.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gajanan.rocketapp.modalClass.RocketDetailResponse
import com.gajanan.rocketapp.modalClass.RocketsResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertRocket( rocketItem: RocketsResponse)

        @Query("DELETE FROM rockets")
        suspend fun deleteAllRocket()

        @Query("SELECT * FROM rockets")
         fun getAllRocket() : Flow<List<RocketDetailResponse>>

         @Query("SELECT * FROM rockets WHERE id=:id")
         fun getRocketDetailById(id : String) : Flow<RocketDetailResponse>
}