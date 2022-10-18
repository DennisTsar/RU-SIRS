package remote

import EntriesByProf
import EntriesByProfMap
import Entry
import School
import generateSchoolsMap

interface EntriesFromFileSource : EntriesSource {
    suspend fun getEntriesFromDir(school: String, dept: String, folderNum: Int = 9): List<Entry>

    override suspend fun getLatestEntriesInDept(school: String, dept: String): List<Entry> =
        getEntriesFromDir(school, dept)

    suspend fun getEntriesByProfFromDir(school: String, dept: String, folderNum: Int = 9): EntriesByProf

    suspend fun getLatestEntriesByProfInDept(school: String, dept: String): EntriesByProf =
        getEntriesByProfFromDir(school, dept)

    suspend fun getAllLatestEntriesByProf(schools: Collection<School>): EntriesByProfMap =
        schools.generateSchoolsMap { school, dept -> getLatestEntriesByProfInDept(school, dept) }
}