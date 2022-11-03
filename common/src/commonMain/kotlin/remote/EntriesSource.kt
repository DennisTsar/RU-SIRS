package remote

import EntriesMap
import Entry
import School
import generateSchoolMap

interface EntriesSource {
    suspend fun getLatestEntriesInDept(school: String, dept: String): List<Entry>

    suspend fun getAllLatestEntries(schools: Collection<School>): EntriesMap =
        schools.generateSchoolMap { school, dept -> getLatestEntriesInDept(school, dept) }
}

