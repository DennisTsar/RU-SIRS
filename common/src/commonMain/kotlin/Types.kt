typealias EntriesByProf = Map<String, List<Entry>>
typealias EntriesByProfMap = SchoolDeptsMap<EntriesByProf>
typealias EntriesMap = SchoolDeptsMap<List<Entry>>
typealias SchoolDeptsMap<T> = Map<String, Map<String, T>>

inline fun <T> SchoolDeptsMap<T>.forEachDept(action: (String, String, T) -> Unit) {
    forEach { (school, deptMap) ->
        deptMap.forEach { (dept, something) ->
            action(school, dept, something)
        }
    }
}

inline fun <T, R> SchoolDeptsMap<T>.mapEachDept(transform: (String, String, T) -> R): SchoolDeptsMap<R> {
    return mapValues { (school, deptMap) ->
        deptMap.mapValues { (dept, something) ->
            transform(school, dept, something)
        }
    }
}

inline fun <T, R> SchoolDeptsMap<T>.flatMapEachDept(transform: (String, String, T) -> List<R>): List<R> {
    return flatMap { (school, deptMap) ->
        deptMap.flatMap { (dept, something) ->
            transform(school, dept, something)
        }
    }
}