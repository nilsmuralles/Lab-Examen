package com.example.labexamen.data.network.util

import com.example.labexamen.domain.network.util.NetworkError
import com.example.labexamen.domain.network.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch(e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch(e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch(e: Exception) {
        /**
         * Esto es necesario porque, cuando una corrutina es cancelada, deberíamos dejar que el padre
         * de la corrutina se entere de esto. El problema es que, al agarrar Exception (que esto agarra
         * CUALQUIER tipo de exception que no sean los que especificamos arriba), agarraría el Exception
         * tirado al cancelar la corrutina, y por ende, el padre no se enteraría.
         *
         * coroutineContext.ensureActive() internamente valida que si, la corrutina sigue activa,
         * entonces sigue a la siguiente línea, pero si no, tira una CancellationException.
         */
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}