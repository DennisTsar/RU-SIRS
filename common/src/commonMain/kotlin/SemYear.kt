data class SemYear(val semester: Semester, val year: Int) : Comparable<SemYear> {
    override fun compareTo(other: SemYear): Int {
        return if (year != other.year)
            year.compareTo(other.year)
        else
            semester.compareTo(other.semester)
    }
}

enum class Semester(val num: Int) {
    Spring(1), Fall(9); // !! Note that this order matters !!

    fun other(): Semester = if (this == Spring) Fall else Spring

}