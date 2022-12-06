import kotlinx.serialization.Serializable

@Serializable
data class Semester(val type: SemesterType, val year: Int) : Comparable<Semester> {
    override fun compareTo(other: Semester): Int {
        return numValue.compareTo(other.numValue)
    }

    constructor(num: Int) : this(SemesterType.values()[num % 2], num / 2)

    val numValue get() = year * 2 + if (type == SemesterType.Spring) 0 else 1
}

enum class SemesterType(val num: Int) {
    Spring(1), Fall(9); // !! Note that this order matters !!

    fun other(): SemesterType = if (this == Spring) Fall else Spring
}