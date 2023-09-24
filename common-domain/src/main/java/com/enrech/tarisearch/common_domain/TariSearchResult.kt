package com.enrech.tarisearch.common_domain

sealed class TariSearchResult<out T> {
    data class Success<out T>(val data: T, val secondaryError: TariSearchError? = null) : TariSearchResult<T>()
    data class Failure<T>(val error: TariSearchError) : TariSearchResult<T>()
}

fun <T> T.toResult(): TariSearchResult<T> {
    return TariSearchResult.Success(this)
}

fun <T> TariSearchError.toResult(): TariSearchResult<T> {
    return TariSearchResult.Failure(this)
}

fun <T> TariSearchResult<T>.isSuccess(): Boolean {
    return this is TariSearchResult.Success
}

fun <T> TariSearchResult<T>.isFailure(): Boolean {
    return this is TariSearchResult.Failure
}

fun <T> TariSearchResult<T>.getData(): T {
    return (this as TariSearchResult.Success).data
}

fun <T> TariSearchResult<T>.getError(): TariSearchError {
    return (this as TariSearchResult.Failure).error
}

fun <T> TariSearchResult<T>.getOrElse(onFailure: (error: TariSearchError) -> T) = when (this) {
    is TariSearchResult.Success -> data
    is TariSearchResult.Failure -> onFailure(error)
}

fun <T> TariSearchResult<T>.getOrNull() = when (this) {
    is TariSearchResult.Success -> data
    is TariSearchResult.Failure -> null
}

fun <T> TariSearchResult<T>.getErrorOrNull() = when(this) {
    is TariSearchResult.Success -> null
    is TariSearchResult.Failure -> error
}

inline fun <R, T> TariSearchResult<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (error: TariSearchError) -> R
): R = when (this) {
    is TariSearchResult.Success -> onSuccess(data)
    is TariSearchResult.Failure -> onFailure(error)
}

inline fun <R, T> TariSearchResult<T>.map(
    onSuccess: (value: T) -> R,
    onFailure: (error: TariSearchError) -> TariSearchError = { error -> error }
): TariSearchResult<R> = when (this) {
    is TariSearchResult.Success -> TariSearchResult.Success(onSuccess(data))
    is TariSearchResult.Failure -> TariSearchResult.Failure(onFailure(error))
}

inline fun <R, T> TariSearchResult<T>.transform(
    transform: (TariSearchResult<T>) -> TariSearchResult<R>
): TariSearchResult<R> = transform(this)

inline fun <R,T> TariSearchResult<T>.transform(
    onSuccess: (value: T) -> TariSearchResult<R>,
    onFailure: (error: TariSearchError) -> TariSearchResult<R>
): TariSearchResult<R> = when(this) {
    is TariSearchResult.Success -> onSuccess(data)
    is TariSearchResult.Failure -> onFailure(error)
}

inline fun <R, T> TariSearchResult<T>.map(
    transform: (T) -> R
): TariSearchResult<R> = when (this) {
    is TariSearchResult.Success -> TariSearchResult.Success(transform(data))
    is TariSearchResult.Failure -> TariSearchResult.Failure(error)
}

inline fun <R, T> TariSearchResult<T>.flatMap(
    transform: (T) -> TariSearchResult<R>
): TariSearchResult<R> = when (this) {
    is TariSearchResult.Success -> transform(data)
    is TariSearchResult.Failure -> TariSearchResult.Failure(error)
}

inline fun <T> TariSearchResult<T>.onSuccess(
    onSuccess: (value: T) -> Unit
): TariSearchResult<T> {
    if (this is TariSearchResult.Success) onSuccess(data)
    return this
}

inline fun <T> TariSearchResult<T>.onFailure(
    onFailure: (error: TariSearchError) -> Unit
): TariSearchResult<T> {
    if (this is TariSearchResult.Failure) onFailure(error)
    return this
}
