import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.websocket.Frame


class RequestRepository(){
//Lerning request with ktor
suspend fun main(){
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get("https://jsonplaceholder.typicode.com/posts/")
    println(response.status)
    client.close()
}
}



//    suspend fun registerFrete(nome: String) : ResponseRespositoy{
//       val httpClient = HttpClient(Android) {
//            install(Logging) {
//                level = LogLevel.ALL
//            }
//            install(ContentNegotiation) {
//                json(Json {
//                    ignoreUnknownKeys = true
//                })
//            }
//        }
//        return httpClient.get("https://jsonplaceholder.typicode.com/posts").body()
//    }