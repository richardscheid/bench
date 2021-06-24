package application.modules

import org.koin.dsl.module
import resources.bench.BenchAccountingHttpClient
import resources.client.http.HttpRequest

object ClientModule {

    fun modules() = module {
        single { BenchAccountingHttpClient(get(), get()) }
        single { HttpRequest() }
    }
}
