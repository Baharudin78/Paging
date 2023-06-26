package com.baharudin.pagingcaching.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baharudin.pagingcaching.data.local.dao.BeerDao
import com.baharudin.pagingcaching.data.local.model.BeerEntity
import com.baharudin.pagingcaching.data.remote.dto.BeerDto

@Database(
    entities = [BeerEntity::class],
    version = 1
)
abstract class BeerDatabase : RoomDatabase() {
    abstract val beerDao : BeerDao
}