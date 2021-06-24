package unit.resources.bench.stub

import resources.bench.response.TransactionResponse
import unit.domain.transaction.stub.TRANSACTION_STUB

val TRANSACTION_RESPONSE_STUB = TransactionResponse(
    totalCount = 38,
    page = 1,
    transactions = mutableListOf(
        TRANSACTION_STUB,
        TRANSACTION_STUB,
        TRANSACTION_STUB,
        TRANSACTION_STUB,
        TRANSACTION_STUB,
        TRANSACTION_STUB,
        TRANSACTION_STUB,
        TRANSACTION_STUB,
        TRANSACTION_STUB,
        TRANSACTION_STUB
    )
)
