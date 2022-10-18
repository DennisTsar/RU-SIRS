typealias SchoolDeptsMap<T> = Map<String, Map<String, T>>

fun <T> SchoolDeptsMap<T>.forEachDept(action: (String, String, T) -> Unit) {
    forEach { (school, deptMap) ->
        deptMap.forEach { (dept, something) ->
            action(school, dept, something)
        }
    }
}

fun <T, R> SchoolDeptsMap<T>.mapEachDept(action: (String, String, T) -> R): SchoolDeptsMap<R> {
    return mapValues { (school, deptMap) ->
        deptMap.mapValues { (dept, something) ->
            action(school, dept, something)
        }
    }
}