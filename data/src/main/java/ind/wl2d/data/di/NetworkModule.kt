package ind.wl2d.data.di

import ind.wl2d.data.network.NetworkServiceImpl
import ind.wl2d.domain.network.NetworkService
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule =
    module {
        single {
            HttpClient(OkHttp) {
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        },
                    )
                }
                install(Logging) {
                    level = LogLevel.ALL
                }
            }
        }

        single<NetworkService> {
            NetworkServiceImpl(get())
        }
    }
