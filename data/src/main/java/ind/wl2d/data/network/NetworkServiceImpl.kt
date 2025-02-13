package ind.wl2d.data.network

import ind.wl2d.data.models.DataProductModel
import ind.wl2d.data.models.toProduct
import ind.wl2d.domain.model.Product
import ind.wl2d.domain.network.NetworkService
import ind.wl2d.domain.network.ResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.utils.io.InternalAPI
import kotlinx.io.IOException

class NetworkServiceImpl(
    val client: HttpClient,
) : NetworkService {
    private val baseUrl = "https://fakestoreapi.com"

    override suspend fun getProducts(category: String?): ResultWrapper<List<Product>> {
        val url =
            if (category != null) {
                "$baseUrl/products/category/$category"
            } else {
                "$baseUrl/products"
            }

        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { dataModels: List<DataProductModel> ->
                dataModels.map { it.toProduct() }
            },
        )
    }

    @OptIn(InternalAPI::class)
    suspend inline fun <reified T, R> makeWebRequest(
        url: String,
        method: HttpMethod,
        body: Any? = null,
        headers: Map<String, String> = emptyMap(),
        queryParams: Map<String, String> = emptyMap(),
        noinline mapper: ((T) -> R)? = null,
    ): ResultWrapper<R> =
        try {
            val response =
                client
                    .request(url) {
                        this.method = method

                        url {
                            this.parameters.appendAll(
                                Parameters.build {
                                    queryParams.forEach { (key, value) ->
                                        append(key, value)
                                    }
                                },
                            )
                        }

                        headers.forEach { (key, value) ->
                            header(key, value)
                        }

                        body?.let { this.setBody(it) }

                        contentType(ContentType.Application.Json)
                    }.body<T>()
            val result: R = mapper?.invoke(response) ?: response as R
            ResultWrapper.Success(result)
        } catch (e: ClientRequestException) {
            ResultWrapper.Failure(e)
        } catch (e: ServerResponseException) {
            ResultWrapper.Failure(e)
        } catch (e: IOException) {
            ResultWrapper.Failure(e)
        } catch (e: Exception) {
            ResultWrapper.Failure(e)
        }
}
