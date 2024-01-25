package com.codingtest.backjun.classfive.g2

import java.io.StreamTokenizer
import java.util.PriorityQueue

/**
 * 위상정렬 - 우선순위 큐
 *
 *  - 기존 위상정렬 풀이에서 번호가 적은 순서부터 풀어야 되기 떄문에 일반 큐 대신 우선순위 큐를 사용해주면 된다.
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val arr = Array(N+1) { arrayListOf<Int>() }
    val degree = IntArray(N+1)
    repeat(input()) {
        // A문제는 B문제보다 먼저 푸는 것이 좋다
        val A = input()
        val B = input()
        arr[A].add(B)
        degree[B]++
    }

    val pq = PriorityQueue<Int>() // 작은 번호 부터

    for (i in 1..N)
        if (degree[i] == 0)
            pq.add(i)

    val result = StringBuffer()
    while (pq.isNotEmpty()) {
        val cur = pq.poll()
        result.append("$cur ")

        for (next in arr[cur]) {
            if (degree[next] == 1) {
                pq.add(next)
            }
            degree[next]--
        }
    }
    print(result)
}
