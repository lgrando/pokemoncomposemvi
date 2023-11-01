package com.br.pokemoncomposemvi.framework.extensions

import com.br.pokemoncomposemvi.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <REMOTE, MAPPER> remoteApiToFlow(
    remote: suspend () -> Response<REMOTE>,
    mapper: suspend (REMOTE) -> MAPPER,
    onRemoteFail: (suspend FlowCollector<Resource<MAPPER>>.(code: Int) -> Unit?)? = null
) = flow {

    emit(Resource.Loading(isLoading = true))
    val remoteCall = remote()

    if (remoteCall.isSuccessful) {
        val remoteData = remoteCall.body()
        if (remoteData != null) emit(Resource.Success(data = mapper(remoteData)))
        else emit(Resource.Error(error = "Some generic error message"))
    } else {
        if (onRemoteFail != null) onRemoteFail(remoteCall.code())
        else emit(Resource.Error(error = remoteCall.errorBody().toString()))
    }
    emit(Resource.Loading(isLoading = false))
}