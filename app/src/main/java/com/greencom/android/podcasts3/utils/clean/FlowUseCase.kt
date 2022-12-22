package com.greencom.android.podcasts3.utils.clean

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

abstract class FlowUseCase<in P, out R>(private val dispatcher: CoroutineDispatcher) {

    operator fun invoke(parameters: P): Flow<Result<R>> = execute(parameters)
        .catch { e ->
            Timber.e(e, "Exception occurred while executing ${this.javaClass.simpleName} " +
                    "with parameters $parameters")
            emit(Result.failure(e))
        }
        .flowOn(dispatcher)

    protected abstract fun execute(parameters: P): Flow<Result<R>>

}
