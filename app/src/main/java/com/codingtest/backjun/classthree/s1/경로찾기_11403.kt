package com.codingtest.backjun.classthree.s1

import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()){

    val N = readLine().toInt()

    val arr = Array(N) {IntArray(N)}
    repeat(N) { i ->
        readLine().split(" ").forEachIndexed {j, c ->
            arr[i][j] = c.toInt()
        }
    }

    val result = Array(N) {IntArray(N)}

    for (i in 0 until N) {

        val visited = BooleanArray(N)

        fun bfs(start: Int) {
            val queue = LinkedList<Int>()
            queue.add(start)

            while (queue.isNotEmpty()) {
                val poll = queue.removeFirst()

                for (index in 0 until N) {
                    if (arr[poll][index] == 1 && !visited[index]) {
                        result[start][index] = 1
                        queue.add(index)
                        visited[index] = true
                    }
                }
            }
        }
        bfs(i)

    }
    for (row in result) {
        println(row.joinToString(" "))
    }
}

/**
 * 0 -> 1 -> 2 -> 0
 */