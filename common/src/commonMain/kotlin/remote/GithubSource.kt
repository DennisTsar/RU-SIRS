package remote

import EntriesByProf
import Entry
import Instructor
import School
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json

class GithubSource : RemoteApi, EntriesFromFileSource, SchoolMapSource, ExtraDataSource {
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

    override suspend fun getEntries(school: String, dept: String, folderNum: Int): List<Entry> =
        ghClient.get("json-data/data-$folderNum/$school/$dept.json").body()

    override suspend fun getEntriesByProf(school: String, dept: String, folderNum: Int): EntriesByProf =
        ghClient.get("json-data/data-$folderNum-by-prof/$school/$dept.json").body()

    override suspend fun getSchoolMap(): Map<String, School> =
        ghClient.get("json-data/extra-data/schoolMap.json").body()

    @Deprecated(
        "Data is now stored in separate files by dept. This function is kept to get F22 data.",
        ReplaceWith("GithubSource.getTeachingData"),
    )
    override suspend fun getLatestInstructors(term: String): Map<String, List<String>> =
        ghClient.get("json-data/extra-data/$term-instructors.json").body()

    override suspend fun getTeachingData(school: String, dept: String, term: String): Map<String, List<String>> {
        return try {
            ghClient.get("json-data/extra-data/$term-teaching/$school/$dept.json").body()
        } catch (e: JsonConvertException) {
            emptyMap()
        }
    }

    override suspend fun getCourseNames(school: String, dept: String): Map<String, String> {
        return try {
            ghClient.get("json-data/extra-data/courseNames/$school/$dept.json").body()
        } catch (e: JsonConvertException) {
            emptyMap()
        }
    }

    override suspend fun getDeptMap(): Map<String, String> =
        ghClient.get("json-data/extra-data/deptNameMap.json").body()

    override suspend fun getAllInstructors(dir: String): Map<String, List<Instructor>> =
        ghClient.get("json-data/$dir/allInstructors.json").body()

    override suspend fun getSchoolMap(dataDir: String): Map<String, School> =
        ghClient.get("json-data/$dataDir/schoolMap.json").body()
}
