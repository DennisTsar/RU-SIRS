package remote

import Instructor

interface ExtraDataSource {
    @Deprecated(
        "Data is now stored in separate files by dept. This function is kept to get F22 data.",
        ReplaceWith("getTeachingData"),
    )
    suspend fun getLatestInstructors(term: String = "S23"): Map<String, List<String>>

    suspend fun getTeachingData(school: String, dept: String, term: String = "S23"): Map<String, List<String>>
    suspend fun getDeptMap(): Map<String, String>
    suspend fun getAllInstructors(dir: String = "data-9-by-prof"): Map<String, List<Instructor>>
}