package com.codingtest.backjun.classfive.g3

import java.io.StreamTokenizer
import java.util.PriorityQueue

/**
 * 최소 신장 트리 문제
 *
 * - 크루스칼 알고리즘 사용
 *      우선순위 큐에 모든 간선을 추가해준 뒤
 *      하나씩 빼주면서 조건에 맞을 경우 결과값에 추가
 */
data class Node(val x: Double, val y: Double)
data class Edge(val n1: Int, val n2: Int, val dist: Double)
lateinit var parents: IntArray
lateinit var rank: IntArray
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Double {
        nextToken()
        return nval
    }

    fun find(num : Int) : Int {
        if (parents[num] == num) {
            return num
        }
        return find(parents[num])
    }

    fun unionFind(n1: Int, n2: Int) : Boolean {
        val p1 = find(n1)
        val p2 = find(n2)

        if (p1 == p2) return false
        if (rank[p1] == rank[p2]) rank[p1]++

        if (rank[p1] > rank[p2]) {
            parents[p2] = p1
        } else {
            parents[p1] = p2
        }
        return true
    }

    val N = input().toInt()

    val nodes = Array(N) {Node(0.0,0.0)}
    val pq = PriorityQueue<Edge>(compareBy { it.dist })
    parents = IntArray(N){it}
    rank = IntArray(N)

    repeat(N) { i ->
        nodes[i] = Node(input(), input())
    }

    // 두 정점을 잇는 모든 간선을 구한다.
    for (i in 0 until N-1) {
        for (j in i+1 until N) {
            pq.add(Edge(i, j, calEdge(nodes[i], nodes[j])))
        }
    }

    var result = 0.0
    var count = N-1
    while (pq.isNotEmpty()) {
        val edge = pq.poll()

        // 사이클이 생기지 않는 경우 결고값에 추가
        if (unionFind(edge.n1, edge.n2)) {
            result += edge.dist
            count--
        }
        if (count == 0) break
    }

    print(String.format("%.2f", result))
//    별자리만들기_프림풀이()
}

private fun calEdge(n1: Node, n2: Node): Double {
    return Math.sqrt(Math.pow(n1.x - n2.x, 2.0) + Math.pow(n1.y - n2.y, 2.0))
}

/**
 * 프림 알고리즘
 *  - 모든 정점을 하나씩 돌면서 최소 간선을 추가해주는 방식
 */
data class Edge2(val n: Int, val dist: Double)
fun 별자리만들기_프림풀이()  = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Double {
        nextToken()
        return nval
    }

    val N = input().toInt()
    val nodes = Array(N) {Node(0.0,0.0)}
    val arr = Array(N) { DoubleArray(N) }
    repeat(N) { i ->
        nodes[i] = Node(input(), input())
    }

    for (i in 0 until N-1) {
        for (j in i+1 until N) {
            val dist = calEdge(nodes[i], nodes[j])
            arr[i][j] = dist
            arr[j][i] = dist
        }
    }

    var result = 0.0
    val visited = BooleanArray(N)
    val pq = PriorityQueue<Edge2>(compareBy { it.dist })
    pq.add(Edge2(0,0.0))

    while (pq.isNotEmpty()) {
        val cur = pq.poll()

        if (visited[cur.n]) continue
        visited[cur.n] = true
        result += cur.dist

        for (next in 0 until N) {
            if (!visited[next]) {
                pq.add(Edge2(next, arr[cur.n][next]))
            }
        }
    }
    print(String.format("%.2f", result))
}