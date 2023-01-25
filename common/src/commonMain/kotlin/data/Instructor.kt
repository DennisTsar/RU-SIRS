package data

import kotlinx.serialization.Serializable

@Serializable
data class Instructor(
    val name: String,
    val school: String,
    val dept: String,
    val latestSem: Semester,
)