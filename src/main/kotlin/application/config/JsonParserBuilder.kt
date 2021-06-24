package application.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule

object JsonParserBuilder {

    fun create(): ObjectMapper {
        return jacksonObjectMapper()
            .registerModule(JodaModule())
            .registerModule(kotlinModule())
    }
}
