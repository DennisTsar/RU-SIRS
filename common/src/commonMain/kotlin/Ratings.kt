typealias Ratings = List<List<Int>>

// returns list of (# of 1s, # of 2s, ... # of 5s) for each question
// note that entries must have scores.size>=100 - maybe throw error?
fun List<Entry>.getTotalRatings(): Ratings {
    return map { entry ->
        // group by question (there are 10 nums in table per question)
        // + we only care about first 5 nums per Q (the actual ratings) which are all int amounts
        entry.scores.chunked(10)
            .map { it.subList(0, 5).map(Double::toInt) }
    }.combine()
}

fun Collection<Ratings>.combine(): Ratings {
    return reduce { accByQuestion, responsesByQ ->
        // nums from each entry get zipped with each other, by question
        accByQuestion.zip(responsesByQ) { accRatings, ratings ->
            accRatings.zip(ratings, Int::plus)
        }
    }
}

fun List<Int>.getRatingStats(): Pair<Double, Int> {
    val numResponses = sum()
    val ave = mapIndexed { index, num ->
        (index + 1) * num
    }.sum().toDouble() / numResponses
    return ave.roundToDecimal(2) to numResponses
}

// returns list of Pair(average, # responses) for each question
// consider removing this function since it's trivial to re-implement when needed
fun Ratings.getRatingStats(): List<Pair<Double, Int>> {
    return map { it.getRatingStats() }
}