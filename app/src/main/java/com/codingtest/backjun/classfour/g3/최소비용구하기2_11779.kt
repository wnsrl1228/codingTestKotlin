package com.codingtest.backjun.classfour.g3

import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.PriorityQueue

/**
 * 다익스트라 문제
 *      - 최소 비용만을 요구한다면 기본적인 다익스트라를 이용하여 해결할 수 있지만
 *        해당 문제는 경로에 포함된 도시개수, 방문하는 도시 순서까지 요구하고 있다.
 *
 *        따라서 기존 각 노드별 최단 거리값을 가지는 minDist 배열만 수정해주면 된다.
 *        minDist에는 (최단 거리, 이전 노드 정보) 를 알고 있으면
 *        다익스트라로 결과를 구한 뒤 역추적하여 방문도시와 도시개수를 알 수 있다.
 *
 * 문제 주의점
 *      - 버스 비용은 0을 허용한다.
 *      - 동일한 노선에 여러 비용이 입력될 수 있다.
 */
fun main()= StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input() // 노드 개수
    val M = input() // 간선 개수

    val arr = Array(N+1) { IntArray(N+1) { Int.MAX_VALUE} }
    repeat(M) {
        val a = input()
        val b = input()
        val c = input()
        arr[a][b] = Math.min(arr[a][b], c)
    }
    for (i in 1..N) arr[i][i] = 0

    val start = input()
    val end = input()

    data class Node(val num: Int, var dist: Int)
    data class MinDist(var from: Int, var dist: Int) // 어떤 노드로부터 도달했지는, 최소거리

    val minDist = Array(N+1) { i -> MinDist(i, Int.MAX_VALUE)}
    val queue = PriorityQueue<Node>(compareBy{it.dist})

    minDist[start] = MinDist(start, 0)
    queue.add(Node(start, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        val num = cur.num

        for (next in arr[num].indices) {
            if (arr[num][next] != Int.MAX_VALUE && minDist[next].dist > arr[num][next] + minDist[num].dist) {
                minDist[next].dist = arr[num][next] + minDist[num].dist
                minDist[next].from = num
                queue.add(Node(next, minDist[next].dist))
            }
        }
    }


    // 방문 순서,  방문개수 구하기
    var count = 1
    val pathArr = LinkedList<Int>()
    var path = end
    pathArr.add(end)

    while (path != start) {
        path = minDist[path].from
        pathArr.addFirst(path)
        count++
    }

    println(minDist[end].dist)
    println(count)
    print(pathArr.joinToString(" "))
}
/**
 * v 1000
 * e 100000
 *
 * A에서 B까지의 최단 거리
 * 1. 최소 비용
 * 2. 경로에 포함된 도시 개수
 * 3. 방문하는 도시 순서
 *
 * 다익스트라 1000000
 */