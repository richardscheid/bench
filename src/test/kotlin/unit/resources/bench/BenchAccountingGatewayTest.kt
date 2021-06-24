package unit.resources.bench

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resources.bench.BenchAccountingGateway
import resources.bench.BenchAccountingHttpClient
import resources.bench.response.TransactionResponse
import unit.resources.bench.stub.TRANSACTION_REQUEST_STUB
import unit.resources.bench.stub.TRANSACTION_RESPONSE_STUB

class BenchAccountingGatewayTest {

    private val client = mockk<BenchAccountingHttpClient>()
    private val gateway = BenchAccountingGateway(client)

    @Test
    fun `getTransactions() should get transactions successfully`() {
        val response = TRANSACTION_RESPONSE_STUB
        val request = TRANSACTION_REQUEST_STUB
        val expectedPages = 4
        val expectedSize = 10
        val page = 1

        every { client.get(request, TransactionResponse::class.java) } returns response

        val result = gateway.getTransactions(page)

        verify { client.get(request, TransactionResponse::class.java) }

        assertEquals(result.second, expectedPages)
        assertEquals(result.first.size, expectedSize)
        assertEquals(result.first[0].company, response.transactions[0].company)
        assertEquals(result.first[0].ledger, response.transactions[0].ledger)
        assertEquals(result.first[0].amount, response.transactions[0].amount)
        assertEquals(result.first[0].date, response.transactions[0].date)
    }
}
