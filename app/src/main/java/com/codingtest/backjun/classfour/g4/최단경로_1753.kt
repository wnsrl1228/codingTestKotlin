package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer
import java.util.PriorityQueue

/**
 * 다익스트라 문제 (ElogV)
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    class Node(val num: Int, val dist: Int)

    val V = input()
    val E = input()
    val start = input()
    val arr = Array(V+1) { arrayListOf<Node>() }

    repeat(E) {
        arr[input()].add(Node(input(), input()))
    }

    val pq = PriorityQueue<Node>(compareBy { it.dist })
    pq.add(Node(start, 0))
    val minDist = IntArray(V+1) {Int.MAX_VALUE}
    minDist[start] = 0
    while (pq.isNotEmpty()) {
        val poll = pq.poll()
        val num = poll.num

        for (node in arr[num]) {
            if (minDist[node.num] > node.dist + minDist[num]) {
                minDist[node.num] = node.dist + minDist[num]
                pq.add(Node(node.num, minDist[node.num]))
            }
        }
    }
    val sb = StringBuilder()
    for (i in 1..V) {
        sb.appendLine(if (minDist[i] != Int.MAX_VALUE) minDist[i] else "INF")
    }
    print(sb)
}