package util

fun ensureVersionCode(code: Int): Int {
    return if (code != 0) code else 1
}
