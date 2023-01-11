package com.gajanan.rocketapp.cache

import com.gajanan.rocketapp.utils.ResultApi
import kotlinx.coroutines.flow.*

inline fun <ResultType,RequestType> networkBoundResource(
    crossinline query : () -> Flow<ResultType>,
    crossinline fetch : suspend () -> RequestType,
    crossinline saveFetchResult : suspend (RequestType) -> Unit,
    crossinline shouldFetch : (ResultType) -> Boolean = { true }
) = flow {

    val data = query().first()
   val flow =  if (shouldFetch(data)){
        emit(ResultApi.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { ResultApi.Success(it) }
        }catch (e:Throwable){
            query().map { ResultApi.Error(e,it) }
        }

    }else{
        query().map { ResultApi.Success(it) }
    }

    emitAll(flow)
}