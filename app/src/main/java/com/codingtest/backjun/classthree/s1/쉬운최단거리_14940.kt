package com.codingtest.backjun.classthree.s1

import java.io.StreamTokenizer
import java.util.LinkedList

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {

    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()
    val arr = Array(n) { IntArray(m) }

    data class Node(val y: Int, val x: Int, val count: Int)
    var startIndex = Node(0, 0, 0)

    for (i in 0 until n) {
        for (j in 0 until m) {
            arr[i][j] = input()
            if (arr[i][j] == 2)
                startIndex = Node(i, j, 0)
        }
    }

    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(-1, 0, 1, 0)
    val visited = Array(n) { BooleanArray(m) }

    fun bfs(start: Node) {

        visited[start.y][start.x] = true
        arr[start.y][start.x] = 0
        val queue = LinkedList<Node>()
        queue.add(start)

        while (queue.isNotEmpty()) {
            val poll = queue.removeFirst()

            for (i in dx.indices) {
                val nextX = poll.x + dx[i]
                val nextY = poll.y + dy[i]

                if (nextX < 0 || nextY < 0 || nextX >= m || nextY >= n)
                    continue
                if (visited[nextY][nextX] || arr[nextY][nextX] == 0)
                    continue

                visited[nextY][nextX] = true
                val dist = arr[poll.y][poll.x] + arr[nextY][nextX]
                arr[nextY][nextX] = dist
                queue.add(Node(nextY, nextX, dist))
            }
        }
    }
    bfs(startIndex)


    // 도달할 수 없는 지역인 경우 -1로 변환
    for (i in 0..<n) {
        for (j in 0..<m) {
            if (!visited[i][j] && arr[i][j] == 1)
                arr[i][j] = -1
        }
    }


    val sb = StringBuilder()
    for (row in arr) {
        sb.appendLine(row.joinToString(" "))
    }
    print(sb)
}