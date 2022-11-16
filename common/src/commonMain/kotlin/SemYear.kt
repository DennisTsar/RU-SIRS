data class SemYear(val semester: Semester, val year: Int) : Comparable<SemYear> {
    override fun compareTo(other: SemYear): Int {
        return if (year != other.year)
            year.compareTo(other.year)
        else
            semester.compareTo(other.semester)
    }

    constructor(num: Int) : this(Semester.values()[num % 2], num / 2)

    fun toInt() = year * 2 + if (semester == Semester.Spring) 0 else 1
}

enum class Semester(val num: Int) {
    Spring(1), Fall(9); // !! Note that this order matters !!

    fun other(): Semester = if (this == Spring) Fall else Spring

}