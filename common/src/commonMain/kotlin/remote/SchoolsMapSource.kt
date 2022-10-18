package remote

import School

interface SchoolsMapSource {
    suspend fun getSchoolsMap(): Map<String, School>
}