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

class GithubSource(
    private val repoPath: String = "/DennisTsar/Rutgers-SIRS/master/",
    private val mainJsonDir: String = "json-data/data-9",
    private val extraJsonDir: String = "json-data/extra-data",
    private val baseJsonDir: String = "json-data",
) : RemoteApi, EntriesFromFileSource, SchoolMapSource, ExtraDataSource {
    private val ghClient = client.config {
        install(ContentNegotiation) {
            serialization(ContentType.Text.Plain, Json)
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "raw.githubusercontent.com"
                encodedPath = repoPath
            }
        }
    }

    override suspend fun getEntries(school: String, dept: String, folderNum: Int): List<Entry> =
        ghClient.get("$baseJsonDir/data-$folderNum/$school/$dept.json").body()

    override suspend fun getEntriesByProf(school: String, dept: String, folderNum: Int): EntriesByProf =
        ghClient.get("$baseJsonDir/data-$folderNum-by-prof/$school/$dept.json").body()

    override suspend fun getStatsByProf(school: String, dept: String): Map<String, InstructorStats> =
        ghClient.get("$mainJsonDir-by-prof-stats/$school/$dept.json").body()

    override suspend fun getSchoolMap(): Map<String, School> =
        ghClient.get("$extraJsonDir/schoolMap.json").body()

    @Deprecated(
        "Data is now stored in separate files by dept. This function is kept to get F22 data.",
        ReplaceWith("GithubSource.getTeachingData"),
    )
    override suspend fun getLatestInstructors(term: String): Map<String, List<String>> =
        ghClient.get("$extraJsonDir/$term-instructors.json").body()

    override suspend fun getTeachingData(school: String, dept: String, term: String): Map<String, List<String>> {
        return try {
            ghClient.get("$extraJsonDir/$term-teaching/$school/$dept.json").body()
        } catch (e: JsonConvertException) {
            emptyMap()
        }
    }

    override suspend fun getCourseNames(school: String, dept: String): Map<String, String> {
        return try {
            ghClient.get("$extraJsonDir/courseNames/$school/$dept.json").body()
        } catch (e: JsonConvertException) {
            emptyMap()
        }
    }

    override suspend fun getDeptMap(): Map<String, String> =
        ghClient.get("$extraJsonDir/deptNameMap.json").body()

    override suspend fun getAllInstructors(dir: String): Map<String, List<Instructor>> =
        ghClient.get("$baseJsonDir/$dir/allInstructors.json").body()

    override suspend fun getSchoolMap(dataDir: String): Map<String, School> =
        ghClient.get("$baseJsonDir/$dataDir/schoolMap.json").body()
}
