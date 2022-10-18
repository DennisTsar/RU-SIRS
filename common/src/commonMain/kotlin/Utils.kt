import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

fun String.substringAfterBefore(after: String, before: String): String =
    substringAfter(after).substringBefore(before)

//Stolen from te interwebs
suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> =
    coroutineScope { map { async { f(it) } }.awaitAll() }

suspend fun <A, B, C> Map<A, B>.pmap(f: suspend (Map.Entry<A, B>) -> C): List<C> =
    coroutineScope { map { async { f(it) } }.awaitAll() }