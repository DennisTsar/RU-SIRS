package data

import kotlinx.serialization.Serializable

@Serializable
class InstructorStats(
    val lastSem: Int,
    val overallStats: Ratings,
    val courseStats: Map<String, Ratings>,
)