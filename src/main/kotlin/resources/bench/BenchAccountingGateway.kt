package resources.bench

import domain.transaction.TransactionGateway
import domain.transaction.entities.Transaction
import resources.bench.request.TransactionRequest
import resources.bench.response.TransactionResponse

class BenchAccountingGateway(
    private val client: BenchAccountingHttpClient
) : TransactionGateway {

    override fun getTransactions(page: Int): Pair<MutableList<Transaction>, Int> {
        val request = TransactionRequest(page)

        val response = client.get(request, TransactionResponse::class.java)

        return response.toTransaction()
    }
}
