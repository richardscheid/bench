package resources.client.http

import com.github.kittinunf.fuel.Fuel
import resources.client.Response
import com.github.kittinunf.fuel.core.Response as FuelResponse

class HttpRequest : HttpAdapter {

    override fun get(path: String): Response {
        return Fuel.get(path).response().second.toResponse()
    }
}

fun FuelResponse.toResponse(): Response {
    return Response(
        statusCode = this.statusCode,
        body = String(this.data)
    )
}
