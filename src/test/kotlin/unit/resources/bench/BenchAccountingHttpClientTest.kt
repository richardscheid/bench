package unit.resources.bench

import com.fasterxml.jackson.databind.ObjectMapper
import domain.exceptions.NotFoundException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import resources.bench.BenchAccountingHttpClient
import resources.bench.response.TransactionResponse
import resources.client.http.HttpRequest
import unit.resources.bench.stub.NOT_FOUND_RESPONSE_STUB
import unit.resources.bench.stub.OK_RESPONSE_STUB
import unit.resources.bench.stub.TRANSACTION_REQUEST_STUB
import unit.resources.bench.stub.TRANSACTION_RESPONSE_STUB

class BenchAccountingHttpClientTest {

    private val basePath: String = "https://fake.path.ca/"
    private val jsonParser = mockk<ObjectMapper>()
    private val adapter = mockk<HttpRequest>()
    private val client = BenchAccountingHttpClient(jsonParser, adapter, basePath)

    @Test
    fun `get() should call get successfully`() {
        val request = TRANSACTION_REQUEST_STUB
        val response = TRANSACTION_RESPONSE_STUB
        val path = client.getUrl(request.path)

        every { adapter.get(path) } returns OK_RESPONSE_STUB
        every {
            jsonParser.readValue(
                OK_RESPONSE_STUB.body,
                TransactionResponse::class.java
            )
        } returns response

        val result = client.get(request, TransactionResponse::class.java)

        assertEquals(result.page, response.page)
        assertEquals(result.totalCount, response.totalCount)
    }

    @Test
    fun `get() should throw not found exception when trying to get a non-existent page`() {
        val request = TRANSACTION_REQUEST_STUB
        val path = client.getUrl(request.path)

        every { adapter.get(path) } returns NOT_FOUND_RESPONSE_STUB

        assertThrows<NotFoundException> {
            client.get(request, TransactionResponse::class.java)
        }
    }
}
