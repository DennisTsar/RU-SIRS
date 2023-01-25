package data

import kotlinx.serialization.Serializable

@Serializable
data class School(
    val code: String,
    val name: String,
    val depts: Set<String>,
    val campuses: Set<Campus>,
    val level: LevelOfStudy,
)

enum class Campus(val fullName: String) {
    NB("New-Brunswick"),
    CM("Camden"),
    NK("Newark"),
}

enum class LevelOfStudy(val fullName: String) {
    U("Undergraduate"), G("Graduate")
}

suspend fun <T> Collection<School>.generateSchoolMap(
    generator: suspend (school: String, dept: String) -> T,
): SchoolDeptsMap<T> {
    return pmap { school ->
        school.code to school.depts.associateWith { generator(school.code, it) }
    }.toMap()
}