package com.gajanan.rocketapp.repository

import android.accounts.NetworkErrorException
import androidx.room.withTransaction
import com.gajanan.rocketapp.cache.database.RocketDatabase
import com.gajanan.rocketapp.cache.networkBoundResource
import com.gajanan.rocketapp.modalClass.RocketDetailResponse
import com.gajanan.rocketapp.modalClass.RocketsResponse
import com.gajanan.rocketapp.network.RetrofitApiInterface
import com.gajanan.rocketapp.utils.ResultApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber
import javax.inject.Inject

class RocketRepository
@Inject constructor(
    private val api: RetrofitApiInterface,
    private val db: RocketDatabase
) {
    private val userDao = db.rocketDao()

//    private val _getAllRockets = Channel<ResultApi<RocketsResponse>>()
//    val getAllRockets = _getAllRockets.receiveAsFlow()

    private val _getRocketDetail = Channel<ResultApi<RocketDetailResponse>>()
    val getRocketDetail = _getRocketDetail.receiveAsFlow()

    //    suspend fun getAllRockets() {
//        try {
//            _getAllRockets.send(ResultApi.Loading())
//            val result = api.getAllRockets()
//            if (result.isSuccessful){
//                _getAllRockets.send(ResultApi.Success(result.body()))
//            }
//        }catch (e:Exception){
//            _getAllRockets.send(ResultApi.Error(e))
//        }catch (e:NetworkErrorException){
//            Timber.i(e)
//        }
//    }
     fun getAllRockets() = networkBoundResource(
        query = {
            userDao.getAllRocket()
        },
        fetch = {
            api.getAllRockets()
        },
        saveFetchResult = { rockets ->
            db.withTransaction {
                userDao.deleteAllRocket()
                userDao.insertRocket(rockets.body()!!)

            }
        }
    )

    fun getRocketDetail(id: String) = networkBoundResource(
        query = {
            userDao.getRocketDetailById(id)
        },
        fetch = {
            api.getRocketDetails(id)
        },
        saveFetchResult = { rockets ->

        }
    )

}