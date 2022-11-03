import kotlinx.serialization.Serializable

@Serializable
data class School(
    val code: String,
    val name: String,
    val depts: Set<String>,
)

suspend fun <T> Collection<School>.generateSchoolMap(
    generator: suspend (school: String, dept: String) -> T,
): SchoolDeptsMap<T> {
    return pmap { school ->
        school.code to school.depts.associateWith { generator(school.code, it) }
    }.toMap()
}