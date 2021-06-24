package resources.client

import com.fasterxml.jackson.databind.ObjectMapper
import resources.client.http.HttpAdapter

abstract class HttpClient {
    protected abstract val jsonParser: ObjectMapper
    protected abstract val adapter: HttpAdapter
    protected abstract val basePath: String

    fun <T> get(request: Request, clazz: Class<T>): T {
        val path = getUrl(request.path)

        val response = adapter.get(path)

        return when {
            response.isSuccessful -> readValue(response.body, clazz)
            else -> throw handleError(response)
        }
    }

    open fun <T> readValue(result: String, clazz: Class<T>): T {
        return jsonParser.readValue(result, clazz)
    }

    open fun handleError(response: Response): Exception {
        return when {
            response.isClientError -> Exception("Client Error")
            response.isServerError -> Exception("Server Error")
            else -> Exception("Unexpected Error")
        }
    }

    fun getUrl(path: String): String {
        return StringBuilder(basePath).append(path).toString()
    }
}
