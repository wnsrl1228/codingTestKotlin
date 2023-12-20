package com.codingtest.backjun.classfour.g3

import java.io.StreamTokenizer
import java.util.PriorityQueue

/**
 * 다익스트라 문제
 *  - X에서 N개의 마을까지의 최단 거리 : 평범한 다익스트라
 *  - N개의 마을에서 X까지의 최단 거리 : 단방향 도로의 방향을 반대로 전환한 뒤에 다익스트라
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input() // 마을 개수
    val M = input() // 가중치 개수
    val X = input() // 마을

    val MAX = 1000000
    data class Node(val num: Int, val dist: Int)
    val arr = Array(N+1) { arrayListOf<Node>() }
    val arrReverse = Array(N+1) { arrayListOf<Node>() }

    repeat(M) {
        val a = input()
        val b = input()
        val c = input()
        arr[a].add(Node(b,c))
        arrReverse[b].add(Node(a,c))
    }

    fun dijkstra(start: Int, map: Array<ArrayList<Node>>): IntArray {
        val minDist = IntArray(N+1) {MAX}
        val pq = PriorityQueue<Node>(compareBy { it.dist })

        minDist[start] = 0
        pq.add(Node(start, 0))

        while (pq.isNotEmpty()) {
            val node = pq.poll()

            for (next in map[node.num]) {
                if (minDist[next.num] > minDist[node.num] + next.dist) {
                    minDist[next.num] = minDist[node.num] + next.dist
                    pq.add(Node(next.num, minDist[next.num])) // 거리 갱신 주의
                }
            }
        }
        return minDist
    }

    val minDist = dijkstra(X, arr)
    val minDistReverse = dijkstra(X, arrReverse)

    var result = 0

    for (i in 1..N) {
        result = Math.max(result, minDist[i] + minDistReverse[i])
    }
    print(result)
}