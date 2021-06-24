package application.modules

import application.config.JsonParserBuilder
import org.koin.dsl.module

object CommonsModule {

    fun modules() = module {
        single { JsonParserBuilder.create() }
    }
}
