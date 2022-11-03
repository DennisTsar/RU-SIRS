package remote

import School

interface SchoolMapSource {
    suspend fun getSchoolMap(): Map<String, School>
}