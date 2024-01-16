package com.codingtest.backjun.classfive.g3

import java.util.LinkedList

/**
 * 위상정렬 문제
 *  - 순서가 정해지지 않는 경우만 체크해주면 된다.
 */
fun main() = with(System.`in`.bufferedReader()) {

    val (N, M) = readLine().split(" ").map { it.toInt() }
    val arr = Array(N+1) { arrayListOf<Int>() }
    val degree = IntArray(N+1)
    repeat(M) {
        val order = readLine().split(" ").map { it.toInt() }
        for (i in 1 until order.size-1) {
            val front = order[i]
            val back = order[i+1]
            arr[front].add(back)
            degree[back]++
        }
    }

    val queue = LinkedList<Int>()
    for (i in 1 until degree.size) {
        if (degree[i] == 0) queue.add(i)
    }

    val result = LinkedList<Int>()
    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        result.add(cur)

        for (num in arr[cur]) {
            if (degree[num] >= 1) {
                if (degree[num] == 1) queue.add(num)
                degree[num]--
            }
        }
    }
    if (result.size == N) print(result.joinToString("\n"))
    else print(0)
}