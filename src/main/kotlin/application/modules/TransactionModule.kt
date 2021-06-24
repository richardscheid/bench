package application.modules

import domain.transaction.TransactionGateway
import domain.transaction.TransactionService
import org.koin.dsl.module
import resources.bench.BenchAccountingGateway

object TransactionModule {

    fun modules() = module {
        single { BenchAccountingGateway(get()) as TransactionGateway }
        single { TransactionService(get()) }
    }
}
