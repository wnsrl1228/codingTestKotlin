package com.codingtest.backjun.classfour.s2

import java.io.StreamTokenizer
import java.util.LinkedList

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()

    val arr = (1..N).associateWith { mutableListOf<Int>() }
    val visited = BooleanArray(N+1)
    val result = IntArray(N+1)

    repeat(N-1) {
        val a = input()
        val b = input()
        arr[a]!!.add(b)
        arr[b]!!.add(a)
    }

    val queue = LinkedList<Int>()
    queue.add(1)
    visited[1] = true

    while (queue.isNotEmpty()) {
        val poll = queue.poll()

        for (i in arr[poll]!!) {
            if (!visited[i]) {
                visited[i] = true
                queue.add(i)
                result[i] = poll
            }
        }
    }

    val sb = StringBuilder()
    for (i in 2..N) {
        sb.appendLine(result[i])
    }
    print(sb)
}
/**
 *     7
 * 1   4  2
 * 6
 * 3
 * 5
 *
 * 0 1 2 3 4 5 6 7
 *   4   6 2 3 1 4
 */