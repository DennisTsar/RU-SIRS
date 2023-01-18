package remote

import EntriesByProf
import EntriesByProfMap
import Entry
import School
import generateSchoolMap

interface EntriesFromFileSource : EntriesSource {
    suspend fun getEntries(school: String, dept: String, folderNum: Int = 9): List<Entry>

    override suspend fun getLatestEntries(school: String, dept: String): List<Entry> =
        getEntries(school, dept)

    suspend fun getEntriesByProf(school: String, dept: String, folderNum: Int = 9): EntriesByProf

    suspend fun getLatestEntriesByProf(school: String, dept: String): EntriesByProf =
        getEntriesByProf(school, dept)

    suspend fun getAllLatestEntriesByProf(schools: Collection<School>): EntriesByProfMap =
        schools.generateSchoolMap { school, dept -> getLatestEntriesByProf(school, dept) }

    suspend fun getStatsByProf(school: String, dept: String): Map<String, InstructorStats>

    suspend fun getSchoolMap(dataDir: String): Map<String, School>
}