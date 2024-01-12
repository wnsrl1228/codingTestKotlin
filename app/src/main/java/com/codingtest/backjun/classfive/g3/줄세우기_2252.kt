package com.codingtest.backjun.classfive.g3

import java.io.StreamTokenizer
import java.lang.StringBuilder
import java.util.LinkedList

/**
 * 못 품
 *   위상정렬을 생각하지 못함
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val degree = IntArray(N+1)
    val map = Array(N+1) { arrayListOf<Int>() }

    repeat(input()) {
        val a = input()
        val b = input()
        map[a].add(b)
        degree[b]++
    }

    val queue = LinkedList<Int>()
    for (i in 1 until degree.size) {
        if (degree[i] == 0) queue.add(i)
    }

    val result = StringBuilder()
    fun bfs() {
        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            result.append("$cur ")
            for (i in map[cur]) {

                if (degree[i] == 1) {
                    queue.add(i)
                }
                degree[i]--
            }
        }
    }
    bfs()
    print(result)
}
