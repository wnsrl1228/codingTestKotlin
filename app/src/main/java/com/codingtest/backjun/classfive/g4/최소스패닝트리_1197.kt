package com.codingtest.backjun.classfive.g4

import java.io.StreamTokenizer
import java.util.PriorityQueue

/**
 * 프림
 * - 최소 신장트리 구하는 알고리즘
 *      - 노드 하나씩 돌면서 최소 간선을 추가
 *      - 방문여부 체크로 사이클 안 생김
 *      - 간선이 많을 경우 효율적
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    data class Node(val num: Int, var dist: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int {
            return if (dist <= other.dist) -1
            else 1
        }
    }

    val V = input() // 정점
    val E = input() // 간선

    val arr = Array(V+1) { arrayListOf<Node>() }

    repeat(E) {
        val a = input()
        val b = input()
        val c = input()
        arr[a].add(Node(b, c))
        arr[b].add(Node(a, c))
    }

    var result = 0
    val visited = BooleanArray(V+1)
    val pq = PriorityQueue<Node>()
    pq.add(Node(1, 0))
    while (pq.isNotEmpty()) {
        val cur = pq.poll()

        if (visited[cur.num]) continue
        visited[cur.num] = true
        result += cur.dist
        for (next in arr[cur.num]) {
            if (!visited[next.num]) {
                pq.add(next)
            }
        }
    }
    print(result)
//    최소스패닝트리_크루스칼풀이()
}
/**
 * 크루스칼
 *  - 최소 신장트리 구하는 알고리즘
 *      - 간선을 정렬한 뒤 작은 간선부터 사이클 여부를 체크
 *      - 사이클이 안 생기는 간선이면 추가,
 *      - 참고로 사이클여부는 union-find로 찾아줌
 *      - 간선이 적을 경우 효율적
 */
fun 최소스패닝트리_크루스칼풀이() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    data class Node(val num1: Int, val num2: Int, val dist: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int {
            return if (dist <= other.dist) -1
            else 1
        }
    }

    val V = input() // 정점
    val E = input() // 간선

    val edges = PriorityQueue<Node>()

    repeat(E) {
        edges.offer(Node(input(), input(), input()))
    }

    var result = 0
    val parents = IntArray(V+1) {it}

    fun find(num: Int): Int {
        if (num != parents[num])
            return find(parents[num])
        return num
    }

    fun unionFind(num1: Int, num2: Int): Boolean {
        val p1 = find(num1)
        val p2 = find(num2)
        if (p1 == p2) return false

        if (p1 > p2) {
            parents[p1] = p2
        } else {
            parents[p2] = p1
        }
        return true

    }

    while (edges.isNotEmpty()) {
        val edge = edges.poll()
        if (unionFind(edge.num1, edge.num2)) {
            result += edge.dist
        }
    }
    print(result)
}


