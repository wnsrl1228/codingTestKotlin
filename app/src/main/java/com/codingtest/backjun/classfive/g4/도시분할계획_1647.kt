package com.codingtest.backjun.classfive.g4

import java.io.StreamTokenizer
import java.util.PriorityQueue

/**
 * 최소 신장 트리 응용 문제
 * - 문제의 조건
 *    - 양뱡향
 *    - 마을을 두 개의 분리된 마을로 분할
 *    - 마을과 마을 사이에는 길이 없어야 됨
 *    - 마을 내부의 임의의 두 집 사이에 경로는 항상 존재
 *    - 마을 내부에는 최소 하나 이상의 집이 있다.
 *
 * - 방법
 *      1. 최소 신장 트리를 구해준다.
 *          - 임의의 두 집 사이에 경로는 항상 존재하며 유지비를 최소로 할 수 있는 방법
 *      2. 최소 신장 트리의 가중치값 중 최댓값을 빼준다.
 *          - 마을과 마을 사이의 길을 제거하면서 유지비를 더 줄이는 방법
 *          - 마을을 두 개로 분리, 분리된다 해도 마을 내부에는 하나 이상의 집이 존재
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

    val N = input() // 집의 개수
    val M = input() // 길의 개수

    val arr = Array(N+1) { arrayListOf<Node>() }

    repeat(M) {
        val a = input()
        val b = input()
        val c = input()
        arr[a].add(Node(b, c))
        arr[b].add(Node(a, c))
    }

    var result = 0     // 유지비
    var maxValue = 0   // 마을 사이 최대 유지비

    val pq = PriorityQueue<Node>()
    val visited = BooleanArray(N+1)
    pq.add(Node(1, 0))

    // 최소 신장 트리
    while (pq.isNotEmpty()) {
        val cur = pq.poll()

        if (visited[cur.num]) continue
        visited[cur.num] = true

        if (maxValue < cur.dist) maxValue = cur.dist  // 최대 간선 갱신
        result += cur.dist

        for (next in arr[cur.num]) {
            if (!visited[next.num]) {
                pq.add(next)
            }
        }
    }

    // 최대 간선 빼줌
    print(result - maxValue)
}