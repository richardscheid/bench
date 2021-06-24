package resources.bench

import com.fasterxml.jackson.databind.ObjectMapper
import domain.exceptions.NotFoundException
import org.eclipse.jetty.http.HttpStatus
import resources.client.HttpClient
import resources.client.Response
import resources.client.http.HttpRequest

class BenchAccountingHttpClient(
    override val jsonParser: ObjectMapper,
    override val adapter: HttpRequest,
    override val basePath: String = "https://resttest.bench.co/"
) : HttpClient() {

    override fun handleError(response: Response): Exception {
        return when (response.statusCode) {
            HttpStatus.NOT_FOUND_404 -> NotFoundException("Not Found Error")
            else -> super.handleError(response)
        }
    }
}
