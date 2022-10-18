fun String.substringAfterBefore(after: String, before: String): String =
    substringAfter(after).substringBefore(before)