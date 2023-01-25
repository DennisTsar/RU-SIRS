package remote

import Instructor
import InstructorStats
import School
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/** This class contains only the methods that are used by the website,
 * even though other data is also accessible from GitHub.
 * In practice, that data can just be accessed locally.
 */
class GithubSource(
    repoPath: String = "/DennisTsar/Rutgers-SIRS/master/",
    private val paths: WebsitePaths = WebsitePaths(),
) : RemoteApi, WebsiteDataSource {
    private val ghClient = client.config {
        install(ContentNegotiation) {
            json(Json, ContentType.Text.Plain)
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "raw.githubusercontent.com"
                encodedPath = repoPath
            }
        }
    }

    override suspend fun getStatsByProf(school: String, dept: String): Map<String, InstructorStats> =
        ghClient.get("${paths.statsByProfDir}/$school/$dept.json").body()

    override suspend fun getCourseNames(school: String, dept: String): Map<String, String> =
        ghClient.get("${paths.courseNamesDir}/$school/$dept.json").body()

    override suspend fun getTeachingData(school: String, dept: String): Map<String, List<String>> {
        return try {
            ghClient.get("${paths.teachingDataDir}/$school/$dept.json").body()
        } catch (e: JsonConvertException) {
            emptyMap()
        }
    }

    override suspend fun getAllInstructors(): Map<String, List<Instructor>> {
        return try {
            ghClient.get(paths.allInstructorsFile).body()
        } catch (e: JsonConvertException) {
            emptyMap()
        }
    }

    override suspend fun getDeptMap(): Map<String, String> = ghClient.get(paths.deptMapFile).body()

    override suspend fun getSchoolMap(): Map<String, School> = ghClient.get(paths.schoolMapFile).body()
}