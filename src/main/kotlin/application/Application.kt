package application

import application.modules.ClientModule
import application.modules.CommonsModule
import application.modules.TransactionModule
import domain.transaction.TransactionService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

object Application : KoinComponent {

    private val service: TransactionService by inject()

    private fun setupDependencyInjection() {
        startKoin {
            modules(
                TransactionModule.modules(),
                CommonsModule.modules(),
                ClientModule.modules()
            )
        }
    }

    fun init() {
        setupDependencyInjection()

        service.calculateDailyBalance()
    }
}

fun main() {
    Application.init()
}
