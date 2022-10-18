package remote

import EntriesByProf
import Entry
import School
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json

class GithubSource : RemoteApi, EntriesFromFileSource, SchoolsMapSource {
    private val ghClient = client.config {
        install(ContentNegotiation) {
            serialization(ContentType.Text.Plain, Json)
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "raw.githubusercontent.com"
                encodedPath = "/DennisTsar/Rutgers-SIRS/master/"
            }
        }
    }

    override suspend fun getEntriesFromDir(school: String, dept: String, folderNum: Int): List<Entry> =
        ghClient.get("json-data-$folderNum/$school/$dept.json").body()

    override suspend fun getEntriesByProfFromDir(school: String, dept: String, folderNum: Int): EntriesByProf =
        ghClient.get("json-data-$folderNum-by-prof/$school/$dept.json").body()

    override suspend fun getSchoolsMap(): Map<String, School> =
        ghClient.get("extra-json-data/schoolDeptsMap.json").body()
}
