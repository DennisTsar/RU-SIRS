package remote

import EntriesMap
import Entry
import School
import generateSchoolsMap

interface EntriesSource {
    suspend fun getLatestEntriesInDept(school: String, dept: String): List<Entry>

    suspend fun getAllLatestEntries(schools: Collection<School>): EntriesMap =
        schools.generateSchoolsMap { school, dept -> getLatestEntriesInDept(school, dept) }
}

