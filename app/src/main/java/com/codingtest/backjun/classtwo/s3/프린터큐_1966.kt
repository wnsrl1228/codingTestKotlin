package com.codingtest.backjun.classtwo.s3

fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val queue = ArrayDeque(readln().split(" ").mapIndexed { index, s -> Doc(s.toInt(), index) })
        val sort = queue.sortedByDescending { it.importance }

        var sortIndex = 0
        var count = 1
        while (true) {
            val max = sort[sortIndex].importance
            if (max == queue[0].importance) {
                if (queue[0].index == m) {
                    break
                }
                sortIndex++
                queue.removeFirst()
                count++
            } else {
                queue.addLast(queue.removeFirst())
            }
        }
        println(count)
    }
}
class Doc(
    val importance : Int,
    val index : Int,
) {
    override fun toString(): String {
        return "Doc(importance=$importance, index=$index)"
    }
}

fun 프린터큐_개선된풀이() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val queue = ArrayDeque(readln().split(" ").mapIndexed { index, s -> Pair(s.toInt(), index) })
        val arr = IntArray(10)
        queue.forEach { i ->
            arr[i.first]++
        }

        var count = 0
        while (true) {
            val p = queue.removeFirst()
            if (isMax(p.first, arr)) {
                arr[p.first]--
                count++
                if (p.second == m) {
                    break
                }
            } else {
                queue.addLast(p)
            }
        }
        println(count)
    }
}

fun isMax(now: Int, ary: IntArray): Boolean {
    for (i in 9 downTo 1) {
        if (ary[i] > 0 ) {
            if (i == now)
                return true
            break
        }
    }
    return false
}