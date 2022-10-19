package remote

interface ExtraDataSource {
    suspend fun getInstructors(term: String = "S23"): Map<String, List<String>>
    suspend fun getDeptMap(): Map<String, String>
}