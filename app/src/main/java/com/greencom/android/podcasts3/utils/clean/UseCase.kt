package com.greencom.android.podcasts3.utils.clean

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class UseCase<in P, out R>(private val dispatcher: CoroutineDispatcher) {

    @Suppress("TooGenericExceptionCaught")
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(dispatcher) {
                val result = execute(parameters)
                Result.success(result)
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception occurred while executing ${this.javaClass.simpleName} " +
                    "with parameters $parameters")
            Result.failure(e)
        }
    }

    protected abstract suspend fun execute(parameters: P): R

}
