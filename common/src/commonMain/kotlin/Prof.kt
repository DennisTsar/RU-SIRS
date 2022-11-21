import kotlinx.serialization.Serializable

@Serializable
data class Prof(
    val name: String,
    val school: String,
    val dept: String,
    val latestSem: Semester,
)