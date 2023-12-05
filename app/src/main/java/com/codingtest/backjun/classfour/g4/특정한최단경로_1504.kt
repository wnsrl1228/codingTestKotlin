package com.codingtest.backjun.classfour.g4

import java.io.StreamTokenizer
import java.util.PriorityQueue

/**
 * 최단 경로 1~N까지
 *  - 방향성 x
 *  - 이동했던 정점 다시 이동 가능
 *  - 반드시 두 정점을 통과해야 됨
 *
 *  다익스트라 문제
 *  1 -> v1 -> v2 -> N
 *  1 -> v2 -> v1 -> N
 *  두 경로 비교해주면 됨
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val INF = 1e8.toInt()
    val N = input()
    val E = input()
    val arr = Array(N+1) {IntArray(N+1)}
    repeat(E) {
        val a = input()
        val b = input()
        val c = input()
        arr[a][b] = c
        arr[b][a] = c
    }

    val v1 = input()
    val v2 = input()

    fun dijkstra(start: Int, minDistArr: IntArray) {

        class Node(val num: Int, val dist: Int)
        minDistArr[start] = 0
        val visited = BooleanArray(N+1)
        val pq = PriorityQueue<Node>(compareBy{it.dist})
        pq.add(Node(start, 0))

        while (pq.isNotEmpty()) {
            val poll = pq.poll()
            val num = poll.num

            if (!visited[num]) visited[num] = true
            else continue
            for (i in arr[num].indices) {
                if (arr[num][i] != 0 && !visited[i]) {
                    minDistArr[i] = Math.min(minDistArr[i], minDistArr[num] + arr[num][i])
                    pq.add(Node(i, minDistArr[i]))
                }
            }
        }
    }
    val startMinDistArr = IntArray(N+1) {INF}
    val v1MinDistArr = IntArray(N+1) {INF}
    val v2MinDistArr = IntArray(N+1) {INF}
    dijkstra(1, startMinDistArr)
    dijkstra(v1, v1MinDistArr)
    dijkstra(v2, v2MinDistArr)

    val max = Math.min(
        startMinDistArr[v1] + v1MinDistArr[v2] + v2MinDistArr[N],
        startMinDistArr[v2] + v2MinDistArr[v1] + v1MinDistArr[N],
    )

    print(if (max < INF) max else -1)
}
