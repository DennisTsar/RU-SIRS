package remote

import Instructor

interface ExtraDataSource {
    suspend fun getLatestInstructors(term: String = "S23"): Map<String, List<String>>
    suspend fun getDeptMap(): Map<String, String>
    suspend fun getAllInstructors(): List<Instructor>
}