package com.example.labexamen.data.network.util

import com.example.labexamen.domain.network.util.NetworkError
import io.ktor.client.statement.HttpResponse
import com.example.labexamen.domain.network.util.Result
import io.ktor.client.call.body

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, NetworkError> {
    return when(response.status.value) {
        // Verifica que el HttpStatus sea de una llamada exitosa
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch(e: Exception) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        }
        /**
         * Resto de errores. Podríamos usar menos, pero esta función sigue las mejores prácticas
         * y toma en cuenta multiples escenarios. Por fines practicos, se podrian eliminar todos
         * y dejar unicamente el else con el [NetworkError.UNKNOWN]
         */
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)
    }
}