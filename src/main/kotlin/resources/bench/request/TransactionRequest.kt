package resources.bench.request

import resources.client.Request

data class TransactionRequest(
    val page: Int,
    override val path: String = "transactions/$page.json"
) : Request
