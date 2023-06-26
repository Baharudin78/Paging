package com.baharudin.pagingcaching.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.baharudin.pagingcaching.data.local.BeerDatabase
import com.baharudin.pagingcaching.data.local.model.BeerEntity
import com.baharudin.pagingcaching.data.remote.mappers.toBeerEntity
import com.baharudin.pagingcaching.data.remote.service.BeerApi
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerDb : BeerDatabase,
    private val beerApi : BeerApi
) :RemoteMediator<Int, BeerEntity>(){

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null){
                        1
                    }else{
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            val beers = beerApi.getBeers(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            beerDb.withTransaction {
                if (loadType == LoadType.REFRESH){
                    beerDb.beerDao.clearAll()
                }
                val beerEntity = beers.map { it.toBeerEntity() }
                beerDb.beerDao.upsertAll(beerEntity)
            }
            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        }catch (e : IOException){
            MediatorResult.Error(e)
        }catch (e : HttpException){
            MediatorResult.Error(e)
        }
    }
}