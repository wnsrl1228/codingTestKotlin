package com.codingtest.backjun.classfour.g4

import java.util.LinkedList

/**
 * bfs 문제
 * 반례가 많은 문제라 어렵다.
 */
private const val UP = 0
private const val DOWN = 1
private const val BLINK = 2
fun main() {
    val (N, K) = readln().split(" ").map { it.toInt() }

    if (N == K) {
        println(0)
        println(1)
        return
    }
    var minTime = Int.MAX_VALUE
    var method = 0

    val visited = IntArray(100001) { 0 }
    val queue = LinkedList<Int>()
    queue.add(N)
    visited[N] = 1

    while (queue.isNotEmpty()) {

        val poll = queue.poll()
        if (minTime < visited[poll]) break

        for (move in 0 until 3) {
            var next = 0
            when (move) {
                UP -> next = poll + 1
                DOWN -> next = poll - 1
                BLINK -> next = poll * 2
            }

            if (next < 0 || next > 100000) continue

            if (next == K) {
                minTime = visited[poll]
                method++
            }
            if (visited[next] == 0 || visited[poll] + 1 == visited[next]) {
                queue.add(next)
                visited[next] = visited[poll] + 1
            }
        }
    }

    println(minTime)
    println(method)

}
