package com.codingtest.backjun.classfour.g5

import java.io.StreamTokenizer
import java.util.PriorityQueue

/**
 * 다익스트라 알고리즘
 * + 해당 문제 주의점
 *      1. 버스 비용이 0인 경우
 *      2. 동일한 버스 노선에 여러 비용을 주는 경우
 *
 * + 우선순위 큐를 사용하는 이유
 *      - 인접 노드의 비용을 신경쓰지 않고 큐에 담을 경우
 *        최소 비용의 노선으로 업데이트 된다 하더라도
 *        이미 방문한 노드일 경우 추가적인 업데이트가 불가능하다.
 *        ex) 4
 *            5
 *            1 2 10
 *            1 4 5
 *            4 2 1
 *            2 3 1
 *            4 3 10
 *            1 3
 */
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val M = input()
    val arr = Array(N+1) { IntArray(N+1) {Int.MAX_VALUE} } // 비용이 0인 경우도 있기에 MAX로 초기화

    repeat(M) {
        val start = input()
        val end = input()
        val cost = input()
        // 동일한 버스노선인 경우 최소 비용을 선택
        if (arr[start][end] > cost) {
            arr[start][end] = cost
        }
    }

    val visited = BooleanArray(N+1)              // 도시 방문 여부
    val minCost = IntArray(N+1) {Int.MAX_VALUE}  // 시작 지점에서 각 도시별 최단 비용

    val start = input()
    val end = input()

    class Node(val number: Int, val edge: Int)  // (도시, 비용)

    val queue = PriorityQueue<Node>(compareBy { it.edge })  // 최소 비용 우선순위 큐
    queue.add(Node(start, 0))
    minCost[start] = 0

    while (queue.isNotEmpty()) {
        val poll =queue.poll()
        val number = poll.number

        if (visited[number]) continue
        visited[number] = true

        for (i in 1..N) {
            // i 도시까지 경로가 존재할 때
            if (arr[number][i] != Int.MAX_VALUE) {
                // 기존 비용보다 적은 비용이 드는 경우 업데이트
                if (minCost[i] > arr[number][i] + minCost[number]) {
                    minCost[i] = arr[number][i] + minCost[number]
                    queue.add(Node(i, minCost[i]))
                }
            }
        }
    }
    print(minCost[end])
}