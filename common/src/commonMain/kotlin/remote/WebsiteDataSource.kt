package remote

import data.Instructor
import data.InstructorStats
import data.School

interface WebsiteDataSource {
    suspend fun getStatsByProf(school: String, dept: String): Map<String, InstructorStats>
    suspend fun getCourseNamesOrEmpty(school: String, dept: String): Map<String, String>
    suspend fun getTeachingDataOrEmpty(school: String, dept: String): Map<String, List<String>>

    suspend fun getAllInstructors(): Map<String, List<Instructor>>
    suspend fun getDeptMap(): Map<String, String>
    suspend fun getSchoolMap(): Map<String, School>
}

/** Useful for testing */
suspend fun WebsiteDataSource.getAllData(school: String, dept: String, print: Boolean = true) {
    val schoolMap = getSchoolMap().also { require(it.isNotEmpty()) }
    val allInstructors = getAllInstructors().also { require(it.isNotEmpty()) }
    val deptNames = getDeptMap().also { require(it.isNotEmpty()) }
    val specificSchoolStats = getStatsByProf(school, dept).also { require(it.isNotEmpty()) }
    val specificCourseNames = getCourseNamesOrEmpty(school, dept).also { require(it.isNotEmpty()) }
    val specificTeachingProfs = getTeachingDataOrEmpty(school, dept).also { require(it.isNotEmpty()) }
    if (print) {
        println(schoolMap)
        println(allInstructors)
        println(deptNames)
        println(specificSchoolStats)
        println(specificCourseNames)
        println(specificTeachingProfs)
    }
}
